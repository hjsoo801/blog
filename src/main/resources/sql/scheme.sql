CREATE TABLE tb_search_keyword (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    keyword CLOB NOT NULL,
    search_count INT NOT NULL DEFAULT 1
);

INSERT INTO tb_search_keyword (keyword, search_count)
VALUES ('kakao', 10),
       ('bank', 9),
       ('2023년', 8),
       ('대규모', 7),
       ('채용', 6),
       ('백엔드', 5),
       ('api', 4),
       ('개발자', 3),
       ('지원', 2),
       ('사전', 1),
       ('과제', 1);