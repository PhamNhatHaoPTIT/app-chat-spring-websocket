
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Chat</title>
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/login.css" rel="stylesheet" >
</head>
<body style="background-color:#00B4EF;">
<div class="container">
    <div class="row" style="margin-top: 10%;">
        <div class="col-md-offset-3 col-md-6">
            <form class="form-horizontal">
                <span class="heading"> <spring:message code="label.login" /> </span>
                <div class="form-group">
                    <input type="text" class="form-control" id="userName" maxlength="20"
                            placeholder=<spring:message code="label.username" /> >
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group help">
                    <input type="password" class="form-control" id="password" maxlength="20"
                            placeholder=<spring:message code="label.password" /> >
                    <i class="fa fa-lock"></i>
                    <a href="#" class="fa fa-question-circle"></a>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Captcha" style="width: 290px;float: left;" id="captcha" maxlength="6"><img src="captcha" class="update-captcha" style="display: inline-block;float: right;">
                </div>
                <div class="form-group">
                    <span class="text"><a href="register"><spring:message code="btn.register" /></a></span>
                    <button type="button" class="btn btn-default" id="userLogin"><spring:message code="btn.login" /></button>
                </div>
                <div class="form-group">
                    <span class="msgtips" id="msgtips" style="color: red; font-size: 16px;"></span>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/js/login.js"></script>
</body>
</html>
