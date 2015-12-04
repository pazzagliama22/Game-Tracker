<%-- 
    Document   : updateRecord2
    Created on : Nov 3, 2015, 8:54:49 PM
    Author     :    MP
--%>

<%@page import="model.VideoGame" %>
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
        <h1><a href="home.html">Video Games </a></h1>
        <h2>Update Game</h2>
        <form action="update" method="get">
            <% VideoGame game = (VideoGame) request.getAttribute("game");%>
            Game ID: <input type="text" name="id" value="<%= game.getId() %>" readonly>
            <br>
            Name: <input type="text" name="name" size="50" value="<%= game.getGameName() %>" required>
            <br>
            Rating: <input type="text" name="rating" size="50" value="<%= game.getGameRating() %>" required>
            <br>          
            Date: <input type="text" name="date" size="50" value="<%= game.getReleaseDate() %>" required>
            <br>
            Completion: <input type="text" name="completion" size="50" value="<%= game.getTimeToComplete() %>" required>
            <br>
            Comments: <input type="text" name="comments" size="50" value="<%= game.getGameComments() %>">
            <br>
            <br><br>

            <input type="hidden" name="action" value="updateRecord2">

            <input type="submit" name="submit" value="Submit">
            <br><br>
        </form>
    </body>
</html>
