package com.github.mcculloh213.kafkaesque.data

open class Error(
    val message: String,
    val code: Int,
    val throwable: Throwable? = null
) : Event("") {
    companion object {
        const val STREAM: String = "topic:error"
    }
}