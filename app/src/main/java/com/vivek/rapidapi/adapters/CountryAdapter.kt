package com.vivek.rapidapi.adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikhaellopez.circularimageview.CircularImageView
import com.vivek.rapidapi.R
import com.vivek.rapidapi.data.CountryInfo
import com.vivek.rapidapi.ui.CountryDetailsActivity


class CountryAdapter(private val userList: List<CountryInfo>, private val frameAct: Activity) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewcity = itemView.findViewById(R.id.list_country) as TextView
        val textViewregion = itemView.findViewById(R.id.list_region) as TextView
        val textViewcapital = itemView.findViewById(R.id.list_capital) as TextView
        val image = itemView.findViewById(R.id.image) as CircularImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cname: CountryInfo = userList[position]
        holder.textViewcity.text = cname.name
        holder.textViewcapital.text = cname.capital
        holder.textViewregion.text = cname.region

        val imageUrl = "https://www.countryflags.io/${cname.alpha2Code}/flat/64.png"

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .fitCenter()
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val options = ActivityOptions
                .makeSceneTransitionAnimation(
                    frameAct,
                    holder.image,
                    ViewCompat.getTransitionName(holder.image)
                )

            ActivityOptions.makeSceneTransitionAnimation(frameAct)
            Intent(holder.itemView.context, CountryDetailsActivity::class.java)
                .putExtra("imageURL", imageUrl)
                .putExtra("country",  cname)
                .let {
                    holder.itemView.context.startActivity(it, options.toBundle())
                }

        }
    }
}