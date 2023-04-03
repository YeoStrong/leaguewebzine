----------------------------------------------------------------------
-----------------------------  MAIN_DAO  ---------------------------
----------------------------------------------------------------------
-- (1) 공지사항 상위 5개
SELECT *
    FROM (SELECT ROWNUM RN, B.*
        FROM (SELECT N.*, ANICKNAME
            FROM NBOARD N, ADMIN A
            WHERE N.AID = A.AID
            ORDER BY NNUM DESC) B)
    WHERE RN BETWEEN 1 AND 5;
-- (2) 공략 상위 5개
SELECT *
    FROM (SELECT ROWNUM RN, B.*
        FROM (SELECT G.*, MNICKNAME
            FROM GBOARD G, MEMBER M
            WHERE G.MID = M.MID
            ORDER BY GNUM DESC) B)
    WHERE RN BETWEEN 1 AND 5;
-- (3) 자유 상위 5개
SELECT *
    FROM (SELECT ROWNUM RN, A.*
        FROM (SELECT F.*, MNICKNAME
            FROM FBOARD F, MEMBER M
            WHERE F.MID = M.MID
            ORDER BY FNUM DESC) A)
    WHERE RN BETWEEN 1 AND 5;
-- (4) 영상게 상위 5개
SELECT *
    FROM (SELECT ROWNUM RN, B.*
        FROM (SELECT V.*, MNICKNAME
            FROM VBOARD V, MEMBER M
            WHERE V.MID = M.MID
            ORDER BY VNUM DESC) B)
    WHERE RN BETWEEN 1 AND 5;
----------------------------------------------------------------------
-----------------------------  MEMBER_DAO  ---------------------------
----------------------------------------------------------------------
-- (1) 회원id 중복체크
SELECT * FROM MEMBER WHERE MID='hong';
-- (2) 회원 닉네임 중복체크
SELECT * FROM MEMBER WHERE MNICKNAME='닉넴1';
-- (3) 회원가입
INSERT INTO MEMBER (MID, MNICKNAME, MPW, MNAME, MTEL, MBIRTH, MEMAIL, MGENDER, MPHOTO, MADDRESS)
    VALUES ('hong', '홍씨성을가진서자', '1', '홍길동', '010-5555-5555', '1443/05/05', 'hong@chosun.com' , '남성', NULL, '경기도 파주시');
INSERT INTO MEMBER (MID, MNICKNAME, MPW, MNAME, MTEL, MBIRTH, MEMAIL, MGENDER, MPHOTO, MADDRESS)
    VALUES ('abc', '트룰', '1', NULL, NULL, NULL, NULL , NULL, NULL, NULL);
COMMIT;
-- (4) 로그인
SELECT * FROM MEMBER WHERE MID='aaa' and MPW='1';
-- (5) mid로 dto가져오기(로그인 성공시 session에 넣기 위함)
SELECT M.*, LNAME FROM MLEVEL L, MEMBER M
    WHERE M.LLEVELNUM=L.LLEVELNUM AND MID='aaa';
-- (6) 회원정보 수정
UPDATE MEMBER 
  SET MNICKNAME='바뀐닉넴1',
      MPW='1',
      MNAME='김구구',
      MTEL='010-9999-9999',
      MBIRTH='1999/09/09',
      MEMAIL='weird@naver.com',
      MGENDER='여성',
      MPHOTO = NULL,
      MADDRESS = '서울시 서대문구'
  WHERE MID='aaa';
-- (7) 회원 리스트(top-n)
SELECT M.*, LNAME FROM MLEVEL L, MEMBER M
    WHERE M.LLEVELNUM=L.LLEVELNUM 
    ORDER BY MRDATE DESC; -- 신입회원순(SUBQUERY)
SELECT *
  FROM (SELECT ROWNUM RN, A.* FROM (SELECT M.*, LNAME FROM MLEVEL L, MEMBER M
                                        WHERE M.LLEVELNUM=L.LLEVELNUM 
                                        ORDER BY MRDATE DESC) A)
  WHERE RN BETWEEN 1 AND 3;
-- (8) 전체 등록된 회원수
SELECT COUNT(*) CNT FROM MEMBER;
-- (9) 회원탈퇴처리
COMMIT;
UPDATE MEMBER SET MWITHDRAWAL=0 WHERE MID='king';
ROLLBACK;
-- (10) 본인 글 갯수(게시판별)
-- 공략
SELECT COUNT(*) CNT FROM GBOARD WHERE MID='abc';
-- 자유
SELECT COUNT(*) CNT FROM FBOARD WHERE MID='abc';
-- 영상
SELECT COUNT(*) CNT FROM VBOARD WHERE MID='abc';
-- (11) 본인 댓글 갯수(게시판별)
-- 공략
SELECT COUNT(*) CNT FROM GCOMMENT WHERE MID='abc';
-- 자유
SELECT COUNT(*) CNT FROM FCOMMENT WHERE MID='abc';
-- 영상
SELECT COUNT(*) CNT FROM VCOMMENT WHERE MID='abc';
--(12) 멤버 등급 변경
UPDATE MEMBER 
  SET LLEVELNUM=2
  WHERE MID='abc';
----------------------------------------------------------------------
-----------------------------  ADMIN_DAO  ----------------------------
----------------------------------------------------------------------
-- (1) admin 로그인
SELECT * FROM ADMIN WHERE AID='admin' AND APW='1';
-- (2) 로그인 후 세션에 넣을 용도 admin aid로 AdminDto 가져오기
SELECT * FROM ADMIN WHERE AID='admin';
----------------------------------------------------------------------
------------------------  NOTICEBOARD_DAO  ---------------------------
----------------------------------------------------------------------
-- (1) 글 제목으로 검색
SELECT *
    FROM (SELECT ROWNUM RN, B.*
        FROM (SELECT N.*, ANICKNAME
            FROM NBOARD N, ADMIN A
            WHERE N.AID = A.AID AND NTITLE LIKE '%'||TRIM('')||'%'
            ORDER BY NNUM DESC) B)
    WHERE RN BETWEEN 1 AND 10;
-- (1)-1 검색 시 글 개수
SELECT COUNT(*) CNT FROM NBOARD N, ADMIN A
    WHERE N.AID = A.AID AND NTITLE LIKE '%'||TRIM('')||'%';
-- (2) 글갯수
SELECT COUNT(*) FROM NBOARD;
-- (3) 글쓰기(원글쓰기)
INSERT INTO NBOARD (NNUM, NTITLE, NCONTENT, NIMAGE, AID)
    VALUES(NBOARD_NNUM_SEQ.NEXTVAL, '세번째 공지사항', '알아서 잘해', NULL, 'admin');
-- (4) hit 1회 올리기
UPDATE NBOARD SET NHIT = NHIT + 1 WHERE NNUM=1;
-- (5) 글번호로 글전체 내용(boardDto) 가져오기
SELECT N.*, ANICKNAME FROM NBOARD N, ADMIN A  WHERE N.AID=A.AID AND NNUM=1;
-- (6) 글 수정하기
UPDATE NBOARD 
    SET NTITLE = '바뀐 2번째 공지',
        NCONTENT = '바꿨습니다',
        NIMAGE = NULL,
        NDATE = SYSDATE
    WHERE NNUM = 2;
-- (7) 글 삭제하기
COMMIT;
DELETE FROM NBOARD WHERE NNUM=2;
ROLLBACK;
----------------------------------------------------------------------
-------------------------  GUIDEBOARD_DAO  ---------------------------
----------------------------------------------------------------------
-- (1) 글목록(startRow~endRow)
SELECT *
    FROM (SELECT ROWNUM RN, B.*
        FROM (SELECT G.*, MNICKNAME
            FROM GBOARD G, MEMBER M
            WHERE G.MID = M.MID AND GTITLE LIKE  '%'||TRIM('')||'%'
            ORDER BY GNUM DESC) B)
    WHERE RN BETWEEN 1 AND 10;
-- (2) 글갯수
SELECT COUNT(*) CNT FROM GBOARD G, MEMBER M
    WHERE G.MID = M.MID AND GTITLE LIKE '%'||TRIM('심화')||'%';
-- (3) 글쓰기(원글쓰기)
INSERT INTO GBOARD (GNUM, GTITLE, GCONTENT, GFILE1, GFILE2, GFILE3, MID, CNAME, GIP)
    VALUES (GBOARD_GNUM_SEQ.NEXTVAL, '심화 공략', '부쉬에 숨어있는다', NULL, NULL, NULL,  'aaa', '가렌', NULL);
-- (4) hit 1회 올리기
UPDATE GBOARD SET GHIT = GHIT + 1 WHERE GNUM=1;
-- (5) 글번호로 글전체 내용(boardDto) 가져오기
SELECT G.*, MNICKNAME FROM GBOARD G, MEMBER M  WHERE G.MID=M.MID AND GNUM=1;
-- (6) 글 수정하기
UPDATE GBOARD 
    SET GTITLE = '바뀐 가렌 심화 공략',
        GCONTENT = '부쉬에 계속 숨어있는다',
        GFILE1 = NULL,
        GFILE2 = NULL,
        GFILE3 = NULL,
        CNAME = '가렌'
    WHERE GNUM = 2;
-- (7) 글 삭제하기
COMMIT;
    -- 댓글 선삭제
    DELETE FROM GCOMMENT WHERE GNUM=2;
-- 글 삭제
DELETE FROM GBOARD WHERE GNUM=2;
ROLLBACK;
----------------------------------------------------------------------
------------------  GUIDEBOARD_COMMENT(GCOMMENT)  --------------------
----------------------------------------------------------------------
-- (1) 1번글에 댓글 쓰기
INSERT INTO GCOMMENT (GCNUM, MID, GNUM, GCCONTENT)
    VALUES(GCOMMENT_GCNUM_SEQ.NEXTVAL, 'ccc', 1, '다시봐도 좋네요');
-- (2) 1번글에 달린 댓글 모두 출력
SELECT G.*, MNICKNAME FROM GCOMMENT G, MEMBER M WHERE M.MID=G.MID AND GNUM=1 ORDER BY GCDATE;
-- (3) 댓글 수정 VIEW
SELECT G.*, MNICKNAME FROM GCOMMENT G, MEMBER M WHERE GCNUM=1 AND G.MID=M.MID;
-- (4) 댓글 수정
UPDATE GCOMMENT SET GCCONTENT = '댓글수정햇우~' WHERE GCNUM=3;
-- (5) 댓글 삭제
COMMIT;
DELETE FROM GCOMMENT WHERE GCNUM=1;
ROLLBACK;
SELECT * FROM GCOMMENT;
----------------------------------------------------------------------
-------------------------  VIDEOBOARD_DAO  ---------------------------
----------------------------------------------------------------------
-- (1) 글목록(startRow~endRow)
SELECT *
    FROM (SELECT ROWNUM RN, B.*
        FROM (SELECT V.*, MNICKNAME
            FROM VBOARD V, MEMBER M
            WHERE V.MID = M.MID AND VTITLE LIKE '%'||TRIM('')||'%'
            ORDER BY VNUM DESC) B)
    WHERE RN BETWEEN 1 AND 10;
-- (2) 글갯수
SELECT COUNT(*) CNT FROM VBOARD V, MEMBER M
    WHERE V.MID = M.MID AND VTITLE LIKE '%'||TRIM('아붕')||'%';
-- (3) 글쓰기(원글쓰기)
INSERT INTO VBOARD (VNUM, CNAME, VTITLE, VCONTENT, VVIDEO, MID, VIP)
    VALUES (VBOARD_VNUM_SEQ.NEXTVAL, '가렌', '가붕이궁쓴다', '투쾅', 'garenUlt.mp4', 'aaa', NULL);
-- (4) hit 1회 올리기
UPDATE VBOARD SET VHIT = VHIT + 1 WHERE VNUM=1;
-- (5) 글번호(VNUM)로 글전체 내용(boardDto) 가져오기
SELECT V.*, MNICKNAME FROM VBOARD V, MEMBER M  WHERE V.MID=M.MID AND VNUM=2;
-- (6) 글 수정하기
UPDATE VBOARD 
    SET CNAME = '가렌',
        VTITLE = '가붕이돈다',
        VCONTENT = '데구구구구구루루루',
        VVIDEO = 'garenWhirl.mp4'
    WHERE VNUM = 1;
-- (7) 글 삭제하기
COMMIT;
    -- 댓글 선삭제
    DELETE FROM VCOMMENT WHERE VNUM=2;
-- 글 삭제
DELETE FROM VBOARD WHERE VNUM=2;
ROLLBACK;
----------------------------------------------------------------------
------------------  VIDEOBOARD_COMMENT(VCOMMENT)  --------------------
----------------------------------------------------------------------
-- (1) 1번글에 댓글 쓰기
INSERT INTO VCOMMENT (VCNUM, MID, VNUM, VCCONTENT)
    VALUES(VCOMMENT_VCNUM_SEQ.NEXTVAL, 'ccc', 1, '멋진 플레이에요');
-- (2) 1번글에 달린 댓글 모두 출력
SELECT V.*, MNICKNAME FROM VCOMMENT V, MEMBER M WHERE M.MID=V.MID AND VNUM=1 ORDER BY VCDATE;
-- (3) 댓글 수정 VIEW
SELECT V.*, MNICKNAME FROM VCOMMENT V, MEMBER M WHERE VCNUM=1 AND M.MID=V.MID;
-- (4) 댓글 수정
UPDATE VCOMMENT SET VCCONTENT = '너무좋아서 댓글도 수정했어요' WHERE VCNUM=1;
-- (5) 댓글 삭제
COMMIT;
DELETE FROM VCOMMENT WHERE VCNUM=1;
ROLLBACK;
----------------------------------------------------------------------
----------------------------  FREEBOARD  -----------------------------
----------------------------------------------------------------------
-- (1) 글 제목으로 검색
SELECT *
    FROM (SELECT ROWNUM RN, A.*
        FROM (SELECT F.*, MNICKNAME
            FROM FBOARD F, MEMBER M
            WHERE F.MID = M.MID AND FTITLE LIKE '%'||TRIM('자유')||'%'
            ORDER BY FGROUP DESC, FSTEP) A)
    WHERE RN BETWEEN 1 AND 10;
-- (2) 검색 시 글 개수
SELECT COUNT(*) CNT FROM FBOARD F, MEMBER M
    WHERE F.MID = M.MID AND FTITLE LIKE '%'||TRIM('자유')||'%';
-- (3) 글쓰기(원글쓰기)
INSERT INTO FBOARD (FNUM, FTITLE, FCONTENT, FIMAGE, FGROUP, FSTEP, FINDENT, MID, FIP)
    VALUES (FBOARD_FNUM_SEQ.NEXTVAL, '자유로운', '자유게시판', NULL, FBOARD_FNUM_SEQ.CURRVAL, 0, 0, 'aaa', '111.1.1.1');
-- (4) hit 1회 올리기
UPDATE FBOARD SET FHIT = FHIT + 1 WHERE FNUM=1;
-- (5) 글번호(fnum)로 글전체 내용(boardDto) 가져오기
SELECT F.*, MNICKNAME FROM FBOARD F, MEMBER M  WHERE F.MID=M.MID AND FNUM=5;
-- (6) 글 수정하기(fid, ftitle, fcontent, ffilename, frdate(SYSDATE), fip 수정)
UPDATE FBOARD 
    SET FTITLE = '제목 바꿈',
        FCONTENT = '본문 바꿈',
        FIMAGE = '바꾼파일.png'
    WHERE FNUM = 5;
-- (7) 글 삭제하기
COMMIT;
    -- 댓글 선삭제
    DELETE FROM FCOMMENT WHERE FNUM=5;
DELETE FROM FBOARD WHERE FNUM=5;
ROLLBACK;
-- 2번글에 답변 쓰기
-- (8) 답변글 쓰기 전 단계(원글의 fgroup과 같고, 원글의 fstep보다 크면 fstep을 하나 증가하기)
UPDATE FBOARD SET FSTEP = FSTEP + 1 WHERE FGROUP=3 AND FSTEP>0;
-- (9) 답변글 쓰기
INSERT INTO FBOARD (FNUM, FTITLE, FCONTENT, FIMAGE, FGROUP, FSTEP, FINDENT, MID, FIP)
    VALUES (FBOARD_FNUM_SEQ.NEXTVAL, '답변이 진짜 가능한', '완전 자유로운 자유게시판', NULL, 3, 1, 1, 'abc', '111.1.1.1');
COMMIT;
----------------------------------------------------------------------
-------------------  FREEBOARD_COMMENT(FCOMMENT)  --------------------
----------------------------------------------------------------------
-- (1) 1번글에 댓글 쓰기
INSERT INTO FCOMMENT (FCNUM, MID, FNUM, FCCONTENT)
    VALUES(FCOMMENT_FCNUM_SEQ.NEXTVAL, 'ccc', 1, '멋진 플레이에요');
-- (2) 1번글에 달린 댓글 모두 출력
SELECT F.*, MNICKNAME FROM FCOMMENT F, MEMBER M WHERE M.MID=F.MID AND FNUM=8 ORDER BY FCDATE;
-- (3) 댓글 수정 view
SELECT F.*, MNICKNAME FROM FCOMMENT F, MEMBER M WHERE M.MID=F.MID AND FCNUM=1;
-- (4) 댓글 수정
UPDATE FCOMMENT SET FCCONTENT = '너무좋아서 댓글도 수정했어요' WHERE FCNUM=3;
-- (5) 댓글 삭제
COMMIT;
DELETE FROM FCOMMENT WHERE FCNUM=1;
ROLLBACK;