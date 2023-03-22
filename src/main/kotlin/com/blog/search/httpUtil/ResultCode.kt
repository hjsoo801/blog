package com.blog.search.httpUtil

enum class ResultCode (
    val status: Int,
    val code: String,
    val msg: String
) {

    SUCCESS(200, "200", ""),
    FAIL(400, "400", "잘못된 요청입니다."),
    ERROR(500, "500", "서버 에러"),
}
