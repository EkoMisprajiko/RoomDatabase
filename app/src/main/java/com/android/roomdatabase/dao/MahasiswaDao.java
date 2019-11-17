package com.android.roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.android.roomdatabase.model.ModelMahasiswa;

import java.util.List;

//DAO = Data A Objek
@Dao
public interface MahasiswaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMahasiswa(ModelMahasiswa modelMahasiswa);

    @Query("SELECT * FROM tb_mahasiswa")
    List<ModelMahasiswa> getMahasiswa();

    @Update
    int updateMahasiswa(ModelMahasiswa modelMahasiswa);

    @Delete
    int deleteMahasiswa(ModelMahasiswa modelMahasiswa);

    @Query("SELECT * FROM tb_mahasiswa WHERE id= :id")
    ModelMahasiswa detailMahasiswa(int id);

}

/*
interface hanya digunakan untuk menyimpan method
tapi tidak bisa membuat data
*/
