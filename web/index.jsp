<%-- 
    Document   : index
    Created on : Jan 9, 2018, 1:05:14 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/fontawesome.css" integrity="sha384-q3jl8XQu1OpdLgGFvNRnPdj5VIlCvgsDQTQB6owSOHWlAurxul7f+JpUOVdAiJ5P" crossorigin="anonymous">
        <title>Index</title>
    </head>
    <body>
        <div class="container">      
            <br/>
            <div style="border:1px solid black; border-radius: 30px ">
                <jsp:include page="Banner.jsp"/>
            </div>
            <br/>
            <div class="row" style="height:500px">
                <div class="col-md-2" style="border:1px solid black;border-right:5px"> <jsp:include page="Menu.jsp"/></div>
                <div class="col-md-10" style="border:1px solid black;"> <jsp:include page="Content.jsp"/></div>
            </div>
        </div>
    </body>
</html>
