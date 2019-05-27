package com.natallia.radaman.slothsproguard

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name = "sloth", strict = false)
data class Sloth constructor(
    @field:Element(name = "name")
    @param:Element(name = "name")
    var name: String = "",

    @field:Element(name = "realName")
    @param:Element(name = "realName")
    var realName: String = "",

    @field:Element(name = "imageResource")
    @param:Element(name = "imageResource")
    var imageResourceName: String = "",

    @field:Element(name = "description")
    @param:Element(name = "description")
    var description: String = "") : Serializable
