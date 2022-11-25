package com.vijay.apiIntegration.listeners;

import com.vijay.apiIntegration.models.User;

public interface UserListener {
    void onClickedUser(User user);

    public interface PostListener{
        void onClickedComments(int postId);
    }
}
