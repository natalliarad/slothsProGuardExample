package com.natallia.radaman.slothsproguard

import android.arch.lifecycle.ViewModel
import android.content.res.Resources
import org.simpleframework.xml.core.Persister
import java.util.SortedMap

class SlothViewModel : ViewModel() {

    private var sloths: SortedMap<String, Sloth>? = null

    fun getSloths(resources: Resources): SortedMap<String, Sloth> {
        if (sloths == null) {
            loadSloths(resources)
        }

        return sloths ?: sortedMapOf()
    }

    private fun loadSloths(resources: Resources) {
        val serializer = Persister()
        val inputStream = resources.openRawResource(R.raw.sloths) // 2
        val sloths = serializer.read(Sloths::class.java, inputStream) // 3
        sloths.list?.let { theList ->
            val map = theList.associateBy({ it.name }, { it })
            this.sloths = map.toSortedMap()
        }
    }
}
