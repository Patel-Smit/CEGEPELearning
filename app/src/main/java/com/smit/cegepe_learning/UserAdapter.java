package com.smit.cegepe_learning;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<UserIdClass> musers;

    public UserAdapter(Context mContext, List<UserIdClass> musers) {
        this.musers = musers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_cardview, parent, false);

        return new UserAdapter.chatHoldeer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        System.out.println(musers.toString());
        final UserIdClass user = musers.get(position);
        //System.out.println(user.getName());
        TextView tvUser = holder.itemView.findViewById(R.id.chat_tv_user);
        TextView tvUsertype = holder.itemView.findViewById(R.id.chat_tv_usertype);
        tvUser.setText(user.getName());
        tvUsertype.setText(user.getUsertype().toUpperCase());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, MessageActivity.class);
                i.putExtra("userChatId", user.getId());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return musers.size();
    }

    class chatHoldeer extends RecyclerView.ViewHolder {
        public TextView username, usertype;

        public chatHoldeer(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.chat_tv_user);
            usertype = itemView.findViewById(R.id.chat_tv_usertype);
        }
    }
}
