package com.blog.search.model

data class SearchDTO (
    val sort: String = "accuracy",
    val page: Int = 1,
    val size: Int = 10,
    val query: String,
)