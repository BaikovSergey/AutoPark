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
  engineVolume float,
  price integer,
  user_id int not null references auto_park_users(id)
);