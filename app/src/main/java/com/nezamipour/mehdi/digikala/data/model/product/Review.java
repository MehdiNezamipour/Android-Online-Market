
package com.nezamipour.mehdi.digikala.data.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Review implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("reviewer")
    @Expose
    private String reviewer;
    @SerializedName("reviewer_email")
    @Expose
    private String reviewerEmail;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    private final static long serialVersionUID = -7663595640270920451L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getReviewerEmail() {
        return reviewerEmail;
    }

    public void setReviewerEmail(String reviewerEmail) {
        this.reviewerEmail = reviewerEmail;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }


    public Review(Integer productId, String reviewer, String reviewerEmail, String review) {
        this.productId = productId;
        this.reviewer = reviewer;
        this.reviewerEmail = reviewerEmail;
        this.review = review;
    }

}
