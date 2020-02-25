@file:JvmName("ConnectorHelper")
package com.github.mcculloh213.kafkaesque

import com.github.mcculloh213.kafkaesque.connector.Connector
import com.github.mcculloh213.kafkaesque.connector.StreamConnector
import com.github.mcculloh213.kafkaesque.data.Error as Err
import com.github.mcculloh213.kafkaesque.data.Event

fun defaultConnector(): Connector<Event> = StreamConnector(Kafkaesque.getDefaultEventStream())
fun errorConnector(): Connector<Err> = StreamConnector(Kafkaesque.getErrorStream())
fun <T : Event> topicConnector(topic: String): Connector<T> = StreamConnector(Kafkaesque.getStream(topic))