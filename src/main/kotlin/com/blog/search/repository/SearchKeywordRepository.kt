package com.blog.search.repository

import com.blog.search.model.SearchKeyword
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SearchKeywordRepository: JpaRepository<SearchKeyword, Int> {
    fun findTop10ByOrderBySearchCountDesc(): List<SearchKeyword>
    fun findByKeyword(query: String): SearchKeyword?
}