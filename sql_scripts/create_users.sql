use `auctions`;

drop table if exists `authority`;
drop table if exists `user`;

create table `user` (
	`username` varchar(50) not null,
    `password` varchar(50) not null,
    `enabled` tinyint not null,
    primary key (`username`)
) engine=InnoDB default charset=latin1;

insert into `user`
values
('john', '{noop}test123', 1),
('mary', '{noop}test123', 1),
('susan', '{noop}test123', 1);

create table `authority` (
    `id` int not null auto_increment primary key,
	`username` varchar(50) not null,
    `authority` varchar(50) not null,
    unique key `authority_idx_1` (`username`, `authority`),
    constraint `authority_ibfk_1` foreign key (`username`) references `user` (`username`)
) engine=InnoDB default charset=latin1;

insert into `authority`
values
('john', 'ROLE_ADMIN'),
('john', 'ROLE_MONITOR'),
('mary', 'ROLE_MONITOR'),
('susan', 'ROLE_MONITOR');