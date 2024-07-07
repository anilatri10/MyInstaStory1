package com.example.myinstastory.utils.data

data class StoryItem(
    val imageUrl: String = "",
    val name: String = "",
    val src: Int = -1
)


fun StoryItem.getResource(): Any {
    return if (this.imageUrl.isNotEmpty()) {
        imageUrl
    } else {
        return if (this.src != -1) {
             this.src
        } else {
            ""
        }
    }
}
