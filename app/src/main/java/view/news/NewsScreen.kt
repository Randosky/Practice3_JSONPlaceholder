package view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import viewModel.NewsViewModel

@Composable
fun NewsScreen(viewModel: NewsViewModel) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Страница новостей")
    }
}