package com.atb.todo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("UPDATE todo SET isCompleted = :isCompleted WHERE id = :id")
    suspend fun update(id: Int, isCompleted: Boolean)

    @Query("SELECT * FROM todo")
    fun getAll(): Flow<List<Todo>>

}