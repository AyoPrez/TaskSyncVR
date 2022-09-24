package com.ayoprez.tasksyncvr.viemodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.ayoprez.tasksyncvr.database.NumbersDatabase
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class MainViewModel constructor(context: Context) : ViewModel() {

    private val db = Room
        .databaseBuilder(
            context.applicationContext,
            NumbersDatabase::class.java, "fibonacci_history"
        )
        .build().storedNumbersDao()

    val storedNumbers: LiveData<List<StoredNumbers>> = liveData {
        val data = db.getAll()
        emit(data)
    }

    fun getRandomFibonacciNumber(): Int {
        val random = (0..10000).random()
        val result = fibonacci(random)
        storeRequestedNumber(result)

        return result
    }

    private fun storeRequestedNumber(number: Int) {
        viewModelScope.launch {
            val result = number.toStoredNumber()
            db.insert(result)
        }
    }
}

//Extension Functions
fun Int.toStoredNumber(): StoredNumbers {
    return StoredNumbers(
        number = this,
        date = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
    )
}

fun Long.toLocalDateTime(): LocalDateTime {
    return Instant.ofEpochSecond(this).atOffset(ZoneOffset.UTC).toLocalDateTime()
}