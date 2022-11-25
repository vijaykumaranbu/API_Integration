package com.vijay.apiIntegration.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.vijay.apiIntegration.databinding.ActivityUserProfileBinding;
import com.vijay.apiIntegration.models.User;
import com.vijay.apiIntegration.utilities.Constants;
import com.vijay.apiIntegration.viewmodels.UserViewModel;

import java.util.Locale;

public class UserProfileActivity extends AppCompatActivity {

    private static final String TAG = "UserProfileActivity";
    private ActivityUserProfileBinding binding;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        User user = (User) getIntent().getSerializableExtra("user");
        if (user != null) {
            binding.imageProfile.setImageResource(Constants.getProfiles()[user.getId() - 1]);
            binding.textUsername.setText(user.getUsername());
            binding.textEmail.setText(user.getEmail());
            binding.textPhone.setText(user.getPhone());
            binding.textAddress.setText(String.format(Locale.getDefault(),
                    "%s\n%s\n%s\n%s",
                    user.getAddress().getStreet(),
                    user.getAddress().getSuite(),
                    user.getAddress().getCity(),
                    user.getAddress().getZipcode()));
            binding.textCompany.setText(String.format(Locale.getDefault(),
                    "%s\n%s\n%s",
                    user.getCompany().getName(),
                    user.getCompany().getCatchPhrase(),
                    user.getCompany().getBs()));

            binding.btnShowPosts.setOnClickListener(view -> {
                Intent intent = new Intent(UserProfileActivity.this, PostListActivity.class);
                intent.putExtra("userId", user.getId());
                startActivity(intent);
            });
        }
    }

}