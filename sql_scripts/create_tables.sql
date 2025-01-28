create database if not exists `auctions`;
use `auctions`;

drop table if exists `bidder`;
drop table if exists `auctioneer`;
drop table if exists `product`;
drop table if exists `bidden_price`;

create table `bidder` (
	`id` int not null auto_increment primary key ,
    `name` varchar(50) default null,
    `email` varchar(50) default null unique,
    `cash` int,
    `created_date` datetime,
    `username` varchar(50) default null unique
) engine=MEMORY default charset=utf8mb4;

create table `auctioneer` (
	`id` int not null auto_increment primary key ,
    `name` varchar(50) default null,
    `email` varchar(50) default null unique,
    `cash` int,
    `created_date` datetime,
    `username` varchar(50) default null unique
) engine=MEMORY default charset=utf8mb4;

create table `product` (
	`id` int not null auto_increment primary key,
    `auctioneer_id` int,
    `name` varchar(50) default null,
    `image_url` varchar(150) default null,
    `beginning_price` int,
    `current_price` int,
    `created_date` datetime,
    `state` int,
    constraint `auctioneer_rl` foreign key (`auctioneer_id`) references `auctioneer` (`id`)
) engine=MEMORY default charset=utf8mb4;

create table `bidden_price` (
	`id` int not null auto_increment primary key,
    `bidder_id` int,
    `product_id` int,
    `price` int,
    `created_date` datetime,
    constraint `bidden_price_rl1` foreign key (`bidder_id`) references `bidder` (`id`),
    constraint `bidden_price_rl2` foreign key (`product_id`) references `product` (`id`)
) engine=MEMORY default charset=utf8mb4 ;