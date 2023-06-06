package com.abrarsardar.whatsappa.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.abrarsardar.whatsappa.databinding.FragmentChatBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class ChatFragment extends Fragment {
    FragmentChatBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


//        ArrayList<Users> list = new ArrayList<>();


//        database = FirebaseDatabase.getInstance();
//        auth = FirebaseAuth.getInstance();
//        ChatAdapter adapter = new ChatAdapter(list, getContext());
//        binding.recyclerView.setAdapter(adapter);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        binding.recyclerView.setLayoutManager(layoutManager);
//
//        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                list.clear();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Users users = dataSnapshot.getValue(Users.class);
//                    users.setUserId(dataSnapshot.getKey());
//
//
//                    name = (String) dataSnapshot.child("usrName").getValue();
//                    Log.d("name1","name1 "+ name);
//
//
//
//                    list.add(users);
//
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        Log.d("name2","name2 "+ name);

        return root;
    }

}


//        binding.addFriend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), Findfriends.class);
//                startActivity(intent);
//            }
//        });

//        list.add(new Users(R.drawable.profile, "ali", "yes it is", "6:23PM"));
//        list.add(new Users(R.drawable.profile, "ali", "yes it is", "6:23PM"));
//        list.add(new Users(R.drawable.profile, "ali", "yes it is", "6:23PM"));
//        list.add(new Users(R.drawable.profile, "ali", "yes it is", "6:23PM"));
//        list.add(new Users(R.drawable.profile, "ali", "yes it is", "6:23PM"));

//        ArrayList<ChatModel> list = new ArrayList<>();
//        list.add(new ChatModel(R.drawable.profile, "ali", "yes it is", "6:23PM"));
//        list.add(new ChatModel(R.drawable.profile, "ali", "yes it is", "6:23PM"));
//        list.add(new ChatModel(R.drawable.profile, "ali", "yes it is", "6:23PM"));
//        list.add(new ChatModel(R.drawable.profile, "ali", "yes it is", "6:23PM"));
//        list.add(new ChatModel(R.drawable.profile, "ali", "yes it is", "6:23PM"));