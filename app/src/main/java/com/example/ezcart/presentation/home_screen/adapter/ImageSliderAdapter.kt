package com.example.ezcart.presentation.home_screen.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.ezcart.R

class ImageSliderAdapter(private val context: Context) : PagerAdapter() {


    private var inflater: LayoutInflater? = null
    private val images = arrayOf(R.drawable.banner_01, R.drawable.banner_02,
        R.drawable.banner_03, R.drawable.banner_04,
        R.drawable.banner_05, R.drawable.banner_06)

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view === `object`
    }

    override fun getCount(): Int {

        return images.size
    }

    @SuppressLint("InflateParams")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val image: ImageView
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater!!.inflate(R.layout.image_slider_item, null)

        image = view.findViewById(R.id.imageViewSlider)
        image.setImageResource(images[position])

        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }

}