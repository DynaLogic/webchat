var logText, name, text, consoleOut, parsedCommand, commandArgs,userList;
var logUpdateMultiplier = 2;
var nameSelected = false;
var consoleSplash = 
	"#######################################################################################\n#######################################################################################\n##  _                                          _    _                                ##\n## | |   ___  __ _ __ _ _ _       __ __ ____ _| |__| |_ __  __ _ _ _        _ _ ___  ##\n## | |__/ _ \\/ _` / _` | ' \\   _  \\ V  V / _` | / _` | '  \\/ _` | ' \\   _  | '_/ _ \\ ##\n## |____\\___/\\__, \\__,_|_||_| (_)  \\_/\\_/\\__,_|_\\__,_|_|_|_\\__,_|_||_| (_) |_| \\___/ ##\n##           |___/                                                                   ##\n##                                                                                   ##\n#######################################################################################\n#######################################################################################";
console.log(consoleSplash);

$(
	function() 
	{
		$('html, body').scrollTop( $(document).height() );
		$("#submit").click(function()
		{
			text = $("#text").val();
			
			if (text !== "")
				{
					$("#text").val("");
					console.log(text);
					if (text.lastIndexOf("/",0) === 0)
						{
							commandPreProccesor(text.slice(1,text.length));
						} else {
							$.post("ServerLogic.php",{name: name,text: text,request: "update_log"});
						}
					
				}
			
			updateClientLog();
		});
		$("#update").click(updateClientLog);
		$("#selectUserName").click(function() {
			//console.log("hem");
			$.post("ServerLogic.php",{request: "find_user",user_name: $("#name").val()}).done(function(data) {
				logText = JSON.parse(data);
						console.log(data);
						name = $("#userName").val();
						$("#UserName").val("");
						$("#login").fadeOut();
						$("#main").fadeIn();
						nameSelected = true;
						$.post("ServerLogic.php",{name : name, request : "user_join"});
						updateClientLog();
			});
		});
		setInterval(updateClientLog,logUpdateMultiplier*1000);
		
		$("#userName").keypress(function(e)
		{
			if(e.which == 13) $("#selectuserName").trigger("click");
		});
	}
);


function updateClientLog()
{
	$('html, body').scrollTop( $(document).height() );
	if(nameSelected) {
		$.post("ServerLogic.php",{request: "poll_log"}).done(function(data){
			logText = JSON.parse(data);
			consoleOut = logText.log;
			consoleOut = consoleOut.replace(/\n/g , "<br />");
			$("#console").text("");
			$("#console").html(consoleOut);
			updateUserList();
		});
	}
}

function updateUserList()
{
	$.post("ServerLogic.php",{request:"list_users"}).done(function(data)
	{
		$("#userList").html("<h2>Users:</h2>");
		userList = JSON.parse(data);
		userList = userList.users;
		
		for(var itt=0; itt<userList.length; itt++)
		{
			$("#userList").append("<p>"+userList[itt]+"</p>");
			$("#userList").css("bottom","0");
		}
	});
}

window.onbeforeunload = function() {
	$.post("ServerLogic.php",{name : name, request : "user_leave"});
};

function commandPreProccesor(rawInput) {
	console.log("boop");
	var command = rawInput.slice(0,rawInput.indexOf(" "));
	var rawArgs = rawInput.slice(rawInput.indexOf(" ")+1,rawInput.length);
	var args = [];
	console.log((rawArgs.match(/ /g) || []).length);
	for(var itr; itr <= (rawArgs.match(/ /g) || []).length +1;itr++)
		{
			rawArgs = rawArgs.slice(rawArgs.indexOf(" "),rawArgs.length);
			args.push(0,rawArgs.indexOf(" ")+1);
			console.log(rawArgs);
		}
	console.log(args.length);
	for (var i; i <= args.length;i++)
		 {
		console.log(args[i] + ":");
		}
}

function commandHelp(args){
	
}