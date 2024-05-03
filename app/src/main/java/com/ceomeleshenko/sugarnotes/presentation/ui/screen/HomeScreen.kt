package com.ceomeleshenko.sugarnotes.presentation.ui.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.NoteViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.context.startKoin

@Composable
fun HomeScreen(
//    viewModel: NoteViewModel = koinViewModel()
) {
    Row {
        Button(onClick = {
//            viewModel.insertNote()
        }) {
            Text(text = "Add note")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
