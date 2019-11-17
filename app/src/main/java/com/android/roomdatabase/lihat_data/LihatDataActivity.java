package com.android.roomdatabase.lihat_data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.android.roomdatabase.R;
import com.android.roomdatabase.adapter.AdapterMahasiswa;
import com.android.roomdatabase.database.AppDatabase;
import com.android.roomdatabase.model.ModelMahasiswa;

import java.util.ArrayList;

public class LihatDataActivity extends AppCompatActivity {
    RecyclerView rcMahasiswa;
    AdapterMahasiswa adapterMahasiswa;
    ArrayList<ModelMahasiswa> data;
    AppDatabase db;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);


        getSupportActionBar().setTitle("Daftar Mahasiswa");
        Toast.makeText(getApplicationContext(), "Berikut adalah Data yang telah ditambahkan",Toast.LENGTH_LONG).show();

        rcMahasiswa = findViewById(R.id.rv_mahasiswa);
        swipe = findViewById(R.id.swipe_refresh);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,
                "db_mahasiswa"
        ).allowMainThreadQueries().build();


        getData();
    }

    void getData(){
        data = new ArrayList<>();
        data.clear();
        data.addAll(db.mahasiswaDao().getMahasiswa());
        adapterMahasiswa = new AdapterMahasiswa(getApplicationContext(),data);

        rcMahasiswa.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rcMahasiswa.setAdapter(adapterMahasiswa);
        swipe.setRefreshing(false);
    }
}
