create user book_shop identified by book

/

grant connect,dba to book_shop;

/

conn book_shop/book

/

drop table ADMIN_DETAILS
/
drop table EMPLOYEE_DETAILS
/
drop table BOOK_DETAILS
/
drop table SALE
/
drop TABLE AUTHOR_DETAILS
/
drop TABLE PUBLICATION_DETAILS
/
COMMIT;
/
QUIT
/





