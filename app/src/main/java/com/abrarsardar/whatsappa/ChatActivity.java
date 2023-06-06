package com.abrarsardar.whatsappa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.abrarsardar.whatsappa.Adapters.MessageAdapter;
import com.abrarsardar.whatsappa.Models.MessageModel;
import com.abrarsardar.whatsappa.databinding.ActivityChatBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        final String senderId = auth.getUid();
//        Log.d("senderId", senderId);
        String recieveId = getIntent().getStringExtra("userId");
        String drecieveId = recieveId + recieveId;
//        Log.d("recieveId", recieveId);
        String userName = getIntent().getStringExtra("userName");
        String profilePic = getIntent().getStringExtra("profilePic");

        binding.userNameChat.setText(userName);
        Picasso.get().load(profilePic).placeholder(R.drawable.profile).into(binding.profileImageChat1);

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final ArrayList<MessageModel> messageModels = new ArrayList<>();
        final MessageAdapter messageAdapter = new MessageAdapter(messageModels, this, recieveId, drecieveId);
        binding.chatActivityRecyclerView.setAdapter(messageAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.chatActivityRecyclerView.setLayoutManager(layoutManager);

        String senderRoom = senderId + recieveId;
        String recieverRoom = recieveId + senderId;

        database.getReference().child("chats").child(senderRoom).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageModels.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    MessageModel model = snapshot1.getValue(MessageModel.class);
                    model.setMessageId(snapshot1.getKey());
                    messageModels.add(model);
                }
                messageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.sendarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = binding.etText.getText().toString();
                final MessageModel messageModel;
//                Log.d("request", "request " + request);
//                if (request == false) {
                    messageModel = new MessageModel(message, senderId);
                    messageModel.setTimestamp(new Date().getTime());
//                } else {

//                }

                binding.etText.setText("");
                database.getReference().child("chats")
                        .child(senderRoom)
                        .push()
                        .setValue(messageModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void unused) {
                        database.getReference().child("chats")
                                .child(recieverRoom)
                                .push()
                                .setValue(messageModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(@NonNull Void unused) {

                            }
                        });
                    }
                });

//                messageModels.add(messageModel);
//                messageAdapter.notifyDataSetChanged();
            }
        });



    }
}