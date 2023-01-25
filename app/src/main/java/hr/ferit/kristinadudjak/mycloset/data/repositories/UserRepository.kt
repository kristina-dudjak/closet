package hr.ferit.kristinadudjak.mycloset.data.repositories

interface UserRepository {

    fun saveUserToDb()

    fun logOut()
}