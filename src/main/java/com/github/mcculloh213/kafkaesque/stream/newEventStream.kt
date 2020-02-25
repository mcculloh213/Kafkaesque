@file:JvmName("StreamHelper")
package com.github.mcculloh213.kafkaesque.stream

import com.github.mcculloh213.kafkaesque.data.Event

fun <T: Event> newEventStream(topic: String) = KafkaStream.newStream(topic) { EventStream<T>(it) }