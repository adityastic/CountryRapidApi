package com.vivek.rapidapi.ui

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.vivek.rapidapi.R
import com.vivek.rapidapi.data.CountryInfo
import com.vivek.rapidapi.databinding.CountrydetailsActivityBinding
import kotlinx.android.synthetic.main.countrydetails_activity.*

class CountryDetailsActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityBinding =
            DataBindingUtil.setContentView<CountrydetailsActivityBinding>(
                this,
                R.layout.countrydetails_activity
            )
        activityBinding.country = intent.getSerializableExtra("country") as? CountryInfo

        postponeEnterTransition()
        setBackListener()
        loadImageWithTransition()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setBackListener() {
        close_button.setOnClickListener {
            finishAfterTransition()
        }
    }

    private fun loadImageWithTransition() {
        Glide.with(this)
            .load(intent.getStringExtra("imageURL"))
            .fitCenter()
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

            })
            .into(country_image)
    }
}
