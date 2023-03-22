package com.blog.search.handler

import com.blog.search.exception.ErrorException
import com.blog.search.model.SearchDTO
import com.blog.search.model.SearchResultDTO
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class BlogApi {

    private val log = LoggerFactory.getLogger(BlogApi::class.java)

    private val client: OkHttpClient = OkHttpClient()

    @Value("\${kakao.key}")
    lateinit var kakoKey: String

    private val KAKAO_URL = "https://dapi.kakao.com/v2/search/web"

    /**
     * 카카오에서 블로그 검색
     * @param searchDTO SearchDTO
     * @return response.documents String
     */
    fun searchByKakao(searchDTO: SearchDTO): SearchResultDTO {

        val gson: Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        client.newCall(
            Request.Builder()
                .url("$KAKAO_URL?sort=${searchDTO.sort}&page=${searchDTO.page}&size=${searchDTO.size}&query=${searchDTO.query}")
                .header("Authorization", "KakaoAK $kakoKey")
                .build()
        ).execute().use { response ->
            if (!response.isSuccessful) {
                log.error("http error : {}", response.message)
                throw ErrorException("Error", response.message)
            } else {
                return gson.fromJson(response.body?.string(), SearchResultDTO::class.java)
            }
        }
    }

}