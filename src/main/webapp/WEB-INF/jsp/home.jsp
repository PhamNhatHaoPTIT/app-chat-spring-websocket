<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sping" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Home | Zola Chat</title>
    <link href="static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/home.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Vollkorn&display=swap" rel="stylesheet">
</head>
<body>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/ChatWithWebSocket/">Zola  Chat</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/ChatWithWebSocket/?language=en">English</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/ChatWithWebSocket/?language=vi">Tiếng Việt</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Header - set the background image for the header in the line below -->
<header class="py-5 bg-image-full" style="background-image: url('static/images/bg.jpg');">
    <section class="py-5">
        <div class="container">
            <h1><spring:message code="label.heading" /></h1>
            <p class="lead"><spring:message code="label.p" /></p>
            <p><spring:message code="label.feature" /></p>
            <ul>
                <li><spring:message code="label.l1" /></li>
                <li><spring:message code="label.l2" /></li>
                <li><spring:message code="label.l3" /></li>
                <li><spring:message code="label.l4" /></li>
                <li><spring:message code="label.l5" /></li>
                <li><spring:message code="label.l6" /></li>
                <li><spring:message code="label.l7" /></li>
            </ul>
        </div>
    </section>
    <a href="/ChatWithWebSocket/Login"><button type="button" class="btn btn-light btn-login"><spring:message code="btn.get" />
    </button></a>
</header>

<!-- Content section -->

<!-- Footer -->
<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white"><spring:message code="label.author" /></p>
    </div>
    <!-- /.container -->
</footer>

<script src="static/vendor/jquery/jquery.min.js"></script>
<script src="static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
