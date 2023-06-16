package com.atb.todo.ui.reusable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun TransparentTextField(
    modifier: Modifier = Modifier,
    hint: String,
    text: String,
    textStyle: TextStyle,
    singleLine: Boolean = false,
    onTextChange: (String) -> Unit
) {
    var isFocused by remember {
        mutableStateOf(false)
    }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier.fillMaxWidth().padding(12.dp)
    ) {
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            textStyle = textStyle,
            singleLine = singleLine,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isFocused = it.isFocused
                }
        )
        if (!isFocused && text.isBlank()) {
            Text(
                text = hint,
                style = textStyle,
                color = Color.LightGray
            )
        }
    }
}
