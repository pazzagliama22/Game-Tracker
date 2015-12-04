<%-- 
    Document   : displayRecords
    Created on : Nov 3, 2015, 4:52:40 PM
    Author     : MP

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
        <h2>Recorded Game</h2>
        <%
            List<VideoGame> mydata = (List<VideoGame>) request.getAttribute("mydata");
            out.println("<table>");
            out.println("<tr><td>Name</td><td>Rating</td><td>Date</td><td>Completion</td><td>Comments</td></tr>\n");
            for (VideoGame game : mydata) {
                out.println(game.inHTMLRowFormat(0));
            }
            out.println("</table>");
        %>
    </body>
</html>