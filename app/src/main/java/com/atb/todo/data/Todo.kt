package com.atb.todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Todo(
    @PrimaryKey
    val id: Int = 0,
    val title: String,
    val description: String,
    var isCompleted: Boolean = false
)
