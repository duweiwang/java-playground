package com.wangduwei.kotlin.jvm_annotation

interface Play {
    fun play()
}

object Singleton : Play {
//    @JvmStatic ==>Error
    override fun play() {


    }
}