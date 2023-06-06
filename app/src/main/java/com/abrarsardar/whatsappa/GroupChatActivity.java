package com.abrarsardar.whatsappa;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.abrarsardar.whatsappa.databinding.ActivityGroupChatBinding;

public class GroupChatActivity extends AppCompatActivity {
ActivityGroupChatBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = ActivityGroupChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
//        binding.backArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(GroupChatActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        ArrayList<MessageModel> messageModels = new ArrayList<>(); //final
//
//        String senderId = FirebaseAuth.getInstance().getUid(); //final
//        binding.userNameChat.setText("gangbang");
//        MessageAdapter messageAdapter = new MessageAdapter(messageModels, this);
//        binding.chatActivityRecyclerView.setAdapter(messageAdapter);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        binding.chatActivityRecyclerView.setLayoutManager(layoutManager);
//
//
//        database.getReference().child("Group chats").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                messageModels.clear();
//                for(DataSnapshot snapshot1: snapshot.getChildren()){
//                    MessageModel model = snapshot1.getValue(MessageModel.class);
//                    messageModels.add(model);
//                }
//                messageAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        binding.sendarrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String message = binding.etText.getText().toString(); //final
//                MessageModel model = new MessageModel(message, senderId);
//                model.setTimestamp(new Date().getTime());
//
//                database.getReference().child("Group chats")
//                        .push()
//                        .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(@NonNull Void unused) {
//
//                    }
//                });
//            }
//        });
    }
}