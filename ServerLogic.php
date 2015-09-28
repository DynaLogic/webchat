<?php
/*
ServerLogic.php (c) 2014-2015 L. Waldman

####################
#  Valid Requests  #
####################

find_user 	: call to find if a username is taken; returns {"user_found":true} if user is connected, false otherwise
list_users 	: call to return a list of all users connected
poll_log 	: call to return the complete log
update_log 	: call to add text to the log
user_join 	: call when a user joins; adds '[user] Joined the conversation' to the log
user_leave 	: call when a user leaves; adds '[user] Left the conversation' to the log

*/
date_default_timezone_set('America/New_York');
require __DIR__ . "/vendor/autoload.php";
$redis = new Predis\Client();
$redis->connect();


session_start();
$time = time();
require_once(dirname(__FILE__) . "/Server-Config.php");

switch ($_POST["request"])
	{
	case 'update_log': //update_log request
		$formatText = date("U", $time) . " | " . $_POST["name"] . " : " . $_POST["text"] . " \n ";
		$out = appendLog($formatText);
		$return = array("return" => $out);
		break;
	
	case 'poll_log': //poll_log request
		$out = file_get_contents("./log.txt" , true);
		$return = array("log" => $out);
		break;
	
	case 'find_user': //find_user request
		$users = $redis->smembers("chat:users");
		for($x = 0; $x < count($users); $x++)
		{
			if($users[$x] == $_POST["user_name"])
			{
				$return = array("user_found" => true);
			}else
			{
				$return = array("user_found" => false);
			}
		}
		break;
	
	case 'user_join': //user_join request
		appendLog($_POST["name"] . " Joined The Conversation\n");
		$redis->sadd("chat:users",$_POST["name"] );
		$return = array("return" => "sucess");
		break;
	
	case 'user_leave': //user_leave request
		$redis->srem("chat:users",$_POST["name"]);
		appendLog($_POST['name'] . " Left the Conversation\n");
		$return = array("return" => "sucess");
		break;
	
	case 'list_users':
		$users = $redis->smembers("chat:users");
		$return = array("users" => $users);
		break;
	
	default:
		$return = array("error" => "bad call");
	
	
}

echo json_encode($return);

function appendLog($logLine) {
	$out = file_put_contents(CHAT_LOG_NAME, $logLine, FILE_APPEND | LOCK_EX);
	return $out;
}
?>
