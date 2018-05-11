/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2017/5/11 15:19:16                           */
/*==============================================================*/


alter table pay_refund
   drop primary key cascade;

drop table pay_refund cascade constraints;

alter table pay_trade
   drop primary key cascade;

drop table pay_trade cascade constraints;

/*==============================================================*/
/* Table: pay_refund                                            */
/*==============================================================*/
create table pay_refund 
(
   out_refund_no        varchar(32)          not null,
   out_trade_no         varchar(32),
   refund_id            varchar(32),
   refund_fee           numeric(10,0),
   refund_channel       varchar(16),
   refund_status        varchar(16),
   refund_time          varchar(19),
   create_time          varchar(19),
   update_time          varchar(19)
);

comment on table pay_refund is
'退款表';

comment on column pay_refund.out_refund_no is
'商户退款单号';

comment on column pay_refund.out_trade_no is
'商户订单号';

comment on column pay_refund.refund_id is
'威富通退款单号';

comment on column pay_refund.refund_fee is
'退款金额';

comment on column pay_refund.refund_channel is
'退款渠道';

comment on column pay_refund.refund_status is
'退款状态';

comment on column pay_refund.refund_time is
'退款时间';

comment on column pay_refund.create_time is
'创建时间';

comment on column pay_refund.update_time is
'修改时间';

alter table pay_refund
   add constraint pk_pay_refund primary key (out_refund_no);

/*==============================================================*/
/* Table: pay_trade                                             */
/*==============================================================*/
create table pay_trade 
(
   out_trade_no         varchar(32)          not null,
   transaction_id       varchar(32),
   out_transaction_id   varchar(64),
   body                 varchar(127),
   total_fee            numeric(10,0),
   trade_type           varchar(32),
   trade_state          varchar(32),
   time_start           varchar(19),
   time_expire          varchar(19),
   time_end             varchar(19),
   create_time          varchar(19),
   update_time          varchar(19)
);

comment on table pay_trade is
'订单表';

comment on column pay_trade.out_trade_no is
'商户订单号';

comment on column pay_trade.transaction_id is
'威富通订单号';

comment on column pay_trade.out_transaction_id is
'第三方订单号';

comment on column pay_trade.body is
'商品描述';

comment on column pay_trade.total_fee is
'总金额';

comment on column pay_trade.trade_type is
'交易类型';

comment on column pay_trade.trade_state is
'交易状态';

comment on column pay_trade.time_start is
'订单生成时间';

comment on column pay_trade.time_expire is
'订单超时时间';

comment on column pay_trade.time_end is
'订单完成时间';

comment on column pay_trade.create_time is
'创建时间';

comment on column pay_trade.update_time is
'修改时间';

alter table pay_trade
   add constraint pk_pay_trade primary key (out_trade_no);

