-- 시퀀스
DROP SEQUENCE GESIPAN_SEQ;
CREATE SEQUENCE GESIPAN_SEQ NOCACHE;

-- 테이블
DROP TABLE GESIPAN;
CREATE TABLE GESIPAN (
    GESIPAN_NO      NUMBER NOT NULL,
    TITLE         VARCHAR2(1000 BYTE) NOT NULL,
    CONTENT       CLOB,
    MODIFIED_DATE DATE,
    CREATED_DATE  DATE NOT NULL,
    CONSTRAINT PK_GESIPAN PRIMARY KEY(GESIPAN_NO)
);

-- 행(ROW)
INSERT INTO GESIPAN VALUES(GESIPAN_SEQ.NEXTVAL, 'Cant take my eyes off you', '오래된 노래 넘 좋다', NULL, SYSDATE);
INSERT INTO GESIPAN VALUES(GESIPAN_SEQ.NEXTVAL, 'Love never felt so good', '이거도 꽤나 좋다', NULL, SYSDATE);