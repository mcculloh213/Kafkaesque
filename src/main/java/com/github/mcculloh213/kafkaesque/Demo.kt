package com.github.mcculloh213.kafkaesque

import com.github.mcculloh213.kafkaesque.connector.Connector
import com.github.mcculloh213.kafkaesque.connector.OnErrorListener
import com.github.mcculloh213.kafkaesque.connector.OnNextListener
import com.github.mcculloh213.kafkaesque.connector.StreamConnectorConfiguration
import com.github.mcculloh213.kafkaesque.data.Event
import com.github.mcculloh213.kafkaesque.publisher.Publisher

open class Demo : Event("test")
class DemoPublisher : Publisher<Demo> by topicPublisher("test")
class DemoConnector : Connector<Demo> by topicConnector("test") {
    private val demoConfig: StreamConnectorConfiguration<Demo> by lazy {
        StreamConnectorConfiguration(
            topic = "test",
            type = Demo::class.java,
            onNext = onNext,
            onError = onError
        )
    }
    val onNext: OnNextListener<Demo> = { event ->

    }
    val onError: OnErrorListener = { t ->

    }
    init { connect(demoConfig) }
}