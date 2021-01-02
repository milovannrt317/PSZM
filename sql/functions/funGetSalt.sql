CREATE DEFINER=`remoteroot`@`%` FUNCTION `funGetSalt`(usrnm varchar(30)) RETURNS varchar(120) CHARSET latin1
BEGIN

declare slt varchar(120);
select salt 
into slt
from login.USER
where username=usrnm;

RETURN slt;
END