package com.blog.search.controller

import com.blog.search.httpUtil.ApiResponseUtils
import com.blog.search.model.SearchDTO
import com.blog.search.service.BlogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/api/v1")
@RestController
class SearchController {
    @Autowired
    lateinit var blogService: BlogService

    @GetMapping("search")
    fun search(@ModelAttribute searchDTO: SearchDTO): ResponseEntity<Any> {
        return ApiResponseUtils.success(blogService.search(searchDTO))
    }

    @GetMapping("pop")
    fun getPopularKeywordList(): ResponseEntity<Any> {
        return ApiResponseUtils.success(blogService.getPopularKeywordList())
    }
}