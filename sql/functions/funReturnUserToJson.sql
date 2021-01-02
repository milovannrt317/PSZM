CREATE DEFINER=`remoteroot`@`%` FUNCTION `funReturnUserToJson`(usrnm varchar(30)) RETURNS text CHARSET latin1
BEGIN

declare jsonUser text;

select concat('{"firstname": "', first_name, '" , "lastname": "', last_name, '" , "email": "', email, '" , "username": "', username, '"}')
into jsonUser
from login.USER
where username=usrnm;

RETURN jsonUser;
END