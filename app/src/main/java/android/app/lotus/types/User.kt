package android.app.lotus.types

data class User (
    val fullName: String,
    val email: String,
    val phone: String
)

val exampleUser = User(
    fullName = "Hanna Brown",
    email = "hanna.br@gmail.com",
    phone = "+4689785962"
)

