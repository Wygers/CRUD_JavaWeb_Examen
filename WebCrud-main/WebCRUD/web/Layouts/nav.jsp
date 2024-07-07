<%-- 
    Document   : nav
    Created on : 31-05-2024, 00:37:27
    Author     : jr972
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <nav class="cyan">
                <div class="nav-wrapper">
                    <div class="container">
                        <a href="#" class="brand-logo">LOGO</a>
                        <a href="#" data-activates="mobile-menu" class="button-collapse"><i class="material-icons">menu</i></a>
                        <ul class="right hide-on-med-and-down">
                            <li><a href="#">item1</a></li>
                            <li><a href="#">item2</a></li>
                            <li><a href="#">item3</a></li>
                            <li><a href="#">item4</a></li>
                        </ul>
                        <ul class="side-nav" id="mobile-menu">
                            <li>
                                <div class="userView">
                                    <div class="background">
                                        <img src="http://lorempixel.com/output/abstract-q-c-640-480-10.jpg" alt="Background Sidenav">
                                    </div>
                                    <a href="#!user"><img class="circle" src="http://lorempixel.com/output/people-q-c-640-480-9.jpg" alt="User Image Sidenav"></a>
                                    <a href="#!name"><span class="white-text name">John Doe</span></a>
                                    <a href="#!email"><span class="white-text email">jdandturk@gmail.com</span></a>
                                </div>
                            </li>
                            <li><a href="#">item1</a></li>
                            <li><a href="#">item2</a></li>
                            <li><a href="#">item3</a></li>
                            <li><a href="#">item4</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
    </body>
</html>
