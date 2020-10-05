create table auto_park_car_photos (
  id serial primary key,
  name varchar(200),
  sell_order_id integer not null default 0
);