package models;

import java.sql.Timestamp;

public class Review {
    private String username;
    private int rating;
    private String comment;
    private Timestamp reviewDate;

    public Review(String username, int rating, String comment, Timestamp reviewDate) {
        this.username = username;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public String getUsername() { return username; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public Timestamp getReviewDate() { return reviewDate; }
}