package android.recycler_view.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android.R
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.ArrayList

class SimpleRecyclerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_recycler)
        initEvent()
    }

    private fun initEvent() {
        findViewById<View>(R.id.btn_simple_recycler).setOnClickListener {
            simpleJavaList(false)
        }
        findViewById<View>(R.id.btn_horizontal_recycler).setOnClickListener {
            simpleJavaList(true)
        }
    }

    private fun simpleJavaList(isHorizontal: Boolean) {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_simple_recycler)
        val adapter =
            SimpleJavaLearnAdapter(
                createModelList()
            )

        recyclerView.layoutManager =
            if (isHorizontal){
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            }else{
                GridLayoutManager(this, 2)
            }

        recyclerView.adapter = adapter
    }

    private fun createModelList(): List<SimpleRecyclerModel> {
        val simpleRecyclerModelList: MutableList<SimpleRecyclerModel> = ArrayList()
        for (i in 0..299) {
            val simpleRecyclerModel =
                android.recycler_view.simple.SimpleRecyclerModel(
                    i,
                    "num$i="
                )
            simpleRecyclerModelList.add(simpleRecyclerModel)
        }
        return simpleRecyclerModelList
    }
}