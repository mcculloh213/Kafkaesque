package com.github.mcculloh213.kafkaesque.connector

import com.github.mcculloh213.kafkaesque.data.Event
import com.github.mcculloh213.kafkaesque.stream.KafkaStream
import io.reactivex.disposables.Disposable

class MultiStreamConnector<T : Event>(
    private val stream: KafkaStream<T>
) : Connector<T> {
    private val observables = mutableListOf<Disposable>()
    override fun connect(configuration: StreamConnectorConfiguration<T>) {
        observables.add(stream.observe(configuration.type).subscribe(
            configuration.onNext,
            configuration.onError
        ))
    }
    override fun disconnect() = observables.forEach { disposable ->
        if (!disposable.isDisposed) { disposable.dispose() }
    }
}
