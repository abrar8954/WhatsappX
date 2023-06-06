package com.abrarsardar.whatsappa.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abrarsardar.whatsappa.ChatActivity;
import com.abrarsardar.whatsappa.Models.Users;
import com.abrarsardar.whatsappa.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.viewholder> {

    //    ArrayList<ChatModel> list;
    ArrayList<Users> list;
    Context context;
// FirebaseAuth auth;

    //    public ChatAdapter(ArrayList<ChatModel> list, Context context) {
//        this.list = list;
//        this.context = context;
//    }
    public ChatAdapter(ArrayList<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chatssample, parent, false);
        return new viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.viewholder holder, int position) {
//        final ChatModel model = list.get(position);
        final Users users = list.get(position);

        Picasso.get().load(users.getProfilepic()).placeholder(R.drawable.profile).into(holder.img_chat);
//        holder.img_chat.setImageResource(model.getProfilepic());
        holder.name_chat.setText(users.getUsrName());

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("chats")
                .child(FirebaseAuth.getInstance().getUid() + users.getUserId());
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild("timestamp")) {
                    //get last message from firebase
                    FirebaseDatabase.getInstance().getReference().child("chats")
                            .child(FirebaseAuth.getInstance().getUid() + users.getUserId())
                            .orderByChild("timestamp")
                            .limitToLast(1)
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.hasChildren()) {
                                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                            holder.message_chat.setText(dataSnapshot.child("message").getValue().toString());
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


//        holder.message_chat.setText(model.getMessage());
//        holder.time_chat.setText(model.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("userId", users.getUserId());
                Log.d("chatadapteruserid", users.getUserId());
                intent.putExtra("profilePic", users.getProfilepic());
                intent.putExtra("userName", users.getUsrName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView img_chat;
        TextView name_chat, message_chat, time_chat;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            img_chat = itemView.findViewById(R.id.userImageChat);
            name_chat = itemView.findViewById(R.id.userNameChatSample);
            message_chat = itemView.findViewById(R.id.tvChatsampleMessage);
            time_chat = itemView.findViewById(R.id.tvTimeChat);
        }
    }
}
