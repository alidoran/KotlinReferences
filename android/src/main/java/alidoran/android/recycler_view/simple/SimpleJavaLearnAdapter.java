package alidoran.android.recycler_view.simple;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import alidoran.android.R;

public class SimpleJavaLearnAdapter extends RecyclerView.Adapter<SimpleJavaLearnAdapter.ViewHolder> {

    private final List<SimpleRecyclerModel> simpleRecyclerModelList;

    SimpleJavaLearnAdapter(List<SimpleRecyclerModel> simpleRecyclerModelList) {
        this.simpleRecyclerModelList = simpleRecyclerModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.simple_item_recycler, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        SimpleRecyclerModel simpleRecyclerModel = simpleRecyclerModelList.get(i);
        viewHolder.itemCodeView.setText(String.valueOf(simpleRecyclerModel.getCode()));
        viewHolder.itemNameView.setText(simpleRecyclerModel.getName());
        viewHolder.itemView.setOnClickListener(v ->
                Toast.makeText(v.getContext(), simpleRecyclerModel.getName() + simpleRecyclerModel.getCode(), Toast.LENGTH_LONG).show());
    }

    @Override
    public int getItemCount() {
        return simpleRecyclerModelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameView;
        TextView itemCodeView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCodeView = itemView.findViewById(R.id.txt_simple_item_code);
            itemNameView = itemView.findViewById(R.id.txt_simple_item_name);
        }
    }
}