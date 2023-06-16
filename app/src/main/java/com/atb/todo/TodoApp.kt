package com.atb.todo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.atb.todo.ui.navigation.TodoNavHost

@Composable
fun TodoApp(
    modifier: Modifier = Modifier
) {
    TodoNavHost(modifier = modifier)
}