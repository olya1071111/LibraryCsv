create table author
(
    id       int          not null primary key GENERATED ALWAYS AS IDENTITY,
    fullName varchar(256) not null
);

create table genre
(
    id        int          not null primary key GENERATED ALWAYS AS IDENTITY,
    genreName varchar(256) not null
);

create table book2
(
    id        int          not null primary key GENERATED ALWAYS AS IDENTITY,
    bookName  varchar(256) not null,
    year      int,
    author_id int,
    genre_id  int,

    CONSTRAINT FK_AUTHOR FOREIGN KEY (author_id) REFERENCES author (id),
    CONSTRAINT FK_GENRE FOREIGN KEY (genre_id) REFERENCES genre (id)
);

create table shop
(
    id       int          not null primary key GENERATED ALWAYS AS IDENTITY,
    shopName varchar(256) not null,
    website  varchar(256) not null
);

create table availabilityBook
(
    id      int not null primary key GENERATED ALWAYS AS IDENTITY,
    shop_id int,
    book_id int,
    price   varchar(256) not null,

    CONSTRAINT FK_SHOP FOREIGN KEY (shop_id) REFERENCES shop (id),
    CONSTRAINT FK_BOOK FOREIGN KEY (book_id) REFERENCES book2 (id)
);