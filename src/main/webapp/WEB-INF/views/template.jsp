<%@ taglib uri = "http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page isELIgnored="false"%>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Default tiles template</title>
	<style type="text/css">
			body {
				margin: 0px;
				padding: 0px;
				height: 100%;
				overflow: hidden;
			}
			
			table.table1, th.table1, td.table1 {
				border: 1px solid black; 
				
 				border-spacing=0;	 
			}
			
			table.table2, th.table2, td.table2 {
				text-align: left; 
				
 				border-spacing=0;	 
			}
			
			td{
				text-align: center;
				height: 40px;
			}
			
			fontmenu{
				font-size: xx-large;
			}
			
			.page {
				min-height: 100%;
				position: relative;
			}
			.header {
				padding: 10px;
				width: 100%;
				text-align: center;
				font-size: xx-large;
			}
			.content {
				padding: 10px;
				padding-bottom: 20px;
				overflow: hidden;
			}
			.menu {
				padding: 50px 10px 0px 10px;
				width: 200px;
				float: left;
				font-size: x-large;
			}
			.body {
				margin: 50px 10px 0px 250px;
			}
			.footer {
				clear: both;
				position: absolute;
				bottom: 0;
				left: 0;
				text-align: center;
				width: 100%;
				height: 20px;
				font-size: x-small;
			}
		</style>
</head>
<body>
	<div class="page">
		<tiles:insertAttribute name="header" />
		<div class="content">
			<tiles:insertAttribute name="menu" />
			<tiles:insertAttribute name="body" />
		</div>
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>
				