CREATE DEFINER=`remoteroot`@`%` FUNCTION `funCountFailedLogins`(usrnm varchar(30)) RETURNS int(11)
BEGIN

declare fails INTEGER;
declare lastLogin DATETIME;
set lastLogin=login.funFindLastSuccessLogin(usrnm);
if lastLogin is null then
set lastLogin=login.funFindFirstLogin(usrnm);
end if;

select count(*)
into fails
from login.LOG_OF_LOGIN
where time_of_login>lastLogin and success=0 and id_user=(select id_user from login.USER where username=usrnm);

RETURN fails;
END