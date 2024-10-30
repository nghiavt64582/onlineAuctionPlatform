create database if not exists `auctions`;
use `auctions`;

drop table if exists `bidder`;
drop table if exists `auctioneer`;
drop table if exists `product`;

create table `bidder` (
	`id` int not null auto_increment primary key ,
    `name` varchar(50) default null,
    `email` varchar(50) default null,
    `cash` int,
    `createdDate` datetime
);
create table `auctioneer` (
	`id` int not null auto_increment primary key ,
    `name` varchar(50) default null,
    `email` varchar(50) default null,
    `cash` int,
    `createdDate` datetime
);
create table `product` (
	`id` int not null auto_increment primary key,
    `auctioneerId` int,
    `name` varchar(50) default null,
    `imageUrl` varchar(150) default null,
    `email` varchar(50) default null,
    `beginningPrice` int,
    `currentPrice` int,
    `createdDate` datetime,
    `postedDate` datetime,
    `state` int,
    constraint `auctioneer_rl` foreign key (`auctioneerId`) references `auctioneer` (`id`)
);
create table `bidden_price` (
	`id` int not null auto_increment primary key,
    `bidderId` int,
    `productId` int,
    `price` int,
    `createdDate` datetime,
    constraint `bidden_price_rl1` foreign key (`bidderId`) references `bidder` (`id`),
    constraint `bidden_price_rl2` foreign key (`productId`) references `product` (`id`)
 )