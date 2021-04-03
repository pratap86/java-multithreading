package com.pratap.domain;

public class Review {
	
    private int noOfReviews;
    private double overallRating;
    
    public Review() {
    	
    }

	public Review(int noOfReviews, double overallRating) {
		this.noOfReviews = noOfReviews;
		this.overallRating = overallRating;
	}

	public int getNoOfReviews() {
		return noOfReviews;
	}

	public void setNoOfReviews(int noOfReviews) {
		this.noOfReviews = noOfReviews;
	}

	public double getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(double overallRating) {
		this.overallRating = overallRating;
	}

	@Override
	public String toString() {
		return String.format("Review [noOfReviews=%s, overallRating=%s]", noOfReviews, overallRating);
	}
	
}
