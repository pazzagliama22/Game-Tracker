<%-- 
    Document   : createRecord
    Created on : Nov 3, 2015, 5:19:26 PM
    Author     : MP
--%>

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
        <h2>Add New Game</h2>
        <form action="create" method="get">

            Name: <input type="text" name="name" size="50" placeholder="Name" required><br>
            Rating: <input type="text" name="rating" size="50" placeholder="Rating" required><br>
            Date: <input type="text" name="date" size="50" placeholder="Date" required><br>
            Completion: <input type="text" name="completion" size="50" placeholder="Enter the time it took to complete the game" required><br>
            Comments: <input type="text" name="comments" size="50" placeholder="Enter Commments about the game (optional)"><br>
            <br><br>

            <input type="hidden" name="action" value="createRecord">

            <input type="submit" name="submit" value="Submit">
            <br><br>
        </form>
    </body>
</html>

