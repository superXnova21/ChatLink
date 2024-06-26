package com.example.chatlink.Adapter;

import static android.view.View.GONE;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatlink.R;
import com.example.chatlink.model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{

    private List<Users> list;
    OnUserClicked onUserClicked;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public UserAdapter(List<Users> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_items, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserHolder holder, final int position) {
        if(list.get(position).getUid().equals(user.getUid())){
            holder.layout.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }else{
            holder.layout.setVisibility(View.VISIBLE);
        }

        holder.nameTV.setText(list.get(position).getName());
        holder.statusTV.setText(list.get(position).getStatus());

        Glide.with(holder.itemView.getContext().getApplicationContext())
                .load(list.get(holder.getAdapterPosition()).getProfileImage())
                .placeholder(R.drawable.ic_profile)
                .timeout(6500)
                .into(holder.profileImage);

        holder.itemView.setOnClickListener(v -> {
            onUserClicked.onClicked(list.get(holder.getAdapterPosition()).getUid());//Toast.makeText(itemView.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void OnUserClicked(OnUserClicked onUserClicked){
        this.onUserClicked = onUserClicked;
    }

    public interface OnUserClicked{
        void onClicked(String uid);
    }

    static class UserHolder extends RecyclerView.ViewHolder{

        private CircleImageView profileImage;
        private TextView nameTV, statusTV;
        private RelativeLayout layout;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profileImage);
            nameTV = itemView.findViewById(R.id.nameTV);
            statusTV = itemView.findViewById(R.id.statusTV);
            layout = itemView.findViewById(R.id.relativeLayout);
        }



    }
}
