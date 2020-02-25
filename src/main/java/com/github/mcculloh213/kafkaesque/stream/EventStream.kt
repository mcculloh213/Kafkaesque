package com.github.mcculloh213.kafkaesque.stream

import com.github.mcculloh213.kafkaesque.data.Event

class EventStream<T : Event>(topic: String) : KafkaStream<T>(topic) {
    override fun <E : T> emit(event: E) = publisher.onNext(event)
}
