package com.blog.search.repository

import com.blog.search.model.SearchKeyword
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest


@DataJpaTest
class SearchKeywordRepositoryTest {
    @Autowired
    lateinit var searchKeywordRepository: SearchKeywordRepository
    private val keyword: String = "kakao"
    private val searchKeyword: SearchKeyword = SearchKeyword(keyword = keyword, searchCount = 1)

    @BeforeEach
    fun setUp(){

    }

    @Test
    @DisplayName("searchKeyword 테스트")
    fun save() {
        val savedKeyword: SearchKeyword = searchKeywordRepository.save(searchKeyword)
        Assertions.assertNotNull(savedKeyword)
        Assertions.assertEquals(searchKeyword.keyword, savedKeyword.keyword)
        Assertions.assertEquals(savedKeyword.keyword, keyword)
    }

    @Test
    @DisplayName("인기 검색어 목록 top10 조회 테스트")
    fun findTop10ByOrderBySearchCountDesc(){
        val result = searchKeywordRepository.findTop10ByOrderBySearchCountDesc()
        Assertions.assertNotNull(result)


    }

}