package com.natallia.radaman.slothsproguard

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.annotation.Keep
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.igalata.bubblepicker.BubblePickerListener
import com.igalata.bubblepicker.adapter.BubblePickerAdapter
import com.igalata.bubblepicker.model.BubbleGradient
import com.igalata.bubblepicker.model.PickerItem
import kotlinx.android.synthetic.main.activity_main.*

@Keep
class MainActivity : AppCompatActivity() {

    private val viewModel: SlothViewModel by lazy {
        ViewModelProviders.of(this).get(SlothViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bn = BN() //BubbleNumbers
        bn.sn() //bubbleNumbers.setupNumbers

        setupBubblePicker()
    }

    override fun onResume() {
        super.onResume()
        picker.onResume()
    }

    override fun onPause() {
        super.onPause()
        picker.onPause()
    }

    private fun setupBubblePicker() {

        val map = viewModel.getSloths(resources)

        picker.bubbleSize = 50
        picker.centerImmediately = true
        picker.adapter = object : BubblePickerAdapter {

            val colors = resources.obtainTypedArray(R.array.colors)
            val titles = map.toList()

            val multiplier = GO.f1 //GradientObject.field1
            val modulus = GO.f2 //GradientObject.field2
            val addition = GO.f3 //GradientObject.field3

            override val totalCount = titles.size

            override fun getItem(position: Int): PickerItem {
                return PickerItem().apply {
                    title = titles[position].first

                    val start = colors.getColor((position * multiplier) % modulus, 0)
                    val end = colors.getColor((position * multiplier) % modulus + addition, 0)
                    gradient = BubbleGradient(start, end, BubbleGradient.VERTICAL)

                    textColor = ContextCompat.getColor(this@MainActivity, android.R.color.white)
                }
            }
        }

        picker.listener = object : BubblePickerListener {

            override fun onBubbleSelected(item: PickerItem) {
                val showDetailsIntent = Intent(picker.context, SlothDetailActivity::class.java)
                val pet = map[item.title]
                showDetailsIntent.putExtra(SLOTH_KEY, pet)
                startActivity(showDetailsIntent)

                item.isSelected = false
            }

            override fun onBubbleDeselected(item: PickerItem) {
            }
        }
    }

    companion object {
        private const val SLOTH_KEY = "SLOTH"
    }
}
