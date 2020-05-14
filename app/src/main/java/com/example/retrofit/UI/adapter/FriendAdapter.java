package com.example.retrofit.UI.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.Interface.DataManagerObserve;
import com.example.retrofit.R;
import com.example.retrofit.UI.activity.CommunicateActivity;

import com.example.retrofit.domain.FriendRespose;

import java.util.List;



public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.InnerHolder> {


    private final List<FriendRespose.DataBean> mData;

    public FriendAdapter(List<FriendRespose.DataBean> data){
            this.mData=data;
            System.out.println(mData);
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.item_friend,null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {

        holder.setData(mData.get(position));
        //进入好友页面
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CommunicateActivity.class);

                DataManagerObserve instance = DataManagerObserve.getInstance();
                instance.setNowFriend(mData.get(position).getFriendId());
                instance.setNowFriendName(mData.get(position).getName());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData!=null) {
            return mData.size();
        }
        return 0;
    }

    public static class InnerHolder extends RecyclerView.ViewHolder {
        private TextView friendName,friendId,isOnline,lastMessage,lastTime;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            friendName = itemView.findViewById(R.id.item_testview_name);
            friendId =itemView.findViewById(R.id.item_testview_id);
            lastMessage=itemView.findViewById(R.id.item_testview_lastMessage);
            isOnline=itemView.findViewById(R.id.item_friend_isOnline);
            lastTime=itemView.findViewById(R.id.item_testview_lasttime);
        }

        //
        public void setData(FriendRespose.DataBean dataBean) {
            if (dataBean.getIsOnline().equals("1")) {
                isOnline.setText("在线");
            }
            else isOnline.setText("离线请留言      :");
            friendName.setText(dataBean.getName());
            lastMessage.setText(dataBean.getLastMessage());
            friendId.setText(String.valueOf(dataBean.getFriendId()));
            lastTime.setText(dataBean.getLastTime());
        }
    }
}
