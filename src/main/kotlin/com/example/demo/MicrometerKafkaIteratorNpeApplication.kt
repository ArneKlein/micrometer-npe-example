package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafkaStreams
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class MicrometerKafkaIteratorNpeApplication {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<MicrometerKafkaIteratorNpeApplication>(*args)
		}
	}
}
