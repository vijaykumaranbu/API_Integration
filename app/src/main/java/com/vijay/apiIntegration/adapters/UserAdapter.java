package com.vijay.apiIntegration.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vijay.apiIntegration.databinding.ItemContainerProfileBinding;
import com.vijay.apiIntegration.listeners.UserListener;
import com.vijay.apiIntegration.models.User;
import com.vijay.apiIntegration.utilities.Constants;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private List<User> users;
    private LayoutInflater layoutInflater;
    private UserListener userListener;

    public UserAdapter(Context context, List<User> users, UserListener userListener) {
        this.context = context;
        this.users = users;
        this.userListener = userListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerProfileBinding binding = ItemContainerProfileBinding.inflate(
                layoutInflater,
                parent,
                false
        );
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.setBinding(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    class UserViewHolder extends RecyclerView.ViewHolder {

        private ItemContainerProfileBinding binding;

        public UserViewHolder(ItemContainerProfileBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void setBinding(User user) {
            Glide.with(context).load(Constants.getThumbs()[getAdapterPosition()]).into(binding.imageThumbnail);
            Glide.with(context).load(Constants.getProfiles()[getAdapterPosition()]).into(binding.imageProfile);
            binding.textUsername.setText(user.getUsername());
            binding.textCompany.setText(user.getCompany().getName());

            binding.getRoot().setOnClickListener(view -> userListener.onClickedUser(user));
        }
    }
}
