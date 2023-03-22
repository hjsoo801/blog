package com.blog.search.exception

data class ErrorException(
    val code: String, override val message: String? = null
): Exception(code, Throwable(message)) {

    constructor(code: ErrorCode) : this(code.code, code.message)
}
