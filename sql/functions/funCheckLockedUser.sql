CREATE DEFINER=`remoteroot`@`%` FUNCTION `funCheckLockedUser`(usrnm varchar(30)) RETURNS int(11)
BEGIN

declare lckd integer;
declare nowDate DATETIME;

set nowDate=sysdate();

select count(*)
into lckd
from login.LOCKED_ACCOUNTS
where id_user= (select id_user from login.USER where username=usrnm) and start_time<=nowDate and end_time>nowDate;

RETURN lckd;
END