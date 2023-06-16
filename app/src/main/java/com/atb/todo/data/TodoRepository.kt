package com.atb.todo.data

class TodoRepository(private val todoDao: TodoDao) {
    suspend fun insertTodo(todo: Todo) = todoDao.insert(todo)
    suspend fun deleteTodo(todo: Todo) = todoDao.delete(todo)
    suspend fun updateTodo(id: Int, isCompleted: Boolean) = todoDao.update(id, isCompleted)
    fun getAllTodo() = todoDao.getAll()
}