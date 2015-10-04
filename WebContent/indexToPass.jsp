<%@page language="java" import="com.course.*"%>

<html>
<head>
<style>
#MinButton {
	left: 70%;
	position: relative;
	text-align: center;
	bottom: 90px !important;
}

#err {
	left: 3%;
	position: relative;
	text-align: center;
	bottom: 3px !important;
}

#AddButton {
	left: 8%;
	position: relative;
	text-align: center;
	bottom: 90px !important;
}

#statusValue {
	left: 40%;
	position: relative;
	text-align: center;
	bottom: 90px !important;
	border: solid 3px blue;
}

#abc {
	position: relative;
	left: 50%;
	top: 180px;
	margin-left: -100px;
	margin-top: -50px;
}
</style>
<script type="text/javascript">

	function loadXMLDoc() {
		

		var xmlhttp;
		var k = document.getElementById("TextBox").value;
		var urls = "pass.jsp?ver=" + k;
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				document.getElementById("err").innerHTML = xmlhttp.responseText;
			}
		}
		xmlhttp.open("GET", urls, true);
		xmlhttp.send();
	}
</script>
</head>
<%
	String courseId = session.getAttribute("cid").toString();
	String pictureCount = PictureCount.getPictureCount(courseId);
	System.out.println(pictureCount);
	session.setAttribute("maxPictureCount", pictureCount);
%>
<body bgcolor="#94D4E9">
	<script src="http://code.jquery.com/jquery-1.4.3.min.js"></script>
	<script type="text/javascript">
	</script>
	<script>
		var counter = 1;
		$(document).ready(function() {
			$('#AddButton').click(function() {
				counter = $('#TextBox').val();
				counter++;
				 document.getElementById('statusValue').value = counter-1+" of <%=session.getAttribute("maxPictureCount")%>";
				if (counter > 1) {
					document.getElementById("MinButton").disabled = false;
					document.getElementById('abc').style.visibility = 'hidden';
					
				}
				if(counter-1 == <%=session.getAttribute("maxPictureCount")%>)
			{
				document.getElementById("AddButton").disabled = true;
			}
				$('#TextBox').val(counter);
				});
		});
		$(document).ready(function() {
	  		  $('#MinButton').click(function() {
				counter = $('#TextBox').val();
				document.getElementById('statusValue').value = counter- 1+ " of <%=session.getAttribute("maxPictureCount")%>";		counter--;
		if (counter < 1) {
		document.getElementById("MinButton").disabled = true;
		document.getElementById('abc').style.visibility = 'visible';
		}
		if (counter - 1 <= <%=session.getAttribute("maxPictureCount")%>) {
	   document.getElementById("AddButton").disabled = false;
		}
		$('#TextBox').val(counter);
		});
		});
	</script>
	<table id="abc" border=1; align:center>
		<tr>
			<td>ADADADA</td>
			<td>ADADADA</td>
		</tr>
		<tr>
			<td>ADADADA</td>
			<td>ADADADA</td>
		</tr>

		<tr>
			<td>ADADADA</td>
			<td>ADADADA</td>
		</tr>

		<tr>
			<td>ADADADA</td>
			<td>ADADADA</td>
		</tr>

		<tr>
			<td>ADADADA</td>
			<td>ADADADA</td>
		</tr>


	</table>

	<span id="err"> </span>
	<span id="TextBox"></span>
	<input type="Button" id='AddButton' value="Next" onclick="loadXMLDoc()" />
	<input type="text" id='statusValue' readonly="readonly" />
	<input type="Button" id='MinButton' value="Previous" onclick="loadXMLDoc()" />
</body>
</html>