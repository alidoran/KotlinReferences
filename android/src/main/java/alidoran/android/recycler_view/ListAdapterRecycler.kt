package alidoran.android.recycler_view


import alidoran.android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ListAdapterRecycler:
    ListAdapter<RecyclerModel, ListAdapterRecycler.ListAdapterViewHolder>(
        ListDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ListAdapterRecycler.ListAdapterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.simple_item_recycler, parent, false)
        return ListAdapterViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListAdapterRecycler.ListAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ListAdapterViewHolder (
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val txtCode by lazy { itemView.findViewById<TextView>(R.id.txt_simple_item_code) }
        private val txtName by lazy { itemView.findViewById<TextView>(R.id.txt_simple_item_name) }
        fun bind(item: RecyclerModel) {
            txtCode.text = item.code.toString()
            txtName.text = item.name
        }
    }
    class ListDiffCallback : DiffUtil.ItemCallback<RecyclerModel>() {
        override fun areItemsTheSame(oldItem: RecyclerModel, newItem: RecyclerModel): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: RecyclerModel, newItem: RecyclerModel): Boolean {
            return oldItem == newItem
        }
    }
}

