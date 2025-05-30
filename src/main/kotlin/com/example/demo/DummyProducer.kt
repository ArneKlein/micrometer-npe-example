package com.example.demo

import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class DummyProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {
    @Scheduled(initialDelay = 0, fixedDelay = 10_000)
    fun createTestRecords() {

        val records = (1L..100L)
        records.forEach { value ->
            kafkaTemplate.send(
                ProducerRecord(
                    "source-topic",
                    "$value",
                    "$value",
                )
            )
        }

    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(DummyProducer::class.java)
    }
}