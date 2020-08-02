
create table spend_info (
  spend_type_id char(1) not null
, goods_type_id char(3) not null
, spend_type_name varchar(50) null
, goods_type_name varchar(50) null
, create_time timestamp(3)  null
, update_time timestamp(3)  null
,primary key(spend_type_id,goods_type_id)
);