package com.ceomeleshenko.sugarnotes.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Note (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var glucose: Int = 0,
    val bread: Int = 0,
    val insulin: Int = 0,
    val dateTime: LocalDateTime = LocalDateTime.now()
)