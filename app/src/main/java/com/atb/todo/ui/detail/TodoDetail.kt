package com.atb.todo.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.atb.todo.R
import com.atb.todo.data.Todo
import com.atb.todo.ui.reusable.CircleButton
import com.atb.todo.ui.reusable.TransparentTextField


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodoDetail(
    modifier: Modifier = Modifier,
    selectedId: Int,
    onNavigate: () -> Unit
) {
    val viewModel = viewModel(
        modelClass = DetailViewModel::class.java,
        factory = DetailViewModelFactory(selectedId)
    )
    val state = viewModel.state.collectAsState()
    val isUpdateTodo = selectedId == state.value.id

    Scaffold(
        floatingActionButton = {
            val todo = if (isUpdateTodo) Todo(title = state.value.title, description = state.value.description)
            else Todo(title = state.value.title, description = state.value.description, id = selectedId)
            CircleButton(
                onButtonClick = {
                    viewModel.insertTodo(todo)
                    onNavigate()
                },
                icon = Icons.Filled.Done,
                description = "done icon"
            )
        },
        modifier = modifier
    ) {
        TodoDetailComposable(
            title = state.value.title,
            onTitleChange = { viewModel.updateTitle(it) },
            description = state.value.description,
            onDescriptionChange = { viewModel.updateDescription(it) }
        )
    }
}

@Composable
fun TodoDetailComposable(
    modifier: Modifier = Modifier,
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TransparentTextField(
            hint = stringResource(R.string.title),
            text = title,
            textStyle = MaterialTheme.typography.headlineLarge,
            singleLine = true,
            onTextChange = onTitleChange
        )
        TransparentTextField(
            hint = stringResource(R.string.description),
            text = description,
            textStyle = MaterialTheme.typography.bodyLarge,
            onTextChange = onDescriptionChange
        )
    }
}

