package com.github.mcculloh213.kafkaesque.connector

import io.reactivex.disposables.Disposable

typealias OnNextListener<T> = (T) -> Unit
typealias OnErrorListener = (Throwable) -> Unit
typealias OnCompleteListener = () -> Unit
typealias OnSubscribeListener = (Disposable) -> Unit

data class StreamConnectorConfiguration<T>(
    val topic: String,
    val type: Class<T>,
    val onNext: OnNextListener<T>,
    val onError: OnErrorListener
)