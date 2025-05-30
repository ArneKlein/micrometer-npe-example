package com.example.demo.processor

import org.apache.kafka.streams.processor.api.ProcessorSupplier
import org.springframework.stereotype.Component

@Component
class ExampleProcessorSupplier(
) : ProcessorSupplier<String, String?, String, String?> {
    override fun get() =
        ExampleProcessor()
}
