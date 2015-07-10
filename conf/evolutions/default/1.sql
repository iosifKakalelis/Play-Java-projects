# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table class (
  name                      varchar(255))
;

create table course (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_course primary key (id))
;

create table student (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  age                       varchar(255),
  constraint pk_student primary key (id))
;

create table student_and_course (
  student_id                bigint,
  course_id                 bigint,
  date                      datetime(6),
  version                   integer,
  constraint pk_student_and_course primary key (student_id, course_id))
;


create table student_course (
  student_id                     bigint not null,
  course_id                      bigint not null,
  constraint pk_student_course primary key (student_id, course_id))
;
alter table student_and_course add constraint fk_student_and_course_student_1 foreign key (student_id) references student (id) on delete restrict on update restrict;
create index ix_student_and_course_student_1 on student_and_course (student_id);
alter table student_and_course add constraint fk_student_and_course_course_2 foreign key (course_id) references course (id) on delete restrict on update restrict;
create index ix_student_and_course_course_2 on student_and_course (course_id);



alter table student_course add constraint fk_student_course_student_01 foreign key (student_id) references student (id) on delete restrict on update restrict;

alter table student_course add constraint fk_student_course_course_02 foreign key (course_id) references course (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table class;

drop table course;

drop table student_course;

drop table student;

drop table student_and_course;

SET FOREIGN_KEY_CHECKS=1;

