<%-- 
    Document   : updateRecord1
    Created on : Nov 3, 2015, 8:37:40 PM
    Author     : John Phillips
--%>

<%@page import="java.util.List, model.VideoGame"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Video Game Tracker</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mystyle.css">
    </head>
    <body>
        <h1><a href="home.html">Video Game Tracker</a></h1>
        <h2>Update Game</h2>
        <form action="update" method="get">
            <%
                List<VideoGame> mydata = (List<VideoGame>) request.getAttribute("mydata");
                out.println("<table>");
                out.println("<tr><td>Select</td><td>NAME</td><td>RATING</td><td>DATE</td><td>COMPLETION</td><td>COMMENTS</td></tr>\n");
                for (VideoGame game : mydata) {
                    out.println(game.inHTMLRowFormat(1));
                }
                out.println("</table>");
            %>
            
            <input type="hidden" name="action" value="updateRecord1">
            
            <input type="submit" name="submit" value="Submit">
            <br><br>
        </form>
    </body>
</html>
