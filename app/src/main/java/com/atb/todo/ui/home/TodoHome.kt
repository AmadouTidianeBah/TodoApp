package com.atb.todo.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.atb.todo.data.Todo
import com.atb.todo.ui.reusable.CircleButton
import com.atb.todo.ui.reusable.TodoItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoHome(
    modifier: Modifier = Modifier,
    onNavigate: (Todo?) -> Unit
) {
    val viewModel: HomeViewModel = viewModel()
    val state = viewModel.state.collectAsState()

    Scaffold(
        floatingActionButton = { CircleButton(
            onButtonClick = {onNavigate(null)},
            icon = Icons.Filled.Add,
            description = "add icon"
        ) },
        modifier = modifier
    ) {
        LazyColumn {
            items(items = state.value.todos) {todo ->
                TodoItem(
                    todo = todo,
                    onCheckedChange = { viewModel.updateTodo(todo.id, it) },
                    onDeleteClick = { viewModel.deleteTodo(it) },
                    onNavigate = { onNavigate(it) }
                )
            }
        }
    }
}
