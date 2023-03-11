package com.example.todo_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    Context context;
    List<DataModel> list;
    Adapter(Context context, List<DataModel> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.data_model, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataModel dataModel = list.get(position);
        holder.name.setText(dataModel.getName());
        holder.check.setImageResource(R.drawable.check_circle);
        holder.check.setOnClickListener(v->{
            DatabaseHelper db = new DatabaseHelper(context);
            db.deleteOne(dataModel.getName());
            list.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return list.size()==0?0:list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView check;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.task_name);
            check = itemView.findViewById(R.id.check_box);
        }
    }
}
