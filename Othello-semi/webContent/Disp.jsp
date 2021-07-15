<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "model.Judg" %>

<%
	Judg judg = (Judg) application.getAttribute("judg");

	boolean bool = false;
	int checkbord[] = new int[64];
	int bord[][] = new int[8][8];
	int k = 0;
	int black;
	int white;
	String winer  = null;

	black =judg.CHblack();
	white = judg.CHwhite();

	if(judg.getPass() == 2) {
		bool = true;
		if(black > white) {
			winer = "黒";
		}else {
			winer = "白";
		}
	}
	else if(judg.getPass() == 3) {
		bool = true;
		if(judg.getRev_stone() == 1) {
			winer = "黒";
		}else if(judg.getRev_stone() == 2) {
			winer = "白";
		}
	}

    bord = judg.getCheck_board();

 	for(int i = 0; i< 8 ; i++) {
 		for( int j = 0; j< 8; j++) {
 			checkbord [k] = bord[i][j];
 			k++;
 		}
 	}
 %>

<!DOCTYPE html>
<head>
     <meta charset="UTF-8">
     <title>osero</title>
     <link rel="stylesheet" href="Othello.css">
</head>
<body>

	<div id ='popup'>

<div class="pop-box">
<label for="popup-on">
<div class="btn-open">
<%if(bool == true) { %>
<input type="hidden" id ="kekka" class="layer-img">

<%} %>
</div>
</label>


<div class="left_wrap">



  <input type="checkbox" id="popup-on">
  <div class="popup">

    <div class="popup-content">
	  		<h1>ゲーム終了</h1>
	  		<p>黒　：　<%= black%></p>
	  		<p>白　：　<%=white %></p>
	  		<p><%= winer %>の勝ち</p>
    </div>
    <label for="popup-on"><div class="btn-close"><a href="http://172.16.4.165:8080/Othello/index.jsp">ホームに戻る</a></div></label>
  </div>
</div>
</div>
</div>

<div class="right_wrap">
<p><% if(judg.getStone() == 1) {%>
黒の手番です
<%} else if (judg.getStone() == 2) {%>
白の手番です
<%} %></p>




<form action="/Othello/Main" method="post">
	<input type ='hidden' id = 'input' name ='no' value ='500'>
	<input type="submit"  id="submit" value = "降参">
</form>


</div>







<form action="/Othello/Main" method="post">
    <table id = field></table>
    <input type ='hidden' id = 'input' name ='no' value ='0'>
    <input type="submit"  id="submit"  style="display:none;"/>
  </form>


  <script>


     <%if(bool == true) { %>
     document.getElementById("kekka").click();

     <%} %>
     // 盤のこすう
        var area = new Array(64);
       <% for(int i = 0 ; i < 64; i++) { %>
            var td = document.createElement('td');
            td.id =<%= i %>;
            td.className = 'a'  + <%=checkbord[i] %>;
            console.log(<%= i %>);
            console.log(td.className);
//            td.className = "target";
            area [<%= i %>] = td;
       <% } %>

        for(var i = 0 ; i < 64; i++) {
            if (i % 8 == 0) {
                var tr = document.createElement('tr');
                field.appendChild(tr);
            }
            tr.appendChild(area[i]);
        }

        const target = document.querySelectorAll('.a3');
        for (let i = 0; i < target.length; i++) {

        	//クリックイベントを追加
        	target[i].addEventListener('click', (event) => {
        		//idを取得してログに出力する
        		console.log(event.target.id+ `をクリックしました。`);
        		console.log( event.target.className + `をクリックしました。`);
        		//event.target.className = 'a2';

        		document.getElementById('input').setAttribute("value",event.target.id);
            	console.log(document.getElementById('input').getAttribute('value'));
            	document.getElementById("submit").click();

        	}, false);
       // console.log(document.getElementById('input').getAttribute('value'));
        }
        var settest = function() {
            location = "Disp.jsp";
        }
        setTimeout(settest,30000000);
     </script>
</body>
</html>

