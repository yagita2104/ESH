package com.yagita.esh.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.yagita.esh.R;

import java.util.List;

public class SpecializedAdapter extends RecyclerView.Adapter<SpecializedAdapter.ViewHolder> {
    List list;
    Context context;

    public SpecializedAdapter(List list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_specialized, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = (String) list.get(position);
        holder.btnItemSpecialized.setText(item);
        holder.btnItemSpecialized.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Học phần");
                String name = item;
                builder.setItems(new CharSequence[]{item + " 1 ", item + " 2 ",item + " 3 ",item + " 4 ",item + " 5 ",item + " 6 ",}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Toast.makeText(view.getContext(), "Đã chọn học phần 1", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(view.getContext(), "Đã chọn học phần 2", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(view.getContext(), "Đã chọn học phần 3", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                Toast.makeText(view.getContext(), "Đã chọn học phần 4", Toast.LENGTH_SHORT).show();
                                break;
                            case 4:
                                Toast.makeText(view.getContext(), "Đã chọn học phần 5", Toast.LENGTH_SHORT).show();
                                break;
                            case 5:
                                Toast.makeText(view.getContext(), "Đã chọn học phần 6", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Button btnItemSpecialized;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnItemSpecialized = itemView.findViewById(R.id.btnItemSpecialized);
        }

    }
}
