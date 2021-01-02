CREATE DEFINER=`remoteroot`@`%` PROCEDURE `spSignup`(IN usrnm varchar(30), IN eml varchar(50), IN pass varchar(512), IN slt varchar(100), IN fnm varchar(30), IN lnm varchar(30), OUT msg text)
BEGIN
declare proceedToSingup tinyint(1);
set proceedToSingup=1;

if login.funCheckUser(usrnm)=1 then
	set msg="Username already exist!";
	set proceedToSingup=0;
end if;

if login.funCheckEmail(eml)=1 then
	set msg="Email already exist!";
	set proceedToSingup=0;
end if;

if proceedToSingup=1 then
	INSERT INTO login.USER(first_name, last_name, email, username, password, salt)
	VALUES(fnm, lnm, eml, usrnm, pass, slt);
	if ROW_COUNT()=1 then
		set msg="Successfully added!";
	elseif ROW_COUNT()=0 then
		set msg="Error adding!";
    end if;
end if;

END