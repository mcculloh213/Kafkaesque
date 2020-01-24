package com.github.mcculloh213.kafkaesque

interface KafkaProducer {
    fun publish(event: Event)
}
open class SimpleEventProducer : KafkaProducer {
    override fun publish(event: Event) =
        Kafka.stream(event)
}