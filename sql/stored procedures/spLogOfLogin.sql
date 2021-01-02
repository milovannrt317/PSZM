CREATE DEFINER=`remoteroot`@`%` PROCEDURE `spLogOfLogin`(in usrnm varchar(30), in succ tinyint(1))
BEGIN

declare idUsr integer;

select id_user
into idUsr
from login.USER
where username=usrnm;

insert into login.LOG_OF_LOGIN(id_user,success)
values(idUsr,succ);

END