package com.abrarsardar.whatsappa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.abrarsardar.whatsappa.Adapters.FragmentAdapter;
import com.abrarsardar.whatsappa.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
//    TabLayout tabLayout;
//    ViewPager viewPager;
//    FirebaseAuth auth;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setElevation(0);
//        setContentView(R.layout.activity_main);

//        viewPager = findViewById(R.id.viewpager);
        binding.viewpager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
//        tabLayout = findViewById(R.id.tablayout);
        binding.tablayout.setupWithViewPager(binding.viewpager);

//        auth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

//        ArrayList<ChatModel> list = new ArrayList<>();
//        list.add(new ChatModel(R.drawable.profile, "ali", "yes it is", "6:23PM"));
//        list.add(new ChatModel(R.drawable.profile, "ali", "yes it is", "6:23PM"));
//        list.add(new ChatModel(R.drawable.profile, "ali", "yes it is", "6:23PM"));
//        list.add(new ChatModel(R.drawable.profile, "ali", "yes it is", "6:23PM"));
//        list.add(new ChatModel(R.drawable.profile, "ali", "yes it is", "6:23PM"));
//
//        ChatAdapter adapter = new ChatAdapter(list, this);
//        binding.recyclerView.setAdapter(adapter);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        binding.recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
          switch(item.getItemId()){
              case R.id.setting:
                  Intent intent2 = new Intent(MainActivity.this, SettingActivity.class);
                  startActivity(intent2);
                  break;
              case R.id.logout:
//                  auth.signOut();
                  googleSignInClient.signOut();
                  Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                  startActivity(intent);
                  break;
              case R.id.groupchat:
                  Intent intent1 = new Intent(MainActivity.this, GroupChatActivity.class);
                  startActivity(intent1);
                  break;
          }
        return super.onOptionsItemSelected(item);
    }
}