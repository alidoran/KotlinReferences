package alidoran.android.viewpager2.slider

import alidoran.android.R
import alidoran.android.databinding.ActivityViewPagerSliderBinding
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.abs

class ViewPagerSliderActivity : AppCompatActivity() {

    private lateinit var modelList: ArrayList<ViewPagerSliderModel>
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var sliderHandle: Handler
    private lateinit var sliderRun: Runnable
    private lateinit var binding: ActivityViewPagerSliderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerSliderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sliderItems()
        itemSliderView()
    }

    private fun itemSliderView() {
        modelList.add(ViewPagerSliderModel(R.drawable.ic_launcher_background))
        modelList.add(ViewPagerSliderModel(R.drawable.baseline_thumb_down_black_24dp))
        modelList.add(ViewPagerSliderModel(R.drawable.ic_launcher_foreground))
        modelList.add(ViewPagerSliderModel(R.drawable.baseline_thumb_up_inverse_24dp))
    }

    private fun sliderItems() {
        modelList = ArrayList()
        sliderAdapter = SliderAdapter(binding.viewpager, modelList)
        binding.viewpager.adapter = sliderAdapter
        binding.viewpager.clipToPadding = false
        binding.viewpager.clipChildren = false
        binding.viewpager.offscreenPageLimit = 3
        binding.viewpager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        val comPosPageTarn = CompositePageTransformer()
        comPosPageTarn.addTransformer(MarginPageTransformer(40))
        comPosPageTarn.addTransformer { page, position ->
            val r: Float = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        binding.viewpager.setPageTransformer(comPosPageTarn)
        sliderHandle = Handler()
        sliderRun = Runnable {
            binding.viewpager.currentItem = binding.viewpager.currentItem + 1
        }
        binding.viewpager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandle.removeCallbacks(sliderRun)
                    sliderHandle.postDelayed(sliderRun, 2000)
                }
            }
        )
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            0 -> R.drawable.baseline_thumb_up_inverse_24dp
            1 -> R.drawable.baseline_thumb_down_black_24dp
            2 -> R.drawable.baseline_thumb_up_inverse_24dp
            3 -> R.drawable.baseline_thumb_down_black_24dp
            else -> R.drawable.baseline_thumb_up_inverse_24dp
        }
    }

    private fun getTabTitle(position: Int): String {
        return when (position) {
            0 -> "First one"
            1 -> "Second one"
            2 -> "third one"
            3 -> "fourth one"
            else -> "other one"
        }
    }

    override fun onPause() {
        super.onPause()
        sliderHandle.removeCallbacks(sliderRun)
    }

    override fun onResume() {
        super.onResume()
        sliderHandle.postDelayed(sliderRun, 3000)
    }
}