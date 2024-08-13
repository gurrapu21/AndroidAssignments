package com.example.myfirstapplication.CallLogActivities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.entites.CallLogEntity;

import java.util.List;

public class CallLogAdapter extends RecyclerView.Adapter<CallLogAdapter.ViewHolder> {
    private List<CallLogEntity> callLogs;
    private Context context;

    public CallLogAdapter(List<CallLogEntity> callLogs, Context context) {
        this.callLogs = callLogs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.call_log_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CallLogEntity callLog = callLogs.get(position);
        holder.number.setText(callLog.getNumber());
        holder.type.setText(callLog.getType());
        holder.date.setText(callLog.getDate());
        holder.duration.setText(callLog.getDuration());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CallLogDetailsActivity.class);
            intent.putExtra("callLog", callLog);
            context.startActivity(intent);
        });
    }



    @Override
    public int getItemCount() {
        return callLogs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView number, date, duration, type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number);
            date = itemView.findViewById(R.id.date);
            duration = itemView.findViewById(R.id.duration);
            type = itemView.findViewById(R.id.type);
        }
    }
}
