package com.example.demo.config

import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.state.KeyValueStore
import org.apache.kafka.streams.state.StoreBuilder
import org.apache.kafka.streams.state.Stores
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StateStoreConfig {
    @Bean
    fun exampleStore(
    ): StoreBuilder<KeyValueStore<String, String?>> =
        Stores.keyValueStoreBuilder(
            Stores.persistentKeyValueStore("state"),
            Serdes.String(),
            Serdes.String(),
        )
}