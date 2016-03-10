# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table course (
  course_no                     serial not null,
  title                         varchar(255),
  credits                       integer,
  constraint pk_course primary key (course_no)
);

create table inbox (
  id                            serial not null,
  constraint pk_inbox primary key (id)
);

create table manager (
  id                            serial not null,
  password                      varchar(255),
  name                          varchar(255),
  birth_date                    timestamp,
  inbox_id                      integer,
  constraint uq_manager_inbox_id unique (inbox_id),
  constraint pk_manager primary key (id)
);

create table message (
  id                            serial not null,
  body                          varchar(255),
  date                          timestamp,
  constraint pk_message primary key (id)
);

create table provided_course (
  id                            serial not null,
  time                          varchar(255),
  final_exam_time               timestamp,
  teacher_id                    integer,
  course_course_no              integer,
  semester                      varchar(255),
  group_id                      integer,
  room                          varchar(255),
  constraint pk_provided_course primary key (id)
);

create table student (
  id                            serial not null,
  password                      varchar(255),
  name                          varchar(255),
  birth_date                    timestamp,
  inbox_id                      integer,
  constraint uq_student_inbox_id unique (inbox_id),
  constraint pk_student primary key (id)
);

create table teacher (
  id                            serial not null,
  password                      varchar(255),
  name                          varchar(255),
  birth_date                    timestamp,
  inbox_id                      integer,
  level                         integer,
  constraint ck_teacher_level check (level in (0)),
  constraint uq_teacher_inbox_id unique (inbox_id),
  constraint pk_teacher primary key (id)
);

alter table manager add constraint fk_manager_inbox_id foreign key (inbox_id) references inbox (id) on delete restrict on update restrict;

alter table provided_course add constraint fk_provided_course_teacher_id foreign key (teacher_id) references teacher (id) on delete restrict on update restrict;
create index ix_provided_course_teacher_id on provided_course (teacher_id);

alter table provided_course add constraint fk_provided_course_course_course_no foreign key (course_course_no) references course (course_no) on delete restrict on update restrict;
create index ix_provided_course_course_course_no on provided_course (course_course_no);

alter table student add constraint fk_student_inbox_id foreign key (inbox_id) references inbox (id) on delete restrict on update restrict;

alter table teacher add constraint fk_teacher_inbox_id foreign key (inbox_id) references inbox (id) on delete restrict on update restrict;


# --- !Downs

alter table manager drop constraint if exists fk_manager_inbox_id;

alter table provided_course drop constraint if exists fk_provided_course_teacher_id;
drop index if exists ix_provided_course_teacher_id;

alter table provided_course drop constraint if exists fk_provided_course_course_course_no;
drop index if exists ix_provided_course_course_course_no;

alter table student drop constraint if exists fk_student_inbox_id;

alter table teacher drop constraint if exists fk_teacher_inbox_id;

drop table if exists course cascade;

drop table if exists inbox cascade;

drop table if exists manager cascade;

drop table if exists message cascade;

drop table if exists provided_course cascade;

drop table if exists student cascade;

drop table if exists teacher cascade;

