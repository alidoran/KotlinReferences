package alidoran.android.viewpager2.slider

import alidoran.android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class SliderAdapter(
    private val viewPager: ViewPager2,
    private val imgList: ArrayList<ViewPagerSliderModel>
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {
    inner class SliderViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val img = v.findViewById<ImageView>(R.id.img_slider)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.viewpager_item, parent, false)
        return SliderViewHolder(v)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val listImg = imgList[position]
        holder.img.setImageResource(listImg.img)
        if(position ==imgList.size-2){
            viewPager.post(run)
        }
    }

    private val run = Runnable {
        imgList.addAll(imgList)
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int = imgList.size
}