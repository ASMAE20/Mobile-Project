package com.example.nextprojet.folder_api




import com.example.nextprojet.folder_data.supprimer.Supprimer
import com.example.nextprojet.folder_data.data.PostPreview
import com.example.nextprojet.folder_data.data.PostData
import com.example.nextprojet.folder_data.data.DataResponse
import retrofit2.Response
import retrofit2.http.*

// all requests

interface PosteService {
    //la requete de l'affichage
    @Headers("app-id: 623c2f76f3148f34f18ed59b")
    @GET("post")

    suspend fun afficherPosts(
        @Query("pages") nbrpage: Int,
        @Query("limits") nbrposte: Int
    ): Response<DataResponse>

    @Headers("app-id: 623c2f76f3148f34f18ed59b")
    @GET("post/{post}")
    suspend fun afficherPost(
        @Path("post") idpost: String
    ): Response<PostData>


    @Headers("app-id: 623c2f76f3148f34f18ed59b")
    @GET("tag/{tag}/post")
    suspend fun afficherByTag(
        @Path("tag") tag: String,
        @Query("limit") nbrpost: Int,

    ): Response<DataResponse>


    //la requete de création de poste
    @FormUrlEncoded
    @Headers("app-id: 623c2f76f3148f34f18ed59b")
    @POST("post/create")
    suspend fun creerPoste(
        @Field("text") text: String,
        @Field("image") image: String,
        @Field("likes") likes: Int,
        @Field("tags") tags: ArrayList<String>,
        @Field("owner") owner: String,
    ): Response<PostPreview>


    //la requete de suppression
    @Headers("app-id: 623c2f76f3148f34f18ed59b")
    @DELETE("post/{post}")
    suspend fun supprimerPoste(
        @Path("post") idpost: String
    ): Response<Supprimer>



}




