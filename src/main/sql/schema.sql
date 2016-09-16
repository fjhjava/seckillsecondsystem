--数据库初始化脚本


--创建数据库
create database seckill;
--使用数据库
use seckill;
--创建表
create table seckill(
`seckill_id` bigint not null auto_increment,
`name` varchar(120) not null  ,
`number` int not null  ,
`create_time` timestamp not null default current_timestamp  ,
`start_time` timestamp not null  ,
`end_time` timestamp not null  ,
primary key(seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)engine=InnoDB auto_increment=1000

--初始化数据
insert into 
	seckill(name,number,start_time,end_time)
values
	('1000元秒杀iphone6s',100,'2016-9-16 00:00:00','2016-9-18 00:00:00'),
	('500元秒杀ipad2',200,'2016-9-16 00:00:00','2016-9-18 00:00:00'),
	('300元秒杀小米4',300,'2016-9-16 00:00:00','2016-9-18 00:00:00'),
	('200元秒杀iphone4',400,'2016-9-16 00:00:00','2016-9-18 00:00:00');

--秒杀成功明细表
--用户登录认证相关信息
	create table success_killed(
	`seckill_id` bigint not null ,
	`user_phone` bigint not null ,
	`state` tinyint not null default -1 ,
	`create_time` timestamp not null ,
	primary key(seckill_id,user_phone),/*联合主键*/
	key idx_create_time(create_time)
	)engine=InnoDB
	
	--连接数据库
	mysql -u root -p  1234