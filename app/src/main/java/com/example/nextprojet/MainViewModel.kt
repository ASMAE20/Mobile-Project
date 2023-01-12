package com.example.nextprojet

import com.example.nextprojet.folder_data.supprimer.Supprimer
import com.example.nextprojet.folder_data.data.PostPreview
import com.example.nextprojet.folder_data.data.PostData
import com.example.nextprojet.folder_data.data.DataResponse
import com.example.nextprojet.repository.Poste_Repository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

//initialisation les fcts de api
class MainViewModel(private val repository: Poste_Repository): ViewModel() {
    val responseData: MutableLiveData<Response<DataResponse>> = MutableLiveData()
    val responsePoste: MutableLiveData<Response<PostData>> = MutableLiveData()
    val responseSupprimer: MutableLiveData<Response<Supprimer>> = MutableLiveData()
    val ResponseCreer: MutableLiveData<Response<PostPreview>> = MutableLiveData()


    fun afficherPosts(pageNumber: Int, postsNumber: Int) {
        viewModelScope.launch {
            val responses = repository.afficherPosts(pageNumber, postsNumber)
            responseData.value = responses
        }
    }

    fun afficherPost(postId: String) {
        viewModelScope.launch {
            val response = repository.afficherPost(postId)
            responsePoste.value = response
        }
    }

    fun afficher_by_tag(tag: String, postsNumber: Int) {
        viewModelScope.launch {
            val response = repository.afficherByTag(tag, postsNumber)
            responseData.value = response
        }
    }

    fun supprimerPoste(postId: String, title: String) {
        viewModelScope.launch {
            val response = repository.supprimerPoste(postId)
            responseSupprimer.value = response
        }
    }

    fun creerPoste(text: String, image: String, likes: Int, tags: ArrayList<String>, owner: String) {
        viewModelScope.launch {
            val response = repository.creerPoste(text, image,likes, tags ,owner)
            ResponseCreer.value = response
        }
    }
}

