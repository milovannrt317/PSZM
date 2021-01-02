CREATE DEFINER=`remoteroot`@`%` PROCEDURE `spCheckRecoveryPass`(in usrnm varchar(30), in rcvpass varchar(512), out msg text)
BEGIN

declare pass varchar(512);

select recovery_pass
into pass
from login.USER
where username=usrnm and recovery_pass is not null;

if pass is not null then
	if strcmp(pass,rcvpass)=0 then
		set msg="Success!";
	else
		set msg="Fail!";
	end if;
else
	set msg="No recovery password for this user";
end if;
END