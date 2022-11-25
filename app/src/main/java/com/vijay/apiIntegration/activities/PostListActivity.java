package com.vijay.apiIntegration.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.vijay.apiIntegration.adapters.CommentAdapter;
import com.vijay.apiIntegration.adapters.PostAdapter;
import com.vijay.apiIntegration.databinding.ActivityPostListBinding;
import com.vijay.apiIntegration.databinding.LayoutBottomSheetCommentsBinding;
import com.vijay.apiIntegration.listeners.UserListener;
import com.vijay.apiIntegration.viewmodels.UserViewModel;

public class PostListActivity extends AppCompatActivity implements UserListener.PostListener {

    private static final String TAG = "PostListActivity";
    private ActivityPostListBinding binding;
    private UserViewModel userViewModel;
    private BottomSheetDialog commentsBottomSheetDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int userId = getIntent().getIntExtra("userId", 0);

        if (userId != 0) {
            userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
            userViewModel.getPosts(userId).observe(this, posts -> {
                if (posts != null && !posts.isEmpty()) {
                    PostAdapter adapter = new PostAdapter(getApplicationContext(), posts, PostListActivity.this);
                    binding.recyclerviewPosts.setAdapter(adapter);
                    binding.recyclerviewPosts.setHasFixedSize(true);
                }
            });
        }
    }

    @Override
    public void onClickedComments(int postId) {
        commentsBottomSheetDialog = new BottomSheetDialog(PostListActivity.this);
        LayoutBottomSheetCommentsBinding binding = LayoutBottomSheetCommentsBinding.inflate(getLayoutInflater());
        commentsBottomSheetDialog.setContentView(binding.getRoot());

        binding.imageClose.setOnClickListener(view -> commentsBottomSheetDialog.dismiss());

        FrameLayout frameLayout = commentsBottomSheetDialog.findViewById(
                com.google.android.material.R.id.design_bottom_sheet
        );

        if (frameLayout != null) {
            BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(frameLayout);
            bottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

        commentsBottomSheetDialog.show();

        userViewModel.getComments(postId).observe(this, comments -> {
            CommentAdapter adapter = new CommentAdapter(getApplicationContext(), comments);
            binding.recyclerviewComments.setAdapter(adapter);
            binding.recyclerviewComments.setHasFixedSize(true);
        });
    }
}