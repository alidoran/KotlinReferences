package alidoran.android.compose.advance.datasource

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

data class User(val id: String, val name: String, val email: String)

interface UserDataSource {
    fun getUserById(id: String): User?
    fun getAllUsers(): List<User>
    fun addUser(user: User)
    fun updateUser(user: User)
    fun deleteUser(id: String)
}

@Composable
fun UserListDataSourceScreen(dataSource: UserDataSource) {
    var users by remember { mutableStateOf(dataSource.getAllUsers()) }

    Column {
        UserList(users = users)
        AddUserForm(onAddUser = {
            dataSource.addUser(it)
            users = dataSource.getAllUsers()
        })
    }
}

@Composable
fun UserList(users: List<User>) {
    Column {
        for (user in users) {
            UserListItem(user = user)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserListItem(user: User) {
    ListItem(
        icon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
        text = { Text(user.name) },
        secondaryText = { Text(user.email) }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddUserForm(onAddUser: (User) -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.padding(16.dp),
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onAddUser(User(id = System.currentTimeMillis().toString(), name = name, email = email))
                    name = ""
                    email = ""
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
        )

        Button(
            onClick = {
                onAddUser(User(id = System.currentTimeMillis().toString(), name = name, email = email))
                name = ""
                email = ""
                keyboardController?.hide()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
            Text("Save")
        }
    }
}