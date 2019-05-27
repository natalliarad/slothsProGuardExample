package com.natallia.radaman.slothsproguard

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sloth_detail.*

class SlothDetailActivity : AppCompatActivity() {

    private var currentSloth: Sloth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sloth_detail)

        currentSloth = intent.getSerializableExtra(SLOTH_KEY) as Sloth

        nameTextView?.text = currentSloth?.name
        realNameTextView?.text = currentSloth?.realName
        descriptionTextView?.text = currentSloth?.description

        val resourceID = resources.getIdentifier(
            currentSloth?.imageResourceName,
            "drawable", packageName
        )
        photoImageView.setImageResource(resourceID)
    }

    companion object {
        private const val SLOTH_KEY = "SLOTH"
    }
}
