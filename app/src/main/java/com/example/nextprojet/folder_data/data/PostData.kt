package com.example.nextprojet.folder_data.data
//donné de postdata
data class PostData (
    val id: String,
    val image: String,
    val likes: Int,
    val link: String,
    val tags: ArrayList<String>,
    val text: String,
    val publishDate: String,
    val owner: Owner
)