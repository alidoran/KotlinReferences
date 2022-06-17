package project.bottom_sheet

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import alidoran.android_fundamental.R
import android.annotation.SuppressLint
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomSheetActivity : AppCompatActivity() {

    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var bottomSheetView: View
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)

        findViewById<Button>(R.id.btn_bottom_sheet).setOnClickListener{
            showBottomSheetModal()
        }
    }

    private fun showBottomSheetModal() {
        initializeBottomSheetModal()
        loadBottomSheetRecyclerView()
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetDialog.show()
    }

    @SuppressLint("InflateParams")
    private fun initializeBottomSheetModal() {
        bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_sample, null)
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView.parent as View)
        val maxPeekHeight = (resources.displayMetrics.heightPixels * 0.8).toInt()
        bottomSheetBehavior.peekHeight = maxPeekHeight
    }

    private fun loadBottomSheetRecyclerView() {
        val sampleList = createFakeList()
        val layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        val bottomSheetRecyclerView: RecyclerView =
            bottomSheetView.findViewById(R.id.shop_bottom_sheet_factor_recycler)
        val params = bottomSheetRecyclerView.layoutParams
        params.height = dpToPx(sampleList.size * 72)
        bottomSheetRecyclerView.layoutParams = params
        bottomSheetRecyclerView.layoutManager = layoutManager
        bottomSheetRecyclerView.isNestedScrollingEnabled = false
        val adapterList = AdapterList(sampleList)
        bottomSheetRecyclerView.layoutManager = GridLayoutManager(this, 3)
        bottomSheetRecyclerView.isNestedScrollingEnabled = true
        bottomSheetRecyclerView.adapter = adapterList
        bottomSheetRecyclerView.visibility = View.VISIBLE
    }

    private fun createFakeList(): ArrayList<String>{
        val sampleList= ArrayList<String>()
        sampleList.add("1")
        sampleList.add("2")
        sampleList.add("3")
        sampleList.add("4")
        return sampleList
    }

    private fun dpToPx(dp: Int): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }
}