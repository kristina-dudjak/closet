package hr.ferit.kristinadudjak.mycloset.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import hr.ferit.kristinadudjak.mycloset.data.models.Clothing
import javax.inject.Inject

class ClothesRepositoryImpl @Inject constructor() : ClothesRepository {
    private val db = Firebase.firestore
    private val user get() = FirebaseAuth.getInstance().currentUser

    override suspend fun saveClothing(clothing: Clothing) {
        user?.let { user ->
            db.collection("users/${user.uid}/closet").add(clothing)
        }
    }
}