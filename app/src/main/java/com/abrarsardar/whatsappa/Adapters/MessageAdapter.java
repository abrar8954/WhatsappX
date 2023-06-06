package com.abrarsardar.whatsappa.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.abrarsardar.whatsappa.Models.MessageModel;
import com.abrarsardar.whatsappa.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter {
    ArrayList<MessageModel> messageModels;
    Context context;
    String recId;
    int RECIEVER_VIEW_TYPE = 1, SENDER_VIEW_TYPE = 2, FRIEND_REQUEST_VIEW_TYPE = 3;
    String drecieveId;

//    public MessageAdapter(ArrayList<MessageModel> messageModels, Context context) {
//        this.messageModels = messageModels;
//        this.context = context;
//    }

    public MessageAdapter(ArrayList<MessageModel> messageModels, Context context, String recId, String drecieveId) {
        this.messageModels = messageModels;
        this.context = context;
        this.recId = recId;
        this.drecieveId = drecieveId;
        Log.d("rrrrrrrr3", "rrrrrrrr3 " + drecieveId);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SENDER_VIEW_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender, parent, false);
            return new SenderViewHolder(view);
        } else if (viewType == RECIEVER_VIEW_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_reciever, parent, false);
            return new RecieverViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_send_request, parent, false);
            return new RequestViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("rrrrrrrr4", "rrrrrrrr4 " + drecieveId);

        if (messageModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())) {
            Log.d("Sget(position)", String.valueOf(messageModels.get(position)));
            Log.d("SgetuId()", String.valueOf(messageModels.get(position).getuId()));
            Log.d("Sposition", String.valueOf(position));
            Log.d("Sfirebase", String.valueOf(FirebaseAuth.getInstance().getUid()));
            return SENDER_VIEW_TYPE;//=2
        } else if (messageModels.get(position).getuId().equals(drecieveId)) {
            return FRIEND_REQUEST_VIEW_TYPE;
        } else {
            Log.d("Rget(position)", String.valueOf(messageModels.get(position)));
            Log.d("RgetuId()", String.valueOf(messageModels.get(position).getuId()));
            Log.d("Rposition", String.valueOf(position));
            Log.d("Rfirebase", String.valueOf(FirebaseAuth.getInstance().getUid()));
            return RECIEVER_VIEW_TYPE;//=1
        }
//


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel messageModel = messageModels.get(position);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete this message")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String senderroom = FirebaseAuth.getInstance().getUid() + recId;
                                database.getReference().child("chats").child(senderroom)
                                        .child(messageModel.getMessageId())
                                        .setValue(null);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                return false;
            }
        });

        if (holder.getClass() == SenderViewHolder.class) {
            ((SenderViewHolder) holder).sender_message.setText(messageModel.getMessage());
        } else if (holder.getClass() == RecieverViewHolder.class) {
            ((RecieverViewHolder) holder).reciever_message.setText(messageModel.getMessage());
        } else {
            ((RequestViewHolder) holder).add_friend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "add friend........", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    public class RecieverViewHolder extends RecyclerView.ViewHolder {
        TextView reciever_message, reciever_time;

        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);

            reciever_message = itemView.findViewById(R.id.reciever_text);
            reciever_time = itemView.findViewById(R.id.reciever_time);
        }
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder {
        TextView sender_message, sender_time;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);

            sender_message = itemView.findViewById(R.id.sender_text);
            sender_time = itemView.findViewById(R.id.sender_time);
        }
    }

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView report, block, add_friend;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);

            report = itemView.findViewById(R.id.tvReport);
            block = itemView.findViewById(R.id.tvBlock);
            add_friend = itemView.findViewById(R.id.tvAddFriend);
        }
    }

}
