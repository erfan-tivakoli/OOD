# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table inbox (
  id                            serial not null,
  email_address                 varchar(255),
  constraint uq_inbox_email_address unique (email_address),
  constraint pk_inbox primary key (id)
);

create table message (
  id                            serial not null,
  body                          varchar(255),
  date                          timestamp,
  constraint pk_message primary key (id)
);

create table person (
  id                            serial not null,
  user_name                     varchar(255),
  password                      varchar(255),
  name                          varchar(255),
  family_name                   varchar(255),
  email_address                 varchar(255),
  inbox_id                      integer,
  constraint uq_person_user_name unique (user_name),
  constraint uq_person_inbox_id unique (inbox_id),
  constraint pk_person primary key (id)
);

alter table person add constraint fk_person_inbox_id foreign key (inbox_id) references inbox (id) on delete restrict on update restrict;


# --- !Downs

alter table person drop constraint if exists fk_person_inbox_id;

drop table if exists inbox cascade;

drop table if exists message cascade;

drop table if exists person cascade;

