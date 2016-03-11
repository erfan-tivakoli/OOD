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

create table message (
  id                            serial not null,
  inbox_id                      integer not null,
  body                          varchar(255),
  date                          timestamp,
  constraint pk_message primary key (id)
);

create table person (
  role                          varchar(31) not null,
  id                            serial not null,
  password                      varchar(255),
  name                          varchar(255),
  birth_date                    varchar(255),
  inbox_id                      integer,
  level                         integer,
  constraint ck_person_level check (level in (0)),
  constraint uq_person_inbox_id unique (inbox_id),
  constraint pk_person primary key (id)
);

create table person_provided_course (
  person_id                     integer not null,
  provided_course_id            integer not null,
  constraint pk_person_provided_course primary key (person_id,provided_course_id)
);

create table provided_course (
  id                            serial not null,
  time                          varchar(255),
  final_exam_time               timestamp,
  teacher_id                    integer,
  course_course_no              integer,
  syllabes_id                   integer,
  semester                      varchar(255),
  group_id                      integer,
  room                          varchar(255),
  constraint uq_provided_course_syllabes_id unique (syllabes_id),
  constraint pk_provided_course primary key (id)
);

create table provided_course_person (
  provided_course_id            integer not null,
  person_id                     integer not null,
  constraint pk_provided_course_person primary key (provided_course_id,person_id)
);

create table source (
  id                            serial not null,
  source                        varchar(255),
  link                          varchar(255),
  constraint pk_source primary key (id)
);

create table syllabes (
  id                            serial not null,
  topic_id                      integer,
  constraint uq_syllabes_topic_id unique (topic_id),
  constraint pk_syllabes primary key (id)
);

create table syllabes_source (
  syllabes_id                   integer not null,
  source_id                     integer not null,
  constraint pk_syllabes_source primary key (syllabes_id,source_id)
);

create table topic (
  id                            serial not null,
  description                   varchar(255),
  constraint pk_topic primary key (id)
);

alter table message add constraint fk_message_inbox_id foreign key (inbox_id) references inbox (id) on delete restrict on update restrict;
create index ix_message_inbox_id on message (inbox_id);

alter table person add constraint fk_person_inbox_id foreign key (inbox_id) references inbox (id) on delete restrict on update restrict;

alter table person_provided_course add constraint fk_person_provided_course_person foreign key (person_id) references person (id) on delete restrict on update restrict;
create index ix_person_provided_course_person on person_provided_course (person_id);

alter table person_provided_course add constraint fk_person_provided_course_provided_course foreign key (provided_course_id) references provided_course (id) on delete restrict on update restrict;
create index ix_person_provided_course_provided_course on person_provided_course (provided_course_id);

alter table provided_course add constraint fk_provided_course_teacher_id foreign key (teacher_id) references person (id) on delete restrict on update restrict;
create index ix_provided_course_teacher_id on provided_course (teacher_id);

alter table provided_course add constraint fk_provided_course_course_course_no foreign key (course_course_no) references course (course_no) on delete restrict on update restrict;
create index ix_provided_course_course_course_no on provided_course (course_course_no);

alter table provided_course add constraint fk_provided_course_syllabes_id foreign key (syllabes_id) references syllabes (id) on delete restrict on update restrict;

alter table provided_course_person add constraint fk_provided_course_person_provided_course foreign key (provided_course_id) references provided_course (id) on delete restrict on update restrict;
create index ix_provided_course_person_provided_course on provided_course_person (provided_course_id);

alter table provided_course_person add constraint fk_provided_course_person_person foreign key (person_id) references person (id) on delete restrict on update restrict;
create index ix_provided_course_person_person on provided_course_person (person_id);

alter table syllabes add constraint fk_syllabes_topic_id foreign key (topic_id) references topic (id) on delete restrict on update restrict;

alter table syllabes_source add constraint fk_syllabes_source_syllabes foreign key (syllabes_id) references syllabes (id) on delete restrict on update restrict;
create index ix_syllabes_source_syllabes on syllabes_source (syllabes_id);

alter table syllabes_source add constraint fk_syllabes_source_source foreign key (source_id) references source (id) on delete restrict on update restrict;
create index ix_syllabes_source_source on syllabes_source (source_id);


# --- !Downs

alter table message drop constraint if exists fk_message_inbox_id;
drop index if exists ix_message_inbox_id;

alter table person drop constraint if exists fk_person_inbox_id;

alter table person_provided_course drop constraint if exists fk_person_provided_course_person;
drop index if exists ix_person_provided_course_person;

alter table person_provided_course drop constraint if exists fk_person_provided_course_provided_course;
drop index if exists ix_person_provided_course_provided_course;

alter table provided_course drop constraint if exists fk_provided_course_teacher_id;
drop index if exists ix_provided_course_teacher_id;

alter table provided_course drop constraint if exists fk_provided_course_course_course_no;
drop index if exists ix_provided_course_course_course_no;

alter table provided_course drop constraint if exists fk_provided_course_syllabes_id;

alter table provided_course_person drop constraint if exists fk_provided_course_person_provided_course;
drop index if exists ix_provided_course_person_provided_course;

alter table provided_course_person drop constraint if exists fk_provided_course_person_person;
drop index if exists ix_provided_course_person_person;

alter table syllabes drop constraint if exists fk_syllabes_topic_id;

alter table syllabes_source drop constraint if exists fk_syllabes_source_syllabes;
drop index if exists ix_syllabes_source_syllabes;

alter table syllabes_source drop constraint if exists fk_syllabes_source_source;
drop index if exists ix_syllabes_source_source;

drop table if exists course cascade;

drop table if exists inbox cascade;

drop table if exists message cascade;

drop table if exists person cascade;

drop table if exists person_provided_course cascade;

drop table if exists provided_course cascade;

drop table if exists provided_course_person cascade;

drop table if exists source cascade;

drop table if exists syllabes cascade;

drop table if exists syllabes_source cascade;

drop table if exists topic cascade;

