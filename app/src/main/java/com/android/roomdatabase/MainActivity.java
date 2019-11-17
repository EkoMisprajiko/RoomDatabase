package com.android.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.roomdatabase.lihat_data.LihatDataActivity;
import com.android.roomdatabase.tambah_data.TambahDataActivity;

public class MainActivity extends AppCompatActivity {

    Button btnTambah, btnLihat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTambah = findViewById(R.id.btn_tambah);
        btnLihat  = findViewById(R.id.btn_lihat);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahData();
            }
        });

        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lihatData();
            }
        });

    }

    private void tambahData() {
        Intent intent_tambah = new Intent(getApplicationContext(), TambahDataActivity.class);
        startActivity(intent_tambah);
    }

    private void lihatData() {
        Intent intent_lihat = new Intent(getApplicationContext(), LihatDataActivity.class);
        startActivity(intent_lihat);
    }
}
