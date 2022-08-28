package alidoran.android.recycler_view


import alidoran.android.databinding.SimpleItemRecyclerBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ListRecyclerAdapter :
    ListAdapter<RecyclerModel, ListRecyclerAdapter.ListAdapterViewHolder>(
        ListDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ListRecyclerAdapter.ListAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SimpleItemRecyclerBinding.inflate(inflater, parent, false)
        return ListAdapterViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: ListRecyclerAdapter.ListAdapterViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class ListAdapterViewHolder(
        private val binding: SimpleItemRecyclerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecyclerModel) {
            binding.model = item
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

