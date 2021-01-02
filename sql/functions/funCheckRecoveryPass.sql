CREATE DEFINER=`remoteroot`@`%` FUNCTION `funCheckRecoveryPass`(usrnm varchar(30)) RETURNS int(11)
BEGIN

declare chk INTEGER;

select count(*)
into chk
from login.USER
where username=usrnm and recovery_pass is not null;

RETURN chk;
END