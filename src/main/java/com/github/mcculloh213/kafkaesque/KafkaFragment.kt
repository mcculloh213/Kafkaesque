//package com.github.mcculloh213.kafkaesque
//
//import androidx.fragment.app.Fragment
//
//open class KafkaFragment : Fragment(),
//    KafkaProducer by SimpleEventProducer(),
//    KafkaConsumer by CompoundStreamConsumer() {
//    override fun onDestroy() {
//        unsubscribe()
//        super.onDestroy()
//    }
//}