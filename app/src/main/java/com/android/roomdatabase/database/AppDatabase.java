package com.android.roomdatabase.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.android.roomdatabase.dao.MahasiswaDao;
import com.android.roomdatabase.model.ModelMahasiswa;

@Database(entities = ModelMahasiswa.class,version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MahasiswaDao mahasiswaDao();

}

/*
abstract class adalah kelas yang didalamnya ada objek namun tidak diimplementasikan
*/
