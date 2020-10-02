create table auto_park_sell_orders (
  id serial primary key,
  condition varchar(50),
  brand varchar(50),
  model varchar(50),
  body varchar(50),
  transmission varchar(50),
  engine varchar(50),
  drive varchar(50),
  mileage integer,
  engineVolume double precision,
  price integer,
  status boolean default false ,
  car_photo_id int default 0 references auto_park_car_photos(id),
  user_id int not null references auto_park_users(id)
);