package com.vijay.apiIntegration.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vijay.apiIntegration.databinding.ItemContainerPostBinding;
import com.vijay.apiIntegration.listeners.UserListener;
import com.vijay.apiIntegration.models.Post;
import com.vijay.apiIntegration.utilities.Constants;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> posts;
    private LayoutInflater layoutInflater;
    private UserListener.PostListener postListener;
    private Context context;

    public PostAdapter(Context context, List<Post> posts, UserListener.PostListener postListener) {
        this.posts = posts;
        this.postListener = postListener;
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerPostBinding binding = ItemContainerPostBinding.inflate(
                layoutInflater,
                parent,
                false
        );
        return new PostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.setBinding(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        private ItemContainerPostBinding binding;

        public PostViewHolder(ItemContainerPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void setBinding(Post post) {

            if (String.valueOf(getAdapterPosition()).length() > 1)
                Glide.with(context).load(Constants.getThumbs()[(getItemCount() % 10)]).into(binding.imagePost);
            else
                Glide.with(context).load(Constants.getThumbs()[getAdapterPosition()]).into(binding.imagePost);

            binding.textPostTitle.setText(post.getTitle());
            binding.textPostBody.setText(post.getBody());

            binding.btnComments.setOnClickListener(view -> postListener.onClickedComments(post.getId()));
        }

    }
}
