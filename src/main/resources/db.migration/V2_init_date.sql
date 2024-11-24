insert into author(fullName)
values ('Ivan Ivanovich Sidoroy');
insert into author(fullName)
values ('Tom Dipper');
insert into author(fullName)
values ('Sergey Petrovich Pavlov');

insert into genre(genrename)
values ('novel');
insert into genre(genrename)
values ('drama');
insert into genre(genrename)
values ('comedy');
insert into genre(genrename)
values ('poem');

insert into shop(shopname, website)
values ('oz', 'oz.by');
insert into shop(shopname, website)
values ('belkniga', 'belkniga.by');
insert into shop(shopname, website)
values ('chitatel', 'chitatel.by');

insert into book(bookname, year, author_id, genre_id)
values ('Prizma', 2005, 1, 1);
insert into book(bookname, year, author_id, genre_id)
values ('Hope', 2012, 1, 2);
insert into book(bookname, year, author_id, genre_id)
values ('The experience of the future', 2020, 2, 3);
insert into book(bookname, year, author_id, genre_id)
values ('Substitution of concepts', 2012, 2, 2);
insert into book(bookname, year, author_id, genre_id)
values ('Does it work?', 2021, 3, 3);
insert into book(bookname, year, author_id, genre_id)
values ('Accident', 2021, 3, 4);

insert into availabilitybook(shop_id, book_id, price)
values (1, 1, '105');
insert into availabilitybook(shop_id, book_id, price)
values (2, 1, '115');
insert into availabilitybook(shop_id, book_id, price)
values (1, 2, '45');
insert into availabilitybook(shop_id, book_id, price)
values (2, 2, '50');
insert into availabilitybook(shop_id, book_id, price)
values (1, 3, '38');
insert into availabilitybook(shop_id, book_id, price)
values (2, 3, '33');
insert into availabilitybook(shop_id, book_id, price)
values (1, 4, '64');
insert into availabilitybook(shop_id, book_id, price)
values (2, 4, '66');