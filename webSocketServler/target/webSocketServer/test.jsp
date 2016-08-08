<%--
  Created by IntelliJ IDEA.
  User: 青蜂侠
  Date: 2016/8/8
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset=UTF-8>
    <title>Tomcat WebSocket Chat</title>
    <script>
        var ws = new WebSocket("ws://localhost:8080/wsServlet");
        ws.onopen = function(){
        };
        ws.onmessage = function(message){
            document.getElementById("chatlog").textContent += message.data + "\n";
        };
        function postToServer(){
            ws.send(document.getElementById("msg").value);
            document.getElementById("msg").value = "";
        }
        function closeConnect(){
            ws.close();
        }
    </script>
</head>
<body>
<textarea id="chatlog" readonly></textarea><br/>
<input id="msg" type="text" />
<button type="submit" id="sendButton" onClick="postToServer()">Send!</button>
<button type="submit" id="close" onClick="closeConnect()">End</button>
</body>
</html>


</body>
</html>
