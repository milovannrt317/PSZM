CREATE DEFINER=`remoteroot`@`%` FUNCTION `funReturnLockedTime`(usrnm varchar(30)) RETURNS time
BEGIN

declare lckdTime time;
declare nowDate datetime;
set nowDate=sysdate();

select timediff(end_time,nowDate) 
into lckdTime
from login.LOCKED_ACCOUNTS
where id_user= (select id_user from login.USER where username=usrnm) and start_time<=nowDate and end_time>nowDate;

RETURN lckdTime;
END