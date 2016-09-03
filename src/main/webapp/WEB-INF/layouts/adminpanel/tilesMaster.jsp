<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>

<meta http-equiv="content-type" content="text/html;charset=utf-8" />

<title>Radyo Dinlenir - Admin PAnel</title>

<meta name="viewport" 	content="width=device-width, initial-scale=1, maximum-scale=1" />
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->

<script src="<c:url value="/resources/adminPanelAssets/js/jquery-1.11.1.js" />"></script>
<link href='<c:url value="/resources/adminPanelAssets/css/bootstrap.css" />' rel="stylesheet" />
<link href='<c:url value="/resources/adminPanelAssets/css/font-awesome.css" />'	rel="stylesheet" />
<link href='<c:url value="/resources/adminPanelAssets/css/style.css" />'	rel="stylesheet" />
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<tiles:insertAttribute name="header" />
	<div class="content-wrapper">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1 class="page-head-line"><tiles:insertAttribute name="title" /></h1>
				</div>
			</div>
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>