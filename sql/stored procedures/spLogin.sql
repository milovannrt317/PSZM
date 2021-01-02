CREATE DEFINER=`remoteroot`@`%` PROCEDURE `spLogin`(in usrnm varchar(30), in pass varchar(512), out msg text)
BEGIN

if login.funCheckLockedUser(usrnm)=0 then
	if login.funCheckRecoveryPass(usrnm)=0 then
		if login.funCheckLogin(usrnm,pass)=1 then
			set msg=CONCAT("Success#", login.funReturnUserToJson(usrnm));
			call login.spLogOfLogin(usrnm,1);
		else
			call login.spLogOfLogin(usrnm,0);
            set @CountFailed=login.funCountFailedLogins(usrnm);
			if @CountFailed<3 then
				set msg="Wrong password";
			elseif (@CountFailed>=3 and @CountFailed<5) then
				set msg="Captcha";
			elseif (@CountFailed>=5 and @CountFailed<7) then
				set msg="Account lock";
				call login.spLockAcc(usrnm,5);
			elseif @CountFailed>=7  then
                set @recpass = '0';
                call login.spCreateRecoveryPass(usrnm,@recpass);
                set msg=CONCAT("Recovery password#", @recpass,";",login.funGetEmail(usrnm));
			end if;
		end if;
	else 
		set msg="User account have recovery pass sent to email address!";
    end if;
else
	 set msg=concat("User account is locked! Remaining time is ", login.funReturnLockedTime(usrnm), " (HH:MM:SS)");
end if;
END