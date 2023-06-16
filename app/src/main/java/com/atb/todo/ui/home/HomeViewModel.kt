package com.atb.todo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atb.todo.data.Graph
import com.atb.todo.data.Todo
import com.atb.todo.data.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class HomeViewModel(private val todoRepository: TodoRepository = Graph.todoRepository): ViewModel() {
    private var _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState>
        get() = _state.asStateFlow()

    val todos = todoRepository.getAllTodo()
    val isCompleted = MutableStateFlow(_state.value.isCompleted)

    init {
        viewModelScope.launch {
            combine(todos, isCompleted) {todos: List<Todo>, isCompleted: Boolean ->
                HomeState(todos, isCompleted)
            }.collect {
                _state.value = it
            }
        }
    }

    fun updateTodo(id: Int, isCompleted: Boolean) {
        viewModelScope.launch {
            todoRepository.updateTodo(id, isCompleted)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.deleteTodo(todo)
        }
    }
}

data class HomeState(
    val todos: List<Todo> = emptyList(),
    val isCompleted: Boolean = false
)