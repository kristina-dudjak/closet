package hr.ferit.kristinadudjak.mycloset.data

interface UserRepository {

    fun saveUserToDb()

    fun logOut()
}