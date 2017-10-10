package com.example.ggxiaozhi.store.the_basket.bean;

import java.util.List;

/**
 *详情页评论部分数据
 */

public class AppCommentBean {

    private String score ;
    private String starts ;
    private List<RatingDstListBean> ratingDstList;
    private List<CommentsBean> comments;

    public AppCommentBean(String score, String starts, List<RatingDstListBean> ratingDstList, List<CommentsBean> comments) {
        this.score = score;
        this.starts = starts;
        this.ratingDstList = ratingDstList;
        this.comments = comments;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStarts() {
        return starts;
    }

    public void setStarts(String starts) {
        this.starts = starts;
    }

    public List<RatingDstListBean> getRatingDstList() {
        return ratingDstList;
    }

    public void setRatingDstList(List<RatingDstListBean> ratingDstList) {
        this.ratingDstList = ratingDstList;
    }

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class RatingDstListBean {
        /**
         * rating : 1
         * ratingCounts : 3356
         */

        private int rating;
        private int ratingCounts;

        public RatingDstListBean(int rating, int ratingCounts) {
            this.rating = rating;
            this.ratingCounts = ratingCounts;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public int getRatingCounts() {
            return ratingCounts;
        }

        public void setRatingCounts(int ratingCounts) {
            this.ratingCounts = ratingCounts;
        }
    }

    public static class CommentsBean {
        /**
         * accountId : 40086000022520300
         * accountName : 感觉****在我耳边
         * approveCounts : 4373
         * commentId : 41519e1dfa9940ca811fce2eecb85457
         * commentInfo : 新浪旗下的使用人数最多，最受欢迎的社交软件，在里面即可以看到明星八卦，也可以了解到国家大事，可以根据我们的兴趣选择相应公众号进行关注。
         * commentType : 1
         * id : 41519e1dfa9940ca811fce2eecb85457
         * operTime : 2017/3/5 23:35
         * phone : 荣耀畅玩6X
         * photoUrl : https://upfile1.hicloud.com/FileServer/image/b.0040086000022520300.20160415051340.59932118.1000.CE8982CCA0B63BF86B5AFAD3F27E93B0A25854178586FA55918F0D27B4106E23.png
         * rating : 5.0000
         * replyCounts : 25
         * stars : 5.0000
         * versionName : 7.2.0
         */

        private String accountId;
        private String accountName;
        private String approveCounts;
        private String commentId;
        private String commentInfo;
        private String commentType;
        private String id;
        private String operTime;
        private String phone;
        private String photoUrl;
        private String rating;
        private String replyCounts;
        private String stars;
        private String versionName;

        public CommentsBean(String accountId, String accountName, String approveCounts, String commentId, String commentInfo, String commentType, String id, String operTime, String phone, String photoUrl, String rating, String replyCounts, String stars, String versionName) {
            this.accountId = accountId;
            this.accountName = accountName;
            this.approveCounts = approveCounts;
            this.commentId = commentId;
            this.commentInfo = commentInfo;
            this.commentType = commentType;
            this.id = id;
            this.operTime = operTime;
            this.phone = phone;
            this.photoUrl = photoUrl;
            this.rating = rating;
            this.replyCounts = replyCounts;
            this.stars = stars;
            this.versionName = versionName;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getApproveCounts() {
            return approveCounts;
        }

        public void setApproveCounts(String approveCounts) {
            this.approveCounts = approveCounts;
        }

        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
        }

        public String getCommentInfo() {
            return commentInfo;
        }

        public void setCommentInfo(String commentInfo) {
            this.commentInfo = commentInfo;
        }

        public String getCommentType() {
            return commentType;
        }

        public void setCommentType(String commentType) {
            this.commentType = commentType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOperTime() {
            return operTime;
        }

        public void setOperTime(String operTime) {
            this.operTime = operTime;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getReplyCounts() {
            return replyCounts;
        }

        public void setReplyCounts(String replyCounts) {
            this.replyCounts = replyCounts;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }
    }


}
