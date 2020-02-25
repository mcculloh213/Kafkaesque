package com.github.mcculloh213.kafkaesque.stream

typealias StreamFactory<T> = (topic: String) -> KafkaStream<T>