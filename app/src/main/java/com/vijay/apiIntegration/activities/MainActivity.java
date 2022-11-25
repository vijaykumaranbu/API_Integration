package com.vijay.apiIntegration.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.vijay.apiIntegration.adapters.UserAdapter;
import com.vijay.apiIntegration.databinding.ActivityMainBinding;
import com.vijay.apiIntegration.listeners.UserListener;
import com.vijay.apiIntegration.models.User;
import com.vijay.apiIntegration.viewmodels.UserViewModel;

public class MainActivity extends AppCompatActivity implements UserListener {

    private ActivityMainBinding binding;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (checkInternetConnection()) {
            userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
            userViewModel.getUsers().observe(this, users -> {
                if (users != null && !users.isEmpty()) {
                    UserAdapter userAdapter = new UserAdapter(getApplicationContext(), users, MainActivity.this);
                    binding.recyclerviewUsers.setAdapter(userAdapter);
                    binding.recyclerviewUsers.setHasFixedSize(true);
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected());
    }

    @Override
    public void onClickedUser(User user) {
        Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}