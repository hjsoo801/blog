package com.blog.search.exception


import com.blog.search.httpUtil.ApiResponseUtils
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestControllerAdvice
class ExceptionHandler {

    /**
     *  request 조건에 따라 error / fail 처리 하는 함수
     * @param request : HttpServletRequest
     * @param response: HttpServletResponse
     * @param exception: Exception
     * @return T (ApiResponseUtils or ModelAndView)
     */
    @ExceptionHandler(Exception::class)
    fun exceptionHandler(request: HttpServletRequest, response: HttpServletResponse, exception: Exception): Any {
//        return if( isApiRequest(request) ) {
            return if( exception::class == ErrorException::class ) {
                ApiResponseUtils.fail(exception as ErrorException)

            } else if( exception.message != null ) {
                ApiResponseUtils.fail(exception.message!!)
            } else {
                ApiResponseUtils.fail()
            }
//        } else {
//            //json 이 아닐경우 error page 로 이동
//            //TODO CHECK : error page
//            ModelAndView("/error/" + response.status)
//        }
    }

    fun isApiRequest(request: HttpServletRequest): Boolean {
        val accept = request.getHeader("accept")
        val apiType = request.getHeader("x-requested-with")
        val contentType = request.getHeader("content-type")
        val jsonReg = Regex(".*application/json.*")

        return accept != null && jsonReg.matches(accept)
                || apiType != null && apiType.equals("XMLHttpRequest")
                || contentType != null && jsonReg.matches(contentType)
    }
}
