package com.wangduwei.kotlin.jvm_annotation


interface Play2 {
    fun play()
}

class Singleton2 {

    private companion object : Play2 {
        var any = Any()
        @JvmStatic
        override fun play() {
            println(any.hashCode())
        }

    }


}