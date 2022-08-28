package alidoran.android.recycler_view

import alidoran.android.R
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.*
import kotlinx.coroutines.*

class RecyclerActivity : AppCompatActivity() {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler_simple_recycler) }

    private var recyclerListForLiveData = ArrayList<RecyclerModel>(
        arrayListOf(
            RecyclerModel(0, "num=0")
        )
    )
    private val _liveData = MutableLiveData<List<RecyclerModel>>()
    private val liveData
        get() = _liveData

    private val listAdapter = ListRecyclerAdapter()
    private val asyncAdapter = AsyncListDifferAdapter()
    private lateinit var job: Job


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        initEvent()
    }


    private fun initEvent() {
        findViewById<Button>(R.id.btn_simple_recycler).setOnClickListener {
            cancelJob()
            simpleRecycler()
        }
        findViewById<Button>(R.id.btn_simple_horizontal_recycler).setOnClickListener {
            cancelJob()
            horizontalRecycler()
        }
        findViewById<Button>(R.id.btn_recycler_view_binding).setOnClickListener {
            cancelJob()
            bindingRecycler()
        }
        findViewById<Button>(R.id.btn_recycler_list_adapter).setOnClickListener {
            cancelJob()
            listAdapter()
        }
        findViewById<Button>(R.id.btn_recycler_differ).setOnClickListener {
            cancelJob()
            differRecycler()
        }
    }


    private fun simpleRecycler() {
        recyclerView.layoutManager =
            GridLayoutManager(this, 2)
        recyclerView.adapter = SimpleRecyclerAdapter(listGenerator())
    }

    private fun horizontalRecycler() {
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = SimpleRecyclerAdapter(listGenerator())
    }

    private fun bindingRecycler() {
        recyclerView.layoutManager =
            GridLayoutManager(this, 2)
        recyclerView.adapter = BindingRecyclerAdapter(listGenerator())
    }

    private fun listAdapter() {
        if (recyclerListForLiveData.size == 1)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@RecyclerActivity)
                addItemDecoration(
                    DividerItemDecoration(
                        this@RecyclerActivity,
                        DividerItemDecoration.VERTICAL
                    )
                )
                itemAnimator = DefaultItemAnimator()
                adapter = listAdapter
            }
        liveData.observe(this) {
            listAdapter.submitList(it.shuffled())
        }
        fakeLiveDataGenerator()
    }

    private fun differRecycler() {
        if (recyclerListForLiveData.size == 1)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@RecyclerActivity)
                addItemDecoration(
                    DividerItemDecoration(
                        this@RecyclerActivity,
                        DividerItemDecoration.VERTICAL
                    )
                )
                itemAnimator = DefaultItemAnimator()
                adapter = asyncAdapter
            }
        liveData.observe(this) {
            asyncAdapter.submitList(it.shuffled())
        }
        fakeLiveDataGenerator()
    }

    private fun listGenerator(): List<RecyclerModel> {
        val recyclerModelList: MutableList<RecyclerModel> = ArrayList()
        for (i in 0..299) {
            val recyclerModel =
                RecyclerModel(
                    i,
                    "num$i="
                )
            recyclerModelList.add(recyclerModel)
        }
        return recyclerModelList
    }

    private fun fakeLiveDataGenerator() {
        job = CoroutineScope(Dispatchers.Main).launch {
            while (recyclerListForLiveData.last().code < 300) {
                livedataGenerator()
                delay(2000)
            }
        }
    }

    private fun livedataGenerator() {
        val newCode = recyclerListForLiveData.last().code + 1
        recyclerListForLiveData.add(
            RecyclerModel(newCode + 1, "num$newCode=")
        )
        _liveData.value = recyclerListForLiveData
    }

    private fun cancelJob() {
        if (::job.isInitialized && job.isActive)
            job.cancel()
        recyclerListForLiveData = ArrayList(
            arrayListOf(
                RecyclerModel(0, "num=0")
            )
        )
    }

}