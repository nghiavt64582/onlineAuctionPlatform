create database if not exists `auctions`;
use `auctions`;

drop table if exists `bidden_price`;
drop table if exists `product_category`;
drop table if exists `sold_product`;
drop table if exists `bidder`;
drop table if exists `product`;
drop table if exists `auctioneer`;
drop table if exists `authority`;
drop table if exists `user`;
drop table if exists `category`;

create table `user` (
    `id` int auto_increment not null primary key,
	`username` varchar(50) not null unique,
    `password` varchar(50) not null,
    `enabled` tinyint not null,
    `email` varchar(50) not null,
    `age` tinyint not null,
    `name` nvarchar(50) not null,
    `location` nvarchar(50) not null,
    `cash` int not null,
    `created_date` datetime not null,
    `last_login` datetime not null,
    `role` varchar(20) not null
) engine=InnoDB default charset=latin1;
create index `username_idx` on `user`(`username`) using btree;

create table `authority` (
    `id` int not null auto_increment primary key,
	`username` varchar(50) not null,
    `authority` varchar(50) not null,
    unique key `authority_idx_1` (`username`, `authority`),
    foreign key (`username`) references `user` (`username`)
) engine=InnoDB default charset=latin1;

create table `bidder` (
	`id` int not null auto_increment primary key,
    `n_bought_product` int not null,
    foreign key (`id`) references `user`(`id`)
) engine=InnoDB default charset=utf8mb4;

create table `auctioneer` (
	`id` int not null auto_increment primary key,
    `credit` int not null,
    `n_sold_product` int not null,
    foreign key (`id`) references `user`(`id`)
) engine=InnoDB default charset=utf8mb4;

create table `product` (
	`id` int not null auto_increment primary key,
    `auctioneer_id` int not null,
    `name` nvarchar(100) default null,
    `image_url` varchar(150) default null,
    `beginning_price` int not null,
    `current_price` int not null,
    `created_date` datetime not null,
    `state` varchar(20) not null,
    `location` nvarchar(50) not null,
    foreign key (`auctioneer_id`) references `auctioneer` (`id`)
) engine=InnoDB default charset=utf8mb4;
create index `auctioneer_idx` on `product`(`auctioneer_id`) using btree;

create table `sold_product` (
    `id` int not null auto_increment primary key,
    `sold_date` datetime not null,
    `bidder_id` int not null,
    foreign key (`bidder_id`) references `bidder`(`id`)
) engine=InnoDB default charset=utf8mb4;
create index `bidder_idx` on `sold_product`(`bidder_id`) using btree;

create table `bidden_price` (
	`id` int not null auto_increment primary key,
    `bidder_id` int not null,
    `product_id` int not null,
    `price` int not null,
    `created_date` datetime not null,
    foreign key (`bidder_id`) references `bidder` (`id`),
    foreign key (`product_id`) references `product` (`id`)
) engine=InnoDB default charset=utf8mb4 ;
create index `bidder_idx` on `bidden_price`(`bidder_id`) using btree;
create index `product_idx` on `bidden_price`(`product_id`) using btree;

create table `category` (
    `id` int not null auto_increment primary key,
    `name` varchar(50) not null
) engine=InnoDB default charset=utf8mb4 ;
create index `name_idx` on `category`(`name`) using btree;

create table `product_category` (
    `id` int not null auto_increment primary key,
    `product_id` int not null,
    `category_id` int not null,
    foreign key (`product_id`) references `product` (`id`),
    foreign key (`category_id`) references `category` (`id`)
) engine=InnoDB default charset=utf8mb4 ;
create index `product_idx` on `product_category`(`product_id`) using btree;
create index `category_idx` on `product_category`(`category_id`) using btree;