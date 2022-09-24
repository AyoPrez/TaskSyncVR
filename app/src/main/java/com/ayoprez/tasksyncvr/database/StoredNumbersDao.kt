package com.ayoprez.tasksyncvr.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ayoprez.tasksyncvr.viemodel.StoredNumbers

@Dao
interface StoredNumbersDao {
    @Query("SELECT * FROM storednumbers")
    suspend fun getAll(): List<StoredNumbers>

    @Insert
    suspend fun insert(number: StoredNumbers)
}