package com.github.mcculloh213.kafkaesque

import com.github.mcculloh213.kafkaesque.data.Error as Err
import com.github.mcculloh213.kafkaesque.data.Event
import com.github.mcculloh213.kafkaesque.stream.*

object Kafkaesque {
    val topics: List<String>
        get() = sources.map { it.key }
    private const val DEFAULT_EVENT_STREAM: String = "topic:default-events"
    private val sources = mutableMapOf<String, Streamable<*>>(
        Pair(DEFAULT_EVENT_STREAM, newEventStream<Event>(DEFAULT_EVENT_STREAM)),
        Pair(Err.STREAM, newEventStream<Err>(Err.STREAM))
    )
    @Suppress("UNCHECKED_CAST")
    @Throws(ClassCastException::class)
    private operator fun <T : Event> get(topic: String) = try {
        sources[topic] as? Streamable<T>
    } catch (ex: ClassCastException) {
        throw ex
    }
    @Synchronized
    private operator fun <T : Event> set(topic: String, streamable: Streamable<T>) {
        if (get<T>(topic) != null) throw RuntimeException("Topic already exists")
        sources[topic] = streamable
    }
    @Synchronized
    fun <T : Event> addTopic(topic: String, factory: StreamFactory<T>): Boolean = try {
        set(topic, KafkaStream.newStream(topic, factory))
        true
    } catch (ex: RuntimeException) {
        false
    }
    @Synchronized
    fun <T : Event> getStream(topic: String): Streamable<T> {
        return get(topic) ?: run {
            val s = EventStream<T>(topic)
            addTopic(topic) { s }
            s
        }
    }
    fun getDefaultEventStream(): Streamable<Event> = get(DEFAULT_EVENT_STREAM)!!
    fun getErrorStream(): Streamable<Err> = get(Err.STREAM)!!
}