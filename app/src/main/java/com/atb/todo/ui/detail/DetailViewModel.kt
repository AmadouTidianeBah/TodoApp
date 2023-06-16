package com.atb.todo.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.atb.todo.data.Graph
import com.atb.todo.data.Todo
import com.atb.todo.data.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class DetailViewModel(
    id: Int,
    private val todoRepository: TodoRepository = Graph.todoRepository
) : ViewModel() {
    private val title = MutableStateFlow("")
    private val description = MutableStateFlow("")
    private val todoId = MutableStateFlow(-1)
    private var _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState>
        get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            combine(title, description, todoId) {title, description, todoId ->
                DetailState(todoId, title, description)
            }.collect {
                _state.value = it
            }
        }
    }

    init {
        viewModelScope.launch {
            todoRepository.getAllTodo().collect { todos ->
                todos.find { todo ->
                    todo.id == todoId.value
                }.also {
                    todoId.value = it?.id ?: -1
                    if (todoId.value != -1) {
                        title.value = it?.title ?: ""
                        description.value = it?.description ?: ""
                    }
                }
            }
        }
    }

    fun updateTitle(title: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(title = title)
        }
    }

    fun updateDescription(description: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(description = description)
        }
    }

    fun insertTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.insertTodo(todo)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val id: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(id = id) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}

data class DetailState(
    val id: Int = -1,
    val title: String = "",
    val description: String = ""
)