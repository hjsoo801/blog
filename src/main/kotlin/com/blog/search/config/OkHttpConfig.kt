package com.blog.search.config

import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class OkHttpConfig {

    @Bean("okHttpClient")
    fun okHttpClient(): OkHttpClient {

        return OkHttpClient()
            .newBuilder().apply {
                // 서버 연결 최대 10초 수행
                connectTimeout(10, TimeUnit.SECONDS)
                // 서버 요청 최대 10초 수행
                writeTimeout(10, TimeUnit.SECONDS)
                // 서버 응답 대기 20초
                readTimeout(20, TimeUnit.SECONDS)
            }.build()
    }
}