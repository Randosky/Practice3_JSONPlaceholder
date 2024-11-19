import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ovinkin.practice3_jsonplaceholder.presentation.viewModel.SettingsViewModel

@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel,
    navController: NavHostController,
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    var userName by remember { mutableStateOf(TextFieldValue(settingsViewModel.userNameFilter)) }
    var postContent by remember { mutableStateOf(TextFieldValue(settingsViewModel.postContentFilter)) }

    LaunchedEffect(Unit) {
        settingsViewModel.getSettings()
    }

    LaunchedEffect(settingsViewModel.userNameFilter) {
        userName = TextFieldValue(settingsViewModel.userNameFilter)
    }

    LaunchedEffect(settingsViewModel.postContentFilter) {
        postContent = TextFieldValue(settingsViewModel.postContentFilter)
    }


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Настройки фильтрации", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = userName,
            onValueChange = {
                userName = it
            },
            label = { Text("По имени автора") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = postContent,
            onValueChange = {
                postContent = it
            },
            label = { Text("По содержанию поста") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            settingsViewModel.setSettings(userName.text, postContent.text)
            keyboardController?.hide()
            navController.navigate("posts")
        }) {
            Text("Фильтровать")
        }

        if (settingsViewModel.userNameFilter.isNotEmpty() || settingsViewModel.postContentFilter.isNotEmpty()) {
            Button(onClick = {
                settingsViewModel.resetSettings()
                keyboardController?.hide()
            }) {
                Text("Сбросить фильтры")
            }
        }

    }
}
