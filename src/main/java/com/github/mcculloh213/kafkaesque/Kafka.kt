package com.github.mcculloh213.kafkaesque

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object Kafka {
    private val publisher = PublishSubject.create<Any>()
    fun stream(event: Any) = publisher.onNext(event)
    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)
}