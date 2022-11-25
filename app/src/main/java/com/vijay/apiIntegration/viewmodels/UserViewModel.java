package com.vijay.apiIntegration.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.vijay.apiIntegration.models.Comment;
import com.vijay.apiIntegration.models.Post;
import com.vijay.apiIntegration.models.User;
import com.vijay.apiIntegration.repositories.UserRepository;

import java.util.List;

public class UserViewModel extends ViewModel {

    private UserRepository userRepository;

    public UserViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<List<User>> getUsers() {
        return userRepository.getUsers();
    }

    public LiveData<List<Post>> getPosts(int userId) {
        return userRepository.getPosts(userId);
    }

    public LiveData<List<Comment>> getComments(int postId) {
        return userRepository.getComments(postId);
    }
}
