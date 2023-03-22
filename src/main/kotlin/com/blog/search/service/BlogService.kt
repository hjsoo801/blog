package com.blog.search.service

import com.blog.search.exception.ErrorException
import com.blog.search.handler.BlogApi
import com.blog.search.model.SearchDTO
import com.blog.search.model.SearchKeyword
import com.blog.search.model.SearchResultDTO
import com.blog.search.repository.SearchKeywordRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
class BlogService {
    private val log = LoggerFactory.getLogger(BlogService::class.java)

    @Autowired
    lateinit var searchKeywordRepository: SearchKeywordRepository

    @Autowired
    lateinit var blogApi: BlogApi


    fun search(searchDTO: SearchDTO): SearchResultDTO {
        val sortList = listOf("recency", "accuracy")
        upsertSearchKeyword(searchDTO)
        when (true) {
            (searchDTO.page > 50) -> throw ErrorException("out of range", "page 가 최대 값보다 큽니다(최대 50)")
            (searchDTO.size > 50) -> throw ErrorException("out of range", "size 가 최대 값보다 큽니다(최대 50)")
            (sortList.none { it == searchDTO.sort }) -> throw ErrorException(
                "invalid parameter",
                "잘못 된 param 입니다(사용가능 param : recency, accuracy)"
            )
            else -> {
                return blogApi.searchByKakao(searchDTO)
            }
        }
    }

    @Transactional
    fun upsertSearchKeyword(searchDTO: SearchDTO) {
        val result = searchKeywordRepository.findByKeyword(searchDTO.query) ?: SearchKeyword(keyword = searchDTO.query)
        result.apply {
            searchCount += 1
        }.run {
            searchKeywordRepository.save(this)
        }
    }

    fun getPopularKeywordList(): List<SearchKeyword> {
        return searchKeywordRepository.findTop10ByOrderBySearchCountDesc()
    }

}