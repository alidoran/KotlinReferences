package alidoran.android.compose.advance.datasource

class InMemoryUserDataSource : UserDataSource {
    private val users = mutableListOf<User>()

    override fun getUserById(id: String): User? {
        return users.find { it.id == id }
    }

    override fun getAllUsers(): List<User> {
        return users.toList()
    }

    override fun addUser(user: User) {
        users.add(user)
    }

    override fun updateUser(user: User) {
        val index = users.indexOfFirst { it.id == user.id }
        if (index != -1) {
            users[index] = user
        }
    }

    override fun deleteUser(id: String) {
        users.removeIf { it.id == id }
    }
}