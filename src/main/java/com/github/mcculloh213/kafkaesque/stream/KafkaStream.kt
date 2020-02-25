package com.github.mcculloh213.kafkaesque.stream

import com.github.mcculloh213.kafkaesque.data.Event
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

abstract class KafkaStream<T : Event>(val topic: String) : Streamable<T> {
    protected val publisher: PublishSubject<T> = PublishSubject.create<T>()
    final override fun <E : T> observe(type: Class<E>): Observable<E> = publisher.ofType(type)
    companion object {
        fun <T : Event> newStream(topic: String, init: StreamFactory<T>) = init.invoke(topic)
    }
}