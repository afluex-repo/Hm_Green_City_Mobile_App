package com.hmgreencity.retrofit;

public interface DashboardPostListener {
    void likePost(String postId, int likeCount, boolean isLike, int pos);

    void followUser(String userId, boolean isFollow, int pos);

    void sharePost(String postId, String userId);
}
