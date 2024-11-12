package alidoran.android.recycler_view

import alidoran.android.databinding.SimpleItemRecyclerBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BindingRecyclerAdapter(
    private val recyclerList: List<RecyclerModel>
) :
    RecyclerView.Adapter<BindingRecyclerAdapter.BindingRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):
            BindingRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            SimpleItemRecyclerBinding.inflate(inflater, parent, false)
        return BindingRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingRecyclerViewHolder, position: Int) =
        holder.bind(recyclerList[position])


    override fun getItemCount(): Int =
        recyclerList.size


    inner class BindingRecyclerViewHolder(
        private val binding: SimpleItemRecyclerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: RecyclerModel) {
            binding.model = model
        }
    }
}