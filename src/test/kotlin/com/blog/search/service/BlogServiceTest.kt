package com.blog.search.service

import com.blog.search.exception.ErrorException
import com.blog.search.model.SearchDTO
import com.blog.search.model.SearchKeyword
import com.blog.search.repository.SearchKeywordRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any

@ExtendWith(MockitoExtension::class)
@DisplayName("MemberService 테스트")
internal class BlogServiceTest{

    @Mock
    private lateinit var searchKeywordRepository: SearchKeywordRepository

    @InjectMocks
    private lateinit var blogService: BlogService

    private val saveResult: SearchKeyword = SearchKeyword(keyword = "kakao")
    @BeforeEach
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        `when`(searchKeywordRepository.save(any())).thenReturn(saveResult)
    }

    @Test
    @DisplayName("사이즈가 max 값 보다 클 경우, ErrorException 반환")
    fun outOfRangeSizeValue() {
        val searchDTO = SearchDTO(page = 1, size = 51, sort = "recency", query = "kakao")
        Assertions.assertThrows(ErrorException::class.java) { blogService.search(searchDTO) }
    }

    @Test
    @DisplayName("정렬 value 가 유효하지 않을 경우, ErrorException 반환")
    fun invalidSortValue() {
        val searchDTO = SearchDTO(sort = "invalidSort", query = "kakao")
        Assertions.assertThrows(ErrorException::class.java) { blogService.search(searchDTO) }
    }
    @Test
    @DisplayName("페이지가 max 값 보다 클 경우, ErrorException 반환")
    fun outOfRangePageValue() {
        val searchDTO = SearchDTO(page = 51, query = "kakao")
        Assertions.assertThrows(ErrorException::class.java) { blogService.search(searchDTO) }
    }
}