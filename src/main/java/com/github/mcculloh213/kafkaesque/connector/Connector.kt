package com.github.mcculloh213.kafkaesque.connector

interface Connector<T> {
    fun connect(configuration: StreamConnectorConfiguration<T>)
    fun disconnect()
}