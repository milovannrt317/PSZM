CREATE DEFINER=`remoteroot`@`%` FUNCTION `funFindFirstLogin`(usrnm varchar(30)) RETURNS datetime
BEGIN

declare firstDate DATETIME;

select min(time_of_login)
into firstDate
from login.LOG_OF_LOGIN
where id_user=(select id_user from login.USER where username=usrnm);

RETURN firstDate;
END