package com.github.mcculloh213.kafkaesque

import io.reactivex.disposables.Disposable
import java.util.concurrent.atomic.AtomicReference

typealias StreamListener<T> = ((T) -> Unit)

interface KafkaConsumer {
    fun <T : Event> subscribe(eventType: Class<T>, onNext: StreamListener<T>): Boolean
    fun unsubscribe()
}
open class CompoundStreamConsumer :
    KafkaConsumer {
    protected val streams: List<Disposable>
        get() = _streams
    private val _streams = mutableListOf<Disposable>()
    override fun <T : Event> subscribe(eventType: Class<T>, onNext: StreamListener<T>) =
        _streams.add(Kafka.listen(eventType).subscribe(onNext))
    override fun unsubscribe() = _streams.forEach { stream ->
        if (!stream.isDisposed) { stream.dispose() }
    }
}
open class SingleStreamConsumer :
    KafkaConsumer {
    val stream: Disposable?
        get() = _stream.get()
    private val _stream: AtomicReference<Disposable?> = AtomicReference(null)
    override fun <T : Event> subscribe(eventType: Class<T>, onNext: StreamListener<T>): Boolean {
        _stream.getAndSet(Kafka.listen(eventType).subscribe(onNext))?.apply {
            if (!isDisposed) {
                dispose()
            }
        }
        return true
    }
    override fun unsubscribe() {
        _stream.getAndSet(null)?.apply {
            if (!isDisposed) {
                dispose()
            }
        }
    }
}
