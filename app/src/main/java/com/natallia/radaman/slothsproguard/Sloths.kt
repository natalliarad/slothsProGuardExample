package com.natallia.radaman.slothsproguard

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "sloths", strict = false)
data class Sloths constructor(
    @field:ElementList(entry = "sloth", inline = true)
    @param:ElementList(entry = "sloth", inline = true)
    val list: List<Sloth>? = null
)
