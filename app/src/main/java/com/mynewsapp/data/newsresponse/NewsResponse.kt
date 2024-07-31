package com.mynewsapp.data.newsresponse

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
) {
    companion object {
        fun noResponse() : NewsResponse {
            return NewsResponse(
                status = "",
                totalResults = 0,
                articles = emptyList()
            )
        }
    }
}