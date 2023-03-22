# BLOG 검색 서비스


1. [jar 다운로드 경로](#jar-다운로드-경로)
2. [Project 환경](#Project-환경)
3. [OpenSource](#OpenSource)
4. [API 명세](#API-명세)
5. [Test Case](#Test-Case)


## jar 다운로드 경로
[jar 다운로드](https://github.com/hjsoo801/blog/raw/main/libs/search-0.0.1-SNAPSHOT.jar)


## Project 환경

- Language : Kotlin
- FrameWork: Spring Boot 2.7.9
- JavaVersion : 11
- DB: Inmemory DB (h2)


## OpenSource
- Mockito
    - TestCode를 작성할 때 Mock 객체를 만들어 테스트 하기 위해 사용
- okhttp3
    - 타 서비스로 api 를 호출 할 때 사용
    - 내장 서비스 대비 컨넥션 관련하여 지원하는 코드가 존재하여 사용
- Gson
    - json 파싱을 위해 사용
    - jackson과 달리 기본적으로 json과 kotlin(java)객체의 프로퍼티 명을 일치시켜 주는 편리함이 있고 response 구조가 복잡하지 않아 jackson 보다 해당 프로젝트에 부합하다고 생각하여 사용
- log4jdbc
    - jpa 로 쿼리를 발생시킬 때, 사용되는 query 와 param을 찍기 위해 사용
- spring-boot-starter-data-jpa
    - jpa 를 사용하기 위해서 사용
- spring-boot-starter-logging
    - DB 쿼리 결과 및 error log를 남기기 위해 사용
- org.springframework.boot:spring-boot-starter-test
    - TestCode를 작성하기 위해 사용
- kotlin-reflect
    - kotlin 리플렉션을 사용하기 위해 사용

## API 명세
- GET {endpoint}/api/v1/search
```
  curl --location 'localhost:8080/api/v1/search?query=kakao&sort=accuracy&page=1&size=1'
  
  # 200 OK Content-Type: application/json
  
  {
    "status": "SUCCESS",
    "resultData": {
        "meta": {
        "totalCount": 39115939,
        "pageableCount": 620,
        "isEnd": false
      },
      "documents": [
          {
            "title": "<b>카카오</b>(기업) - 나무위키",
            "contents": "<b>카카오</b>는 다양한 모바일 및 인터넷 서비스를 제공하는 IT 기업이다. 2022년 기준 재계서열 15위다.2022년 공정거래위원회 발표. 2021년 6월 이후 국내 시가총액 3위~6위에 랭크되기도 했다. 자회사로는 <b>카카오</b>...",
            "url": "https://namu.wiki/w/%EC%B9%B4%EC%B9%B4%EC%98%A4(%EA%B8%B0%EC%97%85)",
            "blogname": null,
            "thumbnail": null,
            "datetime": "2023-03-20T00:00:00.000+09:00"
          },...
        ]
    },
  "resultCode": "",
  "resultMessage": ""
  }
```
|param|type| desc                       |
|------|---|----------------------------|
|query|String| required                   |
|sort|String| recency, accuracy(default) |
|page|Int| 1(default)~50              |
|size|Int| 1(default)~50              |


- GET {endpoint}/api/v1/pop
```
  curl --location 'localhost:8080/api/v1/pop'
  # 200 OK Content-Type: application/json
  {
  "status": "SUCCESS",
  "resultData": [
    {
    "id": 1,
    "keyword": "kakao",
    "searchCount": 10
    },...
  ],
  "resultCode": "",
  "resultMessage": ""
  }
```

