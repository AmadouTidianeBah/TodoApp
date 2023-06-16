package com.atb.todo.data

import android.content.Context

object Graph {
    lateinit var database: TodoDatabase
        private set
    val todoRepository by lazy { TodoRepository(todoDao = database.getTodoDao()) }
    fun provide(context: Context) {
        database = TodoDatabase.getDatabase(context = context)
    }
}