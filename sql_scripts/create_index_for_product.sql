use auctions;

create index `price_idx`
on `bidden_price`(`price`)
using btree;

show indexes from `product`;
drop index `image_url_idx` on `product`;
show create table `product`;
alter table `product` engine = MEMORY
