CREATE DEFINER=`remoteroot`@`%` PROCEDURE `spLockAcc`(usrnm varchar(30), mins integer)
BEGIN
declare idUsr integer;

select id_user
into idUsr
from login.USER
where username=usrnm;

insert into login.LOCKED_ACCOUNTS(id_user,end_time)
values(idUsr, DATE_ADD(sysdate(), INTERVAL mins MINUTE));

END