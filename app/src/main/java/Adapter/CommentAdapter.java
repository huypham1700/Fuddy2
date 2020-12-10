package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis_fuddy.R;

import java.util.List;

import Entity.Comment;
import view_holder.CommentItemViewHolder;


public class CommentAdapter extends RecyclerView.Adapter<CommentItemViewHolder> {
    private List<Comment> arrayComment;

    public CommentAdapter(List<Comment> arrayComment) {
        this.arrayComment = arrayComment;
    }

    public void setCommentArray(List<Comment> list) {
        this.arrayComment = list;
    }

    @NonNull
    @Override
    public CommentItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.view_comment_item, parent, false);
        return new CommentItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentItemViewHolder holder, int position) {
        holder.setCommentHolder(this.arrayComment.get(position), position);
    }

    @Override
    public int getItemCount() {
        return arrayComment.size();
    }
}
