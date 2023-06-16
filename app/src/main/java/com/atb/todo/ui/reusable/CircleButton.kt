package com.atb.todo.ui.reusable

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.atb.todo.ui.theme.TodoTheme

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
    icon: ImageVector,
    description: String?
) {
    IconButton(
        onClick = onButtonClick,
        modifier = modifier
            .clip(CircleShape)
            .background(Color(0xFF6200EE))
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CircleButtonPre() {
    TodoTheme {
        CircleButton(
            onButtonClick = { /*TODO*/ },
            icon = Icons.Filled.Add,
            description = null
        )
    }
}