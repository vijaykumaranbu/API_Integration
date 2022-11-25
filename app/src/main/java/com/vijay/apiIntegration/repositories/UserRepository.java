package com.vijay.apiIntegration.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vijay.apiIntegration.models.Comment;
import com.vijay.apiIntegration.models.Post;
import com.vijay.apiIntegration.models.User;
import com.vijay.apiIntegration.network.ApiClient;
import com.vijay.apiIntegration.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private ApiService apiService;
    private MutableLiveData<List<User>> userLiveData;
    private MutableLiveData<List<Post>> postLiveData;
    private MutableLiveData<List<Comment>> commentLiveData;

    public UserRepository() {
        apiService = ApiClient.getClient().create(ApiService.class);
        userLiveData = new MutableLiveData<>();
        postLiveData = new MutableLiveData<>();
        commentLiveData = new MutableLiveData<>();
    }

    public LiveData<List<User>> getUsers() {
        apiService.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                userLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                userLiveData.setValue(null);
            }
        });
        return userLiveData;
    }

    public LiveData<List<Post>> getPosts(int userId) {
        apiService.getPosts(userId).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                postLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                postLiveData.setValue(null);
            }
        });
        return postLiveData;
    }

    public LiveData<List<Comment>> getComments(int postId) {
        apiService.getComments(postId).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                commentLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                commentLiveData.setValue(null);
            }
        });
        return commentLiveData;
    }
}
