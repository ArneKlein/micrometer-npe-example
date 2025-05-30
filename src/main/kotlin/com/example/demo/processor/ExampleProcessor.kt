package com.example.demo.processor

import org.apache.kafka.streams.processor.PunctuationType
import org.apache.kafka.streams.processor.Punctuator
import org.apache.kafka.streams.processor.api.Processor
import org.apache.kafka.streams.processor.api.ProcessorContext
import org.apache.kafka.streams.processor.api.Record
import org.apache.kafka.streams.state.KeyValueStore
import org.slf4j.LoggerFactory
import java.time.Duration

class ExampleProcessor() : Processor<String, String?, String, String?>,
    Punctuator {
    private lateinit var context: ProcessorContext<String, String?>
    private lateinit var state: KeyValueStore<String, String?>

    override fun init(context: ProcessorContext<String, String?>) {
        this.context =
            context.also {
                it.schedule(
                    Duration.ofSeconds(30),
                    PunctuationType.WALL_CLOCK_TIME,
                    this::punctuate,
                )
            }
        this.state =
            context.getStateStore("state") as KeyValueStore<String, String?>
    }

    override fun process(record: Record<String, String?>) {
        val id = record.key()
        val value = record.value()
        if (value == null) {
            state.delete(id)
        } else {
            state.put(id, value)
        }

        context.forward(record)

    }


    override fun punctuate(timestamp: Long) {
        state.all().use { iterator ->
            while (iterator.hasNext()) {
                val currentValue = iterator.next()
                LOGGER.info("Punctuate $currentValue")
            }
        }
    }

    override fun close() {
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(ExampleProcessor::class.java)
    }
}
