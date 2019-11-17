package com.android.roomdatabase.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.android.roomdatabase.R;
import com.android.roomdatabase.dao.MahasiswaDao;
import com.android.roomdatabase.database.AppDatabase;
import com.android.roomdatabase.model.ModelMahasiswa;
import com.android.roomdatabase.tambah_data.TambahDataActivity;

import java.util.ArrayList;

import static com.android.roomdatabase.R.*;

public class AdapterMahasiswa extends RecyclerView.Adapter<AdapterMahasiswa.ViewHolderMahasiswa> {

    Context context;
    ArrayList<ModelMahasiswa> data;
    AppDatabase db;

    public AdapterMahasiswa(Context context, ArrayList<ModelMahasiswa> data) {
        this.context = context;
        this.data = data;

        //inisialisasi db
        db = Room.databaseBuilder(context,
                AppDatabase.class,
                "db_mahasiswa"
        ).allowMainThreadQueries().build();
    }

    @NonNull
    @Override

    //setting layout
    public ViewHolderMahasiswa onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mahasiswa, parent, false);
        return new ViewHolderMahasiswa(view);
    }

    @Override
    //setting data
    public void onBindViewHolder(@NonNull ViewHolderMahasiswa holder, final int position) {
        holder.tvNama.setText(data.get(position).getNama());
        holder.tvNim.setText(Integer.toString(data.get(position).getNim()));
        holder.tvAlamat.setText(data.get(position).getAlamat());

        //agar cardview nya bisa di klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelMahasiswa modelMahasiswa = db.mahasiswaDao().detailMahasiswa(data.get(position).getId());
                Intent intent = new Intent(context, TambahDataActivity.class);
                intent.putExtra("data", modelMahasiswa);
                //tambahkan implements Serializable pada class ModelMahasiswa untuk mengirim data 1 class
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Yakin akan menghapus Data Mahasiswa?");
                alertDialog.setMessage("Pilih Delete untuk menghapus data.");
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //delete data
                        deleteMahasiswa(position);
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //cancel
                        dialog.cancel();
                    }
                });

                alertDialog.show();
                return true;
            }
        });

    }

    @Override
    //setting jumlah data
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolderMahasiswa extends RecyclerView.ViewHolder {
        TextView tvNama, tvNim, tvAlamat;

        public ViewHolderMahasiswa(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(id.tv_list_nama);
            tvNim = itemView.findViewById(id.tv_list_nim);
            tvAlamat = itemView.findViewById(id.tv_list_alamat);
        }
    }

    public void deleteMahasiswa(int position){
        db.mahasiswaDao().deleteMahasiswa(data.get(position));
        data.remove(position);
        notifyItemChanged(position);
        notifyItemChanged(position,data.size());
    }

}
