CREATE DEFINER=`remoteroot`@`%` PROCEDURE `spCreateRecoveryPass`(IN usrnm varchar(30), OUT pass varchar(512))
BEGIN

set pass=sha2(concat(usrnm,sysdate(),RAND()*1000000),256);

update login.USER
set recovery_pass=pass
where username=usrnm;

END