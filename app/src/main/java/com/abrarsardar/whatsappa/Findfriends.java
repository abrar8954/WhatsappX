package com.abrarsardar.whatsappa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.abrarsardar.whatsappa.Adapters.SearchAdapter;
import com.abrarsardar.whatsappa.Models.SearchModel;
import com.abrarsardar.whatsappa.Models.Users;
import com.abrarsardar.whatsappa.databinding.ActivityFindfriendsBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Findfriends extends AppCompatActivity {
    ActivityFindfriendsBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    SearchAdapter adapter1;
    ArrayList<Users> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindfriendsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        getSupportActionBar().hide();
         list = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
//        ChatAdapter adapter = new ChatAdapter(list, this);
//        binding.recyclerViewa.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerViewa.setLayoutManager(layoutManager);

//        final String senderId = auth.getUid();
//        Log.d("senderIdsearch", senderId);
        String recieveId = getIntent().getStringExtra("userIdSearch");
        Log.d(TAG, "recieveId " + recieveId);


        database.getReference().child("Users")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Users users = dataSnapshot.getValue(Users.class);
                            users.setUserId(dataSnapshot.getKey());

                            if (dataSnapshot.getKey().equals(auth.getUid())) {
                                String country = users.getCountry();
                                SharedPreferences preferences = getSharedPreferences("demo", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();

                                editor.putString("str", country);
                                editor.apply();
                               }

                            list.add(users);
                        }
//                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        SharedPreferences getshrd = getSharedPreferences("demo", MODE_PRIVATE);
        String value = getshrd.getString("str", "show");
        Log.d("value", value);
        FirebaseRecyclerOptions<SearchModel> options =
                new FirebaseRecyclerOptions.Builder<SearchModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child("Users").orderByChild("country")
                                .equalTo(value), SearchModel.class)
                        .build();

        adapter1 = new SearchAdapter(options, list, this);
        binding.recyclerViewa.setAdapter(adapter1);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter1.startListening();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter1.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu, menu);

        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s) {
        FirebaseRecyclerOptions<SearchModel> options =
                new FirebaseRecyclerOptions.Builder<SearchModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("usrName").startAt(s).endAt(s + "\uf8ff"), SearchModel.class)
                        .build();

        adapter1 = new SearchAdapter(options, list, this);
        adapter1.startListening();
        binding.recyclerViewa.setAdapter(adapter1);

    }
}