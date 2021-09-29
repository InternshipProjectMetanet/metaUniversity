
# 유저 데이터
insert into USERS_DATA(user_code, address, user_birth, user_department, is_Thumbnail, user_email, user_name,
                       user_phone, user_type, enrollment_status, user_grade, user_major, user_minor, worker_spot)
values('20210918', 'testAddress1', 'testBirth1', 'testDepartment1', false, 'testEmail1@hello.world', 'testName1',
       'testPhone1', 'STUDENT', 'ATTENDING', 3, 'testMajor1', 'testMinor1', null);

insert into USERS_DATA(user_code, address, user_birth, user_department, is_Thumbnail, user_email, user_name,
                       user_phone, user_type, enrollment_status, user_grade, user_major, user_minor, worker_spot)
values('20210919', 'testAddress2', 'testBirth2', 'testDepartment2', false, 'testEmail2@hello.world', 'testName2',
       'testPhone2', 'STUDENT', 'ATTENDING', 3, 'testMajor2', 'testMinor2', null);

insert into USERS_DATA(user_code, address, user_birth, user_department, is_Thumbnail, user_email, user_name,
                       user_phone, user_type, enrollment_status, user_grade, user_major, user_minor, worker_spot)
values('20210920', 'testAddress3', 'testBirth3', 'testDepartment3', false, 'testEmail3@hello.world', 'testName3',
       'testPhone3', 'STUDENT', 'ATTENDING', 3, 'testMajor3', 'testMinor3', null);

insert into USERS_DATA(user_code, address, user_birth, user_department, is_Thumbnail, user_email, user_name,
                       user_phone, user_type, enrollment_status, user_grade, user_major, user_minor, worker_spot)
values('20210921', 'testAddress2', 'testBirth2', 'testDepartment2', false, 'testEmail2@hello.world', 'testName2',
       'testPhone2', 'WORKER', 'ATTENDING', 3, 'testMajor2', 'testMinor2', null);



show databases;
show tables;
use meta;
select * from users_data;
update users_data set enrollment_status = "ATTENDING" where user_code = 20210929;
delete from users_data where user_code = "20210929";
select * from users;
select * from board;
select * from board_file;
select * from user_file;
select * from file;
commit;

drop table file;

--
insert into USERS_DATA(user_code, address, user_birth, user_department, is_Thumbnail, user_email, user_name,
                       user_phone, user_type, enrollment_status, user_grade, user_major, user_minor, worker_spot)
values('20210927', 'testAddress1', 'testBirth1', 'testDepartment1', false, 'testEmail1@hello.world', '홍길동',
       'testPhone1', 'STUDENT', 'ATTENDING', 3, '컴퓨터공학과', 'testMinor1', null);

insert into USERS_DATA(user_code, address, user_birth, user_department, is_Thumbnail, user_email, user_name,
                       user_phone, user_type, enrollment_status, user_grade, user_major, user_minor, worker_spot)
values('20210928', 'testAddress2', 'testBirth2', 'testDepartment2', false, 'testEmail2@hello.world', '홍서범',
       'testPhone2', 'STUDENT', 'ATTENDING', 3, '수학과', 'testMinor2', null);

insert into USERS_DATA(user_code, address, user_birth, user_department, is_Thumbnail, user_email, user_name,
                       user_phone, user_type, enrollment_status, user_grade, user_major, user_minor, worker_spot)
values('20210929', 'testAddress3', 'testBirth3', 'testDepartment3', false, 'testEmail3@hello.world', '김김김',
       'testPhone3', 'STUDENT', 'ATTENDING', 3, '기계공학과', 'testMinor3', null);

insert into USERS_DATA(user_code, address, user_birth, user_department, is_Thumbnail, user_email, user_name,
                       user_phone, user_type, enrollment_status, user_grade, user_major, user_minor, worker_spot)
values('010010', 'testAddress3', 'testBirth3', 'testDepartment3', false, 'testEmail3@hello.world', '관리자',
       'testPhone3', 'WORKER', 'ATTENDING', 3, '기계공학과', 'testMinor3', null);


insert into board(board_id, created_date, updated_date, content, title, user_id)
values(1, '2021-09-23 00:00:00', null, '게시글', '제목', 1);
insert into board(board_id, created_date, updated_date, content, title, user_id)
values(2, '2021-09-2 00:00:00', null, '게시글2', '제목2', 1);

commit;

