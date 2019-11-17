package com.android.roomdatabase.tambah_data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.roomdatabase.R;
import com.android.roomdatabase.database.AppDatabase;
import com.android.roomdatabase.lihat_data.LihatDataActivity;
import com.android.roomdatabase.model.ModelMahasiswa;

public class TambahDataActivity extends AppCompatActivity {

    Button btnSubmit;
    EditText etNama, etNim, etAlamat;
    //membuat object db
    AppDatabase db;

    //kebutuhan untuk update
    ModelMahasiswa modelMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        getSupportActionBar().setTitle("Tambah Data Mahasiswa");

        //inisislisasi nama database db_mahasiswa
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "db_mahasiswa").build();

        //update
        modelMahasiswa = (ModelMahasiswa) getIntent().getSerializableExtra("data");

        btnSubmit = findViewById(R.id.btn_submit);
        etNama = findViewById(R.id.et_nama);
        etNim = findViewById(R.id.et_nim);
        etAlamat = findViewById(R.id.et_alamat);

        if(modelMahasiswa != null){
            Toast.makeText(getApplicationContext(), "Silahkan Ubah Data",Toast.LENGTH_LONG).show();
            btnSubmit.setText("Update");
            etNama.setText(modelMahasiswa.getNama());
            etNim.setText(Integer.toString(modelMahasiswa.getNim()));
            etAlamat.setText(modelMahasiswa.getAlamat());
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modelMahasiswa.setNim(Integer.parseInt(etNim.getText().toString()));
                    modelMahasiswa.setNama(etNama.getText().toString());
                    modelMahasiswa.setAlamat(etAlamat.getText().toString());

                    updateMahasiswa(modelMahasiswa);
                    //startActivity(new Intent(TambahDataActivity.this, LihatDataActivity.class));
                    finish();
                }
            });
        }else{
            Toast.makeText(getApplicationContext(), "Silahkan Tambah Data",Toast.LENGTH_LONG).show();
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nim = Integer.parseInt(etNim.getText().toString());
                    String nama = etNama.getText().toString();
                    String alamat = etAlamat.getText().toString();

                    ModelMahasiswa data = new ModelMahasiswa(nim, nama, alamat);

                    insertMahasiswa(data);
                    startActivity(new Intent(TambahDataActivity.this, LihatDataActivity.class));
                    finish();
                }
            });
        }


    }

    @SuppressLint("StaticFieldLeak")
    public void insertMahasiswa(final ModelMahasiswa modelMahasiswa){
        new AsyncTask<Void,Void,Long>(){

            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.mahasiswaDao().insertMahasiswa(modelMahasiswa);
                return status;
            }
            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                Toast.makeText(getApplicationContext(), "Data tersimpan",Toast.LENGTH_LONG).show();
            }

        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void updateMahasiswa(final ModelMahasiswa modelMahasiswa){
        new AsyncTask<Void,Void,Long>(){

            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.mahasiswaDao().updateMahasiswa(modelMahasiswa);
                return status;
            }
            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                Toast.makeText(getApplicationContext(), "Data diupdate",Toast.LENGTH_LONG).show();
            }

        }.execute();
    }
}
