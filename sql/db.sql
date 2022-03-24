drop table product;
drop sequence product_product_id_seq;

-- 테이블 생성
create table product (
    product_id          number(5),
    name                varchar2(30),
    quantity            number(5),
    price               number(10)
);

-- 기본키 생성
alter table product add constraint product_pk primary key (product_id);

-- 시퀀스 생성
create sequence product_product_id_seq
start with 1
increment by 1
minvalue 0
maxvalue 99999999
nocycle
nocache;

-- CRUD 검증
select product_id, product_name, product_quantity, product_price
from product;

insert into product
values(product_product_id_seq.nextval, '컴퓨터', 50, 1000000);

update product
set product_quantity = 40
where product_id = 1;

delete product
where product_id = 1;