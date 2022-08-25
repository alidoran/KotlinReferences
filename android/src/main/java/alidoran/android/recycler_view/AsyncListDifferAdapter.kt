package alidoran.android.recycler_view

import alidoran.android.databinding.SimpleItemRecyclerBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class AsyncListDifferAdapter :
    RecyclerView.Adapter<AsyncListDifferAdapter.AsyncDifferRecyclerListViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<RecyclerModel>() {
        override fun areItemsTheSame(oldItem: RecyclerModel, newItem: RecyclerModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RecyclerModel, newItem: RecyclerModel): Boolean {
            return oldItem == newItem
        }
    }

    private val differ =
        AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AsyncDifferRecyclerListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SimpleItemRecyclerBinding.inflate(inflater, parent, false)
        return AsyncDifferRecyclerListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AsyncDifferRecyclerListViewHolder,
        position: Int
    ) =
        holder.bind(differ.currentList[position])


    override fun getItemCount(): Int =
        differ.currentList.size

    fun submitList(list: List<RecyclerModel>) {
        differ.submitList(list)
    }

    inner class AsyncDifferRecyclerListViewHolder(
        private val binding: SimpleItemRecyclerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recyclerModel: RecyclerModel) {
            binding.model = recyclerModel
        }
    }
}