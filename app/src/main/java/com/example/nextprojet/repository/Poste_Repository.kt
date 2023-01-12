package com.example.nextprojet.repository






import com.example.nextprojet.folder_api.Retrofit
import com.example.nextprojet.folder_data.supprimer.Supprimer
import com.example.nextprojet.folder_data.data.PostPreview
import com.example.nextprojet.folder_data.data.PostData
import com.example.nextprojet.folder_data.data.DataResponse
import retrofit2.Response

// les fcts après les appels des requetes
class Poste_Repository {

    suspend fun afficherPosts(nbrpage: Int, nbrpost: Int): Response<DataResponse> {
        return Retrofit.api.afficherPosts(nbrpage, nbrpost)
    }

    suspend fun afficherPost(nbrpost: String): Response<PostData> {
        return Retrofit.api.afficherPost(nbrpost)
    }

    suspend fun afficherByTag(tag: String, nbrpost: Int): Response<DataResponse> {
        return Retrofit.api.afficherByTag(tag, nbrpost)
    }

    suspend fun creerPoste(text: String, image: String, likes: Int, tags: ArrayList<String>, owner: String): Response<PostPreview> {
        return Retrofit.api.creerPoste(text, image,likes, tags ,owner)
    }

    suspend fun supprimerPoste(postId: String): Response<Supprimer> {
        return Retrofit.api.supprimerPoste(postId)
    }


}