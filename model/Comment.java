package org.example.model;

public class Comment {
    private final String hotelName;
    private final String reviewerName;
    private final String reviewTitle;
    private final String reviewScore;
    private final String positiveComment;
    private final String negativeComment;

    public Comment(String hotelName, String reviewerName, String reviewTitle, String reviewScore, String positiveComment, String negativeComment) {
        this.hotelName = hotelName;
        this.reviewerName = reviewerName;
        this.reviewTitle = reviewTitle;
        this.reviewScore = reviewScore;
        this.positiveComment = positiveComment;
        this.negativeComment = negativeComment;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public String getReviewScore() {
        return reviewScore;
    }

    public String getPositiveComment() {
        return positiveComment;
    }

    public String getNegativeComment() {
        return negativeComment;
    }
    @Override
    public String toString() {
        return "Comments [hotelName = " + hotelName +
                ", reviewerName = " + reviewerName +
                ", reviewTitle = " + reviewTitle +
                ", reviewScore = " + reviewScore +
                ", positiveComment = " + positiveComment +
                ", negativeComment = " + negativeComment +
                "]";
    }
}
