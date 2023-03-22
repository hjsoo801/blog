package com.blog.search.handler

import com.blog.search.exception.ErrorException
import com.blog.search.model.SearchDTO
import com.blog.search.model.SearchResultDTO
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class BlogApiTest{

    private val CLIENT: OkHttpClient = OkHttpClient()
    private val kakoKey: String = "6973a5e211f6474271af4f754089a137"
    private val FAIL_KEY: String = ""
    private val KAKAO_URL: String = "https://dapi.kakao.com/v2/search/web"
    private lateinit var searchData: SearchDTO
    private lateinit var gson: Gson

    @BeforeEach
    fun setUp(){
        searchData = SearchDTO(query = "카카오")
        gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }
    
    @Test
    @DisplayName("기본 값으로 검색 성공 시 meta 값과 documents 값을 반환해야 한다.")
    fun successAPI(){
        val searchResult = CLIENT.newCall(
            Request.Builder()
                .url("$KAKAO_URL?sort=${searchData.sort}&page=${searchData.page}&size=${searchData.size}&query=${searchData.query}")
                .header("Authorization", "KakaoAK $kakoKey")
                .build()
        ).execute().use { response ->
            if(!response.isSuccessful) throw ErrorException("Kakao API Error")
            gson.fromJson(response.body?.string(), SearchResultDTO::class.java)
        }

        assertEquals(false, searchResult.meta.isEnd)
    }


    @Test
    @DisplayName("잘못 된 API key로 검색 시 에러를 반환해야 한다.")
    fun throwErrorException(){
        assertThrows(ErrorException::class.java) { CLIENT.newCall(
            Request.Builder()
                .url("$KAKAO_URL?sort=${searchData.sort}&page=${searchData.page}&size=${searchData.size}&query=${searchData.query}")
                .header("Authorization", "KakaoAK $FAIL_KEY")
                .build()
        ).execute().use { response ->
            if(!response.isSuccessful) throw ErrorException("Error", response.message )
            gson.fromJson(response.body?.string(), SearchResultDTO::class.java)
        }}
    }

}