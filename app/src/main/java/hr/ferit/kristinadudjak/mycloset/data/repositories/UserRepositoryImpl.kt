package hr.ferit.kristinadudjak.mycloset.data.repositories

import android.content.Context
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : UserRepository {
    private val db = Firebase.firestore
    private val user get() = FirebaseAuth.getInstance().currentUser

    override fun saveUserToDb() {
        user?.let { user ->
            db.collection("users").document(user.uid).set(
                mapOf(
                    "uid" to user.uid,
                    "email" to user.email
                )
            )
        }
    }

    override fun logOut() {
        AuthUI.getInstance().signOut(context)
    }
}