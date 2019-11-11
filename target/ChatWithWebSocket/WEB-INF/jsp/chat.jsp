<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Chat</title>
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/chat.css" rel="stylesheet" type="text/css">
</head>
<body style="background-color:#27ae60;">
<div class="container">
    <div class="row" style="margin-top: 5%;">
        <div class="col-md-offset-2 col-md-8">
            <div class="alert alert-success" style="display: none;" role="alert">

            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8 chatbox">
            <div class="sidepanel">
                <div class="wrap">
                    <img id="profile-img" src="http://emilcarlsson.se/assets/mikeross.png" class="online" alt="" />
                    <p>${user.userName}</p>
                </div>
                <div class="user-list" data-user-list-length="${fn:length(userList)}">
                    <c:forEach items="${userList}" var="user">
                    <span class="user message" data-user-list-id="${user.id}">
                        <img src="http://emilcarlsson.se/assets/rachelzane.png" alt="avatar" class="avatar"><span>${user.userName}</span>
                    </span>
                    </c:forEach>
                </div>
            </div>
            <div class="chat" data-user-id="${user_id}">
            </div>
            <div class="input-text">
                <textarea id="content" style="width: 89%; height: 87%;" class="form-control" placeholder="Enter your message" ></textarea>
                <button type="button" id="sendMessage" style="float: right;" class="btn btn-primary" data-to-user-id="">
                    <spring:message code="label.send" />
                </button>
            </div>
        </div>
        <div class="col-md-4 settingsbox">
            <div id="settings">
                <span class="add-friend span-select"> <spring:message code="label.add" /> </span>
                <span class="system-message"> <spring:message code="label.notification" /> </span>
            </div>
            <div id="add-user" style="display: block;">
                <span> <spring:message code="label.friend-name" /> </span>
                <input type="text" style="font-size: 12px; width: 140px; display: inline-block;" class="form-control" id="userName" maxlength="20"/>
                <button type="button" class="btn btn-primary" id="find-user"> <spring:message code="btn.search" /> </button>
                <div id="find-result-list">
                    <ul>
                    </ul>
                </div>
                <nav>
                    <ul class="add-friend-pager pager" data-current-page="1" data-total-page="1">
                        <li><a href="javascript:;" class="prev" style="display: none;"> <spring:message code="label.prev" /> </a></li>
                        <li><a href="javascript:;" class="next"> <spring:message code="label.next" /> </a></li>
                    </ul>
                    <ul class="add-friend-pager pager" style="margin-top: -10px;">
                        <li> <spring:message code="label.first" /> <span>1</span>/<span>3</span>
                            <spring:message code="label.page" />
                        </li>
                    </ul>
                </nav>
            </div>
            <div id="system-message" style="display: none;">
                <div>
                    <ul>

                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/chat.js" type="text/javascript"></script>
</body>
</html>