package com.example.nextprojet.folder_data.data
//donné de postpreview
data class PostPreview (
    val id: String,
    val image: String,
    val likes: Int,
    val tags: ArrayList<String>,
    val text: String,
    val publishDate: String,
    val owner: Owner
)