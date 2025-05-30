package com.example.demo.config

import com.example.demo.processor.ExampleProcessorSupplier
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.Produced
import org.apache.kafka.streams.state.KeyValueStore
import org.apache.kafka.streams.state.StoreBuilder
import org.springframework.kafka.config.KafkaStreamsInfrastructureCustomizer
import org.springframework.stereotype.Component

@Component
class InfrastructureCustomizer(
    val exampleProcessorSupplier: ExampleProcessorSupplier,
    val exampleStore: StoreBuilder<KeyValueStore<String, String?>>,
) : KafkaStreamsInfrastructureCustomizer {
    override fun configureBuilder(builder: StreamsBuilder) {
        builder
            .addStateStore(exampleStore)
        builder
            .stream(
                "source-topic",
                Consumed.with(
                    Serdes.String(), Serdes.String(),
                )
            ).process(exampleProcessorSupplier, "state")
            .to("target-topic", Produced.with(Serdes.String(), Serdes.String()))
    }


}