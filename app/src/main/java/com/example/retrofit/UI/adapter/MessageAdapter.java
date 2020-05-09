package com.example.retrofit.UI.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.retrofit.R;
import com.example.retrofit.domain.MessageRespose;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.InnerHolder> {

    private  List<MessageRespose.DataBean> mData;

    public List<MessageRespose.DataBean> getData() {
        return mData;
    }
    public void setData(List<MessageRespose.DataBean> beans) {
        this.mData=beans;
    }

    public MessageAdapter(List<MessageRespose.DataBean> data){
        this.mData=data;
        System.out.println(mData);
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.item_message,null);
        return new MessageAdapter.InnerHolder(view);
      
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        System.out.println(mData.get(position));
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        if (mData!=null) {
            return mData.size();
        }
        return 0;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            mTextView=itemView.findViewById(R.id.item_message_textView);
        }

        public void setData(MessageRespose.DataBean dataBean) {
            mTextView.setText(dataBean.getContent());

        }
    }
}
