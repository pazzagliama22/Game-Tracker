package model;

import java.io.Serializable;

/**
 *
 * @author John Phillips
 */
public class VideoGame implements Serializable {

    private int Id;
    private String GameName;
    private String GameRating;
    private String ReleaseDate;
    private String TimeToComplete;
    private String GameComments;

    public VideoGame() {
        Id = 0;
        GameName = "";
        GameRating = "";
        ReleaseDate = "";
        TimeToComplete = "";
        GameComments = "";
    }

    public VideoGame(int id, String name, String rating, String date, String completion, String comments) {
        this.Id = id;
        this.GameName = name;
        this.GameRating = rating;
        this.ReleaseDate = date;
        this.TimeToComplete = completion;
        this.GameComments = comments;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getGameName() {
        return GameName;
    }

    public void setGameName(String GameName) {
        this.GameName = GameName;
    }

    public String getGameRating() {
        return GameRating;
    }

    public void setGameRating(String rating) {
        this.GameRating = GameRating;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String ReleaseDate) {
        this.ReleaseDate = ReleaseDate;
    }

    public String getGameComments() {
        return TimeToComplete;
    }

    public void setGameComments(String GameComments) {
        this.TimeToComplete = GameComments;
    }
    
    public String getTimeToComplete() {
        return TimeToComplete;
    }

    public void setTimeToComplete(String completion) {
        this.TimeToComplete = completion;
    }
    
    public String inHTMLRowFormat(int slot) {
        // Initialize.
        String tempAdd = "";
        String[] tempDate = getReleaseDate().split(":");
       
        // Table string.
        if (slot == -1) tempAdd = "<td>#" + Id + "</td>";
        else if (slot == 1) tempAdd = "<td><input type='radio' name='momentId' value=" + Id + " required></td>";
        
        // Returning.
        return("<tr>" + tempDate + "<td>" + GameName + "</td><td>" + GameRating + "</td><td>" + ReleaseDate + "</td><td>" + TimeToComplete + "</td><td>" + GameComments + "</td></tr>\n");
    }

    @Override
    public String toString() {
        return "VideoGame{ Id = " + Id + ", name = " + GameName + ", rating = " + GameRating + ", date = " + ReleaseDate + ", completion = " + TimeToComplete + ", comments = " + GameComments + '}';
    }
}