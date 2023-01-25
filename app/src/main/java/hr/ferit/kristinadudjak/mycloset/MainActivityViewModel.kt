package hr.ferit.kristinadudjak.mycloset

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.ferit.kristinadudjak.mycloset.data.repositories.UserRepository
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    fun saveUser() {
        userRepository.saveUserToDb()
    }
}