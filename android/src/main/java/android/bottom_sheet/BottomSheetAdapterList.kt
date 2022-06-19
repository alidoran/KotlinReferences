package android.bottom_sheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.google.android.material.textview.MaterialTextView

class AdapterList(private val sampleList: List<String>) :
    RecyclerView.Adapter<ListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bottom_sheet_item_list, parent, false)
        return ListHolder(view)
    }

    override fun onBindViewHolder(holder: ListHolder, i: Int) {
        val string = sampleList[i]
        holder.viewItemName.text = string
    }

    override fun getItemCount(): Int {
        return sampleList.size
    }
}

class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var viewItemName: MaterialTextView = itemView.findViewById(R.id.list_item_name_view)
}