package com.ayoprez.tasksyncvr.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ayoprez.tasksyncvr.viemodel.StoredNumbers

@Database(entities = [StoredNumbers::class], version = 1)
abstract class NumbersDatabase : RoomDatabase() {
    abstract fun storedNumbersDao(): StoredNumbersDao
}