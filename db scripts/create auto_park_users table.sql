create table auto_park_users (
  id serial primary key,
  name varchar(200),
  email varchar(200) unique,
  password varchar(200)
);