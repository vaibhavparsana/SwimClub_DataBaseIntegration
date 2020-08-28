<%-- 
    Document   : input.jsp
    Created on : 12-Nov-2019, 2:16:11 PM
    Author     : Vaibhav
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Swimming Club</title>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
    <center> 

        <h1>MISSISSAUGA SWIMMING CLUB </h1><br>
        <h3> Welcome to Swim CLub</h3>
    </center>
    <form method="Post" action="SwimClub">
        Name: <input type="text" name="name"><br><br>
        Age: <input type="text" name="age"><br><br>
        Contact: <input type="text" name="contact"><br><br>
        Address: <input type="text" name="address"><br><br>
        Time: <select name="time">
            <option value="Morning">Morning</option>
            <option value="AfterNoon">AfterNoon</option>
            <option value="Evening">Evening</option>
        </select><br><br>
        <div id="pack">
            Package:<br><br> <input type="radio" name="pack" value="General"> General Training - 250$/year<br><br>
            <input type="radio" name="pack" value="Personal"> Personal Training - 500$/year<br><br>

        </div><br><br>
        <input type="submit" value="Submit" name="Submit">
        <input type="reset" value="Reset" name="Reset">
        <button type="Button" autofocus onclick="location.href='index.jsp'"> Main Page</button>   
    </form>
</body>
</html>
