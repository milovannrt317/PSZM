CREATE DEFINER=`remoteroot`@`%` PROCEDURE `spDeleteRecoveryPass`(usrnm varchar(30))
BEGIN

update login.USER
set recovery_pass=null
where username=usrnm;

END