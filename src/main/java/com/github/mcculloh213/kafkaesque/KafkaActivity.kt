//package com.github.mcculloh213.kafkaesque
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//
//open class KafkaActivity : AppCompatActivity(),
//    KafkaProducer by SimpleEventProducer(),
//    KafkaConsumer by CompoundStreamConsumer() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//    override fun onDestroy() {
//        unsubscribe()
//        super.onDestroy()
//    }
//}