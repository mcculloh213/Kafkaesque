package com.github.mcculloh213.kafkaesque.connector

import com.github.mcculloh213.kafkaesque.data.Event
import com.github.mcculloh213.kafkaesque.stream.Streamable
import io.reactivex.disposables.Disposable
import java.util.concurrent.atomic.AtomicReference

class StreamConnector<T : Event>(
    private val stream: Streamable<T>
) : Connector<T> {
    private val atomicDisposable: AtomicReference<Disposable?> = AtomicReference(null)
    override fun connect(configuration: StreamConnectorConfiguration<T>) {
        atomicDisposable.getAndSet(stream.observe(configuration.type).subscribe(
            configuration.onNext,
            configuration.onError
        ))?.apply { if (!isDisposed) { dispose() } }
    }
    override fun disconnect() {
        atomicDisposable.getAndSet(null)?.apply { if (!isDisposed) { dispose() } }
    }

}