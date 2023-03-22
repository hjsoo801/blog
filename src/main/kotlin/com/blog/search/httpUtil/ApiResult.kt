package com.blog.search.httpUtil

data class ApiResult<T> (
    val status: ResultCode,
    val resultData: T?,
    val resultCode: String = "",
    val resultMessage: String = ""
)
