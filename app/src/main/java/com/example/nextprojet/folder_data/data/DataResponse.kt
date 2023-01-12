package com.example.nextprojet.folder_data.data
//les données de postes
data class DataResponse (
    val data : ArrayList<PostPreview>,
    val total: Int,
    val page: Int,
    val limit: Int
)