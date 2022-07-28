create table address
(
    id      bigint auto_increment
        primary key,
    city    varchar(255) null,
    country varchar(255) null,
    street  varchar(255) null
);

create table app_users
(
    id       int auto_increment
        primary key,
    email    varchar(255) not null,
    password varchar(255) null,
    username varchar(255) not null,
    constraint UK_4vj92ux8a2eehds1mdvmks473
        unique (email),
    constraint UK_spsnwr241e9k9c8p5xl4k45ih
        unique (username)
);

create table app_user_app_user_roles
(
    app_user_id    int not null,
    app_user_roles int null,
    constraint FKptlnuok8fu8x1qswbsfb0yu1w
        foreign key (app_user_id) references app_users (id)
);

create table country
(
    id   int auto_increment
        primary key,
    code varchar(255) null,
    name varchar(255) null
);

create table customer
(
    id         bigint auto_increment
        primary key,
    email      varchar(255) null,
    first_name varchar(255) null,
    last_name  varchar(255) null
);

create table orders
(
    id                    bigint auto_increment
        primary key,
    date_created          datetime(6)    null,
    last_updated          datetime(6)    null,
    order_tracking_number varchar(255)   null,
    status                varchar(255)   null,
    total_price           decimal(19, 2) null,
    total_quantity        int            null,
    customer_id           bigint         null,
    shipping_address_id   bigint         null,
    constraint FK624gtjin3po807j3vix093tlf
        foreign key (customer_id) references customer (id),
    constraint FKh0uue95ltjysfmkqb5abgk7tj
        foreign key (shipping_address_id) references address (id)
);

create table order_item
(
    id         bigint auto_increment
        primary key,
    image_url  varchar(255)   null,
    product_id bigint         null,
    quantity   int            null,
    unit_price decimal(19, 2) null,
    order_id   bigint         null,
    constraint FKt4dc2r9nbvbujrljv3e23iibt
        foreign key (order_id) references orders (id)
);

create table product_category
(
    id            bigint auto_increment
        primary key,
    category_name varchar(255) null
);

create table product
(
    id           bigint auto_increment
        primary key,
    date_created datetime(6)    null,
    description  varchar(510)   null,
    image_url    varchar(255)   null,
    last_updated datetime(6)    null,
    name         varchar(255)   null,
    unit_price   decimal(19, 2) null,
    category_id  bigint         not null,
    constraint FK5cypb0k23bovo3rn1a5jqs6j4
        foreign key (category_id) references product_category (id)
);

create table region
(
    id         int auto_increment
        primary key,
    name       varchar(255) null,
    country_id int          null,
    constraint FK7vb2cqcnkr9391hfn72louxkq
        foreign key (country_id) references country (id)
);

