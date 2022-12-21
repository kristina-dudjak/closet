package hr.ferit.kristinadudjak.mycloset.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.ferit.kristinadudjak.mycloset.data.UserRepository
import javax.inject.Inject

@HiltViewModel
class AppActivityViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    fun logOutUser() {
        userRepository.logOut()
    }
}