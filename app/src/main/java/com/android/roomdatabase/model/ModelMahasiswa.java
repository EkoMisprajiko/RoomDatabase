package com.android.roomdatabase.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//membuat tabel database tb_mahaiswa
@Entity(tableName = "tb_mahasiswa")

public class ModelMahasiswa implements Serializable {

    @PrimaryKey(autoGenerate = true)//membuat id autoincrement dan sebagai primary key
    int id;
    @ColumnInfo(name = "nim")//membuat kolom nim
    int nim;
    @ColumnInfo(name = "nama")//membuat kolom nama
    String nama;
    @ColumnInfo(name = "alamat")//membuat kolom alamat
    String alamat;

    public ModelMahasiswa(int nim, String nama, String alamat) {
        this.nim = nim;
        this.nama = nama;
        this.alamat = alamat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNim() {
        return nim;
    }

    public void setNim(int nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

}
