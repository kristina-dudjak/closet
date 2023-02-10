package hr.ferit.kristinadudjak.mycloset.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import hr.ferit.kristinadudjak.mycloset.data.models.Clothing
import hr.ferit.kristinadudjak.mycloset.data.models.Combination
import hr.ferit.kristinadudjak.mycloset.data.models.CombinationFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class CombinationsRepositoryImpl @Inject constructor(
    private val clothesRepository: ClothesRepository
) : CombinationsRepository {
    private val db = Firebase.firestore
    private val user get() = FirebaseAuth.getInstance().currentUser

    override suspend fun saveCombination(combination: Combination) {
        user?.let { user ->
            val combinationToStore = if (combination.id == "") combination.copy(
                id = UUID.randomUUID().toString()
            ) else combination
            val clothingIds = combinationToStore.clothes.map { it.id }
            db.collection("users/${user.uid}/combinations").document(combinationToStore.id)
                .set(
                    mapOf(
                        "id" to combinationToStore.id,
                        "clothes" to clothingIds
                    )
                ).await()
        }
    }

    override suspend fun deleteCombination(combination: Combination) {
        user?.let { user ->
            db.document("users/${user.uid}/combinations/${combination.id}").delete()
        }
    }

    override suspend fun deleteClothingFromCombination(
        combination: Combination,
        clothing: Clothing
    ) {
        if (combination.clothes.size == 1) deleteCombination(combination)
        else {
            user?.let { user ->
                db.document("users/${user.uid}/combinations/${combination.id}")
                    .update("clothes", FieldValue.arrayRemove(clothing.id))
            }
        }
    }

    override suspend fun getCombinations(): Flow<List<Combination>> {
        return user?.let { user ->
            db.collection("users/${user.uid}/combinations")
                .snapshotFlow()
                .map { snapshot ->
                    val rawCombinations = snapshot.toObjects<CombinationFirestore>()
                    rawCombinations.map { comb ->
                        val clothes = clothesRepository.getClothes(comb.clothes).first()
                        Combination(
                            id = comb.id,
                            clothes = clothes
                        )
                    }
                }
        } ?: emptyFlow()
    }

    override suspend fun getCombination(id: String): Flow<Combination?> {
        return user?.let { user ->
            db.collection("users/${user.uid}/combinations")
                .whereEqualTo("id", id)
                .snapshotFlow()
                .map { snapshot ->
                    val rawCombination = snapshot.firstOrNull()?.toObject<CombinationFirestore>()
                    rawCombination?.let { comb ->
                        val clothes = clothesRepository.getClothes(comb.clothes).first()
                        Combination(
                            id = id,
                            clothes = clothes
                        )
                    }
                }
        } ?: emptyFlow()
    }

//    override suspend fun getMostWornClothing(): String {
//        val combinations = getCombinations().first()
//        val allClothes = combinations.flatMap { it.clothes }
//        val clothesById = allClothes.groupBy { it.id }
//        return clothesById.maxBy { it.value.size }.key
//    }
}