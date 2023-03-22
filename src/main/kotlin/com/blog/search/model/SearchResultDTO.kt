package com.blog.search.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SearchResultDTO(
    val meta: Meta,
    val documents: List<Documents>?
)

data class Meta(
    val totalCount: Int,
    val pageableCount: Int,
    @get:JsonProperty("isEnd")
    val isEnd: Boolean,
)

data class Documents(
    val title: String,
    val contents: String,
    val url: String,
    val blogname: String,
    val thumbnail: String,
    val datetime: String,
)