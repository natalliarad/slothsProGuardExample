package com.natallia.radaman.slothsproguard

import android.support.annotation.Keep
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

class BN { //BubbleNumbers
    fun sn() { //setupNumbers

        //setValue
        sv("com.natallia.radaman.slothsproguard.GO", "f1", 2)
        sv("com.natallia.radaman.slothsproguard.GO", "f2", 8)
        sv("com.natallia.radaman.slothsproguard.GO", "f3", 1)
    }
}

object GO { //GradientObject
    var f1 = 3 //field1
    var f2 = 6 //field2
    var f3 = 7 //field3
}

fun sv(ownerClassName: String, fieldName: String, value: Any) {
    val kClass = Class.forName(ownerClassName).kotlin
    val instance = kClass.objectInstance ?: kClass.java.newInstance()
    val member = kClass.memberProperties.filterIsInstance<KMutableProperty<*>>()
        .firstOrNull { it.name == fieldName }
    member?.setter?.call(instance, value)
}
