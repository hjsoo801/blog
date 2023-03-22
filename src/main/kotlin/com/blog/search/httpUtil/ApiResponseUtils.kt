package com.blog.search.httpUtil

import com.blog.search.exception.ErrorCode
import com.blog.search.exception.ErrorException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

object ApiResponseUtils {
    /**
     * 성공 프로세스에 대한 ApiResult Return
     * @return
     * @param <T>
    </T> */
    fun success(): ResponseEntity<Any>
            = success(null)

    fun <T> success(resultData: T): ResponseEntity<Any>
            = ResponseEntity(ApiResult(ResultCode.SUCCESS, resultData), HttpStatus.OK)

    /**
     * 잘못된 요청에 대한 ApiResult Return
     * @return
     * @param <T>
    </T> */
    fun fail(): ResponseEntity<Any>
            = ResponseEntity( ApiResult(ResultCode.FAIL, null), HttpStatus.valueOf(ResultCode.FAIL.status) )

    fun fail(msg: String): ResponseEntity<Any>
            = ResponseEntity( ApiResult(ResultCode.FAIL, null, resultMessage = msg), HttpStatus.valueOf(ResultCode.FAIL.status) )


    fun fail(exception: ErrorException): ResponseEntity<Any>
            = ResponseEntity( ApiResult(ResultCode.FAIL, null, exception.code, exception.message ?: ""), HttpStatus.valueOf(ResultCode.FAIL.status) )

    fun fail(errorCode: ErrorCode): ResponseEntity<Any>
            = ResponseEntity( ApiResult(ResultCode.FAIL, null, errorCode.code, errorCode.message), HttpStatus.valueOf(ResultCode.FAIL.status) )

    /**
     * 실패 프로세스에 대한 ApiResult Return
     * @return
     * @param <T>
    </T> */
    fun error(): ResponseEntity<Any>
            = ResponseEntity( ApiResult(ResultCode.ERROR, null), HttpStatus.valueOf(ResultCode.ERROR.status) )

    fun error(msg: String): ResponseEntity<Any>
            = ResponseEntity( ApiResult(ResultCode.ERROR, null, resultMessage = msg), HttpStatus.valueOf(ResultCode.ERROR.status) )


    fun error(exception: ErrorException): ResponseEntity<Any>
            = ResponseEntity( ApiResult(ResultCode.ERROR, null, exception.code, exception.message ?: ""), HttpStatus.valueOf(ResultCode.ERROR.status) )

    fun error(errorCode: ErrorCode): ResponseEntity<Any>
            = ResponseEntity( ApiResult(ResultCode.ERROR, null, errorCode.code, errorCode.message), HttpStatus.valueOf(ResultCode.ERROR.status) )
}
