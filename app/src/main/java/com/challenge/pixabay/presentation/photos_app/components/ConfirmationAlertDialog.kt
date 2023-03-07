package com.challenge.pixabay.presentation.photos_app.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ConfirmationAlertDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onConfirm) { Text(text = "OK") }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) { Text(text = "Cancel") }
            },
            title = { Text(text = "Please confirm") },
            text = { Text(text = "Do you want to view the details?") }
        )
    }
}
