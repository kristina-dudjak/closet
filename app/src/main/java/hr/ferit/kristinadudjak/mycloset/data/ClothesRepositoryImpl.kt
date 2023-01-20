package hr.ferit.kristinadudjak.mycloset.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import hr.ferit.kristinadudjak.mycloset.data.models.Clothing
import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesCategory
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ClothesRepositoryImpl @Inject constructor() : ClothesRepository {
    private val db = Firebase.firestore
    private val user get() = FirebaseAuth.getInstance().currentUser

    override suspend fun saveClothing(clothing: Clothing) {
        user?.let { user ->
            db.collection("users/${user.uid}/closet").add(clothing).await()
        }
    }

    override suspend fun getClothes(): Flow<Map<ClothesCategory, List<Clothing>>> {
        return user?.let { user ->
            db.collection("users/${user.uid}/closet")
                .snapshotFlow()
                .map { snapshot ->
                    snapshot.toObjects<Clothing>()
                        .groupBy { it.category }
                }
        } ?: emptyFlow()
    }
}

fun Query.snapshotFlow(): Flow<QuerySnapshot> = callbackFlow {
    val listenerRegistration = addSnapshotListener { value, error ->
        if (error != null) throw error
        if (value != null) trySend(value)
    }
    awaitClose {
        listenerRegistration.remove()
    }
}