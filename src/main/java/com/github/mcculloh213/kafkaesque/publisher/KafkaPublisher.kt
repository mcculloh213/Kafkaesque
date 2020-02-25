package com.github.mcculloh213.kafkaesque.publisher

import com.github.mcculloh213.kafkaesque.data.Event
import com.github.mcculloh213.kafkaesque.stream.Streamable

class KafkaPublisher<T : Event>(
    private val stream: Streamable<T>
) : Publisher<T> {
    override fun <E : T> notify(event: E) = stream.emit(event)
}