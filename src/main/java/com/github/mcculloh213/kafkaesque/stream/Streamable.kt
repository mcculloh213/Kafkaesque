package com.github.mcculloh213.kafkaesque.stream

import com.github.mcculloh213.kafkaesque.data.Event
import io.reactivex.Observable

/**
 * Defines a streaming interface for objects of <i>T</i>.
 */
interface Streamable<T : Event> {
    /**
     * Emit will
     */
    fun <E : T> emit(event: E)
    fun <E : T> observe(type: Class<E>): Observable<E>
}