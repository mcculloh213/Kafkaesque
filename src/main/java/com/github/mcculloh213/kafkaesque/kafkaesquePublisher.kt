@file:JvmName("PublisherHelper")
package com.github.mcculloh213.kafkaesque

import com.github.mcculloh213.kafkaesque.data.Error as Err
import com.github.mcculloh213.kafkaesque.data.Event
import com.github.mcculloh213.kafkaesque.publisher.KafkaPublisher
import com.github.mcculloh213.kafkaesque.publisher.Publisher

fun defaultPublisher(): Publisher<Event> = KafkaPublisher(Kafkaesque.getDefaultEventStream())
fun errorPublisher(): Publisher<Err> = KafkaPublisher(Kafkaesque.getErrorStream())
fun <T : Event> topicPublisher(topic: String): Publisher<T> = KafkaPublisher(Kafkaesque.getStream(topic))

