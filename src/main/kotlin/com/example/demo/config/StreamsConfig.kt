package com.example.demo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafkaStreams
import org.springframework.kafka.config.StreamsBuilderFactoryBeanConfigurer

@Configuration(proxyBeanMethods = false)
@EnableKafkaStreams
class StreamsConfig {
    @Bean
    fun streamsBuilderFactoryBeanConfigurer(
        skeletonSpringBootKafkaStreamsInfrastructureCustomizer: InfrastructureCustomizer,
    ): StreamsBuilderFactoryBeanConfigurer =
        StreamsBuilderFactoryBeanConfigurer {
            it.setInfrastructureCustomizer(
                skeletonSpringBootKafkaStreamsInfrastructureCustomizer,
            )
        }
}
