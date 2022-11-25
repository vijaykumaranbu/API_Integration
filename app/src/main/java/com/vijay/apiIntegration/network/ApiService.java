package com.vijay.apiIntegration.network;

import com.vijay.apiIntegration.models.Comment;
import com.vijay.apiIntegration.models.Post;
import com.vijay.apiIntegration.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") int userId);

    @GET("comments")
    Call<List<Comment>> getComments(@Query("postId") int postId);
}
