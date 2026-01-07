package com.vedanthavv.codevault.data.remote.model

import com.google.firebase.firestore.FirebaseFirestore
import com.vedanthavv.codevault.data.local.entity.Snippet
import com.google.firebase.firestore.QuerySnapshot
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseSeeder(private val firestore: FirebaseFirestore) {

    suspend fun fetchInitialSnippets(): Result<List<Snippet>> {
        return try {
            val snapshot = awaitGetSnippets()
            val snippets = snapshot.documents.mapNotNull { it.toObject(FirebaseSnippet::class.java)?.toSnippet() }
            Result.success(snippets)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun awaitGetSnippets(): QuerySnapshot = suspendCoroutine { cont ->
        firestore.collection("snippets").get()
            .addOnSuccessListener { snapshot ->
                cont.resume(snapshot)
            }
            .addOnFailureListener { exception ->
                cont.resumeWithException(exception)
            }
    }
}
