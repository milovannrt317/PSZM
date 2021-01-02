CREATE DEFINER=`remoteroot`@`%` FUNCTION `funFindLastSuccessLogin`(usrnm varchar(30)) RETURNS datetime
BEGIN

declare lastDate DATETIME;

select max(time_of_login)
into lastDate
from login.LOG_OF_LOGIN
where success=1 and id_user=(select id_user from login.USER where username=usrnm);

RETURN lastDate;
END