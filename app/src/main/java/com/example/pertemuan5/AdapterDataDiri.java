package com.example.pertemuan5;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pertemuan5.Database.DataDiri;

import java.util.List;

public class AdapterDataDiri extends RecyclerView.Adapter<AdapterDataDiri.ViewHolder> {
    private List<DataDiri> dataDiris;
    private DataDiriListener dataDiriListener;
    public Context context;
    public String nama, alamat;
    public int id;
    public char jk;

    public AdapterDataDiri(Context context, DataDiriListener listener) {
        this.context = context;
        this.dataDiriListener = listener;
    }

    public List<DataDiri> getDataDiris() {
        return dataDiris;
    }
    public void setDataDiris(List<DataDiri> dataDiris) {
        this.dataDiris = dataDiris;
    }

    @NonNull
    @Override
    public AdapterDataDiri.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemRow= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_datadiri, parent, false);
        return new ViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataDiri.ViewHolder holder, final int position) {
        holder.tvNama.setText(getDataDiris().get(position).getNama());
        holder.tvAlamat.setText(getDataDiris().get(position).getAlamat());
        holder.tvJk.setText(""+getDataDiris().get(position).getJk());

        holder.bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataDiriListener.OnButtonDelete(getDataDiris().get(position));
            }
        });
        holder.bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = getDataDiris().get(position).getId();
                nama = getDataDiris().get(position).getNama();
                alamat = getDataDiris().get(position).getAlamat();
                jk = getDataDiris().get(position).getJk();
                Intent intent = new Intent(v.getContext(),UpdateActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("nama",nama);
                intent.putExtra("alamat",alamat);
                intent.putExtra("jk",jk);
                v.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return getDataDiris().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama, tvAlamat, tvJk;
        private Button bDelete,bEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvAlamat = itemView.findViewById((R.id.tvAlamat));
            tvJk = itemView.findViewById(R.id.tvJK);
            bDelete =itemView.findViewById(R.id.btnDelete);
            bEdit = itemView.findViewById(R.id.btnEdit);
        }
    }
}
