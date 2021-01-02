CREATE DEFINER=`remoteroot`@`%` FUNCTION `funCheckUser`(usrnm varchar(30)) RETURNS int(11)
BEGIN

declare chk INTEGER;

select count(*)
into chk
from login.USER
where username=usrnm;

RETURN chk;
END