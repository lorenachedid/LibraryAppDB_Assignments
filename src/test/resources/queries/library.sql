select * from book_categories;

SELECT COUNT(id) FROM users; -- actual

SELECT COUNT( DISTINCT id) FROM users;

SELECT full_name, b.name, bb.borrowed_date from users u join book_borrow bb on u.id = bb.user_id
join books b on bb.book_id = b.id
where full_name = 'Test Student 55' and name = 'Lorena Book'
order by  3 desc ;

select * from users;

select count(*) from book_borrow where is_returned=0;

select name from book_categories;

select * from books where name ='Lorena Book';

select bc.name ,count(*) from  book_borrow bb join books b on b.id = bb.book_id
    join book_categories bc on b.book_category_id = bc.id
    group by name
    order by 2 desc ;

select name, isbn,year, author from books
where name = 'Head First Java';
