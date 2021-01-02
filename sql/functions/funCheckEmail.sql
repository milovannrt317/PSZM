CREATE DEFINER=`remoteroot`@`%` FUNCTION `funCheckEmail`(eml varchar(30)) RETURNS int(11)
BEGIN

declare chk INTEGER;

select count(*)
into chk
from login.USER
where email=eml;

RETURN chk;
END