package com.github.mcculloh213.kafkaesque.publisher

interface Publisher<T> {
    fun <E : T> notify(event: E)
}