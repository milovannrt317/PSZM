CREATE DEFINER=`remoteroot`@`%` PROCEDURE `spChangePassword`(IN usrnm varchar(30), IN pass varchar(512), IN slt varchar(100), OUT msg text)
BEGIN

UPDATE login.USER
SET
password = pass,
salt = slt
WHERE username = usrnm;

if ROW_COUNT()=1 then
	set msg="Successfully updated!";
    call login.spDeleteRecoveryPass(usrnm);
elseif ROW_COUNT()=0 then
	set msg="Error updating!";
end if;

END