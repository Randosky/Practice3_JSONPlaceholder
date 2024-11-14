package com.ovinkin.practice3_jsonplaceholder.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ovinkin.practice3_jsonplaceholder.presentation.viewModel.PostsViewModel

@Composable
fun SettingsScreen(
    postsViewModel: PostsViewModel
) {
    var userName by remember { mutableStateOf(TextFieldValue("")) }
    var postContent by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Filter Settings", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = userName,
            onValueChange = {
                userName = it
                postsViewModel.filterPosts(userName.text, postContent.text)
            },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = postContent,
            onValueChange = {
                postContent = it
                postsViewModel.filterPosts(userName.text, postContent.text)
            },
            label = { Text("Post Content") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            postsViewModel.filterPosts(
                userName.text.takeIf { it.isNotBlank() },
                postContent.text.takeIf { it.isNotBlank() }
            )
        }) {
            Text("Apply Filters")
        }
    }
}
