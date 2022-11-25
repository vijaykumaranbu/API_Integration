package com.vijay.apiIntegration.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vijay.apiIntegration.databinding.ItemContainerCommentsBinding;
import com.vijay.apiIntegration.models.Comment;
import com.vijay.apiIntegration.utilities.Constants;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Comment> comments;

    public CommentAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerCommentsBinding binding = ItemContainerCommentsBinding.inflate(
                layoutInflater,
                parent,
                false
        );
        return new CommentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.setBinding(comments.get(position));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {

        private ItemContainerCommentsBinding binding;

        public CommentViewHolder(ItemContainerCommentsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void setBinding(Comment comment) {
            Glide.with(context).load(Constants.getProfiles()[(comment.getId() % 10)]).into(binding.imageProfile);
            binding.textUsername.setText(comment.getName());
            binding.textEmail.setText(comment.getEmail());
            binding.textComment.setText(comment.getBody());
        }
    }
}
