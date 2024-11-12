package alidoran.android.recycler_view

import alidoran.android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class SimpleRecyclerAdapter constructor(
    private val recyclerModelList: List<RecyclerModel>
) :
    RecyclerView.Adapter<SimpleRecyclerAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.simple_item_recycler, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val simpleRecyclerModel = recyclerModelList[position]
        holder.txtCode.text = simpleRecyclerModel.code.toString()
        holder.txtName.text = simpleRecyclerModel.name
        holder.itemView.setOnClickListener { v: View ->
            Toast.makeText(
                v.context,
                simpleRecyclerModel.name + simpleRecyclerModel.code,
                Toast.LENGTH_LONG
            ).show()
        }

    }

    override fun getItemCount(): Int {
        return recyclerModelList.size
    }

    inner class RecyclerViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        var txtCode: TextView = itemView.findViewById(R.id.txt_simple_item_code)
        var txtName: TextView = itemView.findViewById(R.id.txt_simple_item_name)
    }
}