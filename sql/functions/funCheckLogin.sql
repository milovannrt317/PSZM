CREATE DEFINER=`remoteroot`@`%` FUNCTION `funCheckLogin`(usrnm varchar(30), hashedPass varchar(512)) RETURNS int(11)
BEGIN

declare pass varchar(512);
declare cmp integer;

select password
into pass
from login.USER
where username=usrnm;

if strcmp(pass,hashedPass)=0 then
	set cmp=1;
else
	set cmp=0;
end if;

RETURN cmp;
END