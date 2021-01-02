CREATE DEFINER=`remoteroot`@`%` FUNCTION `funGetEmail`(usrnm varchar(30)) RETURNS varchar(50) CHARSET latin1
BEGIN

declare eml varchar(50);
select email 
into eml
from login.USER
where username=usrnm;

RETURN eml;
END