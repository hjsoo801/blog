package com.blog.search.model

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "tb_search_keyword")
@DynamicInsert
@DynamicUpdate
data class SearchKeyword (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Int? = null,

    @Column(name = "keyword")
    val keyword: String,

    @Column(name = "search_count")
    var searchCount: Int = 0,
)