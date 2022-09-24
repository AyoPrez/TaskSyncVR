package com.ayoprez.tasksyncvr.viemodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.ZoneOffset


@Entity
data class StoredNumbers(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val number: Int,
    val date: Long = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
    )