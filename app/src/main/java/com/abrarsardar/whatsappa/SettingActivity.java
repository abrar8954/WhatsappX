package com.abrarsardar.whatsappa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abrarsardar.whatsappa.databinding.ActivitySettingBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class SettingActivity extends AppCompatActivity {
    ActivitySettingBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        
        auth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

//        ArrayList<Users> list = new ArrayList<>();
//
//
//        ChatAdapter adapter = new ChatAdapter(list, this);
//
//        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                list.clear();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Users users = dataSnapshot.getValue(Users.class);
//                    users.setUserId(dataSnapshot.getKey());
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


//        binding.btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String status = binding.etStatus.getText().toString();
//                String name = binding.etName.getText().toString();
//
//                HashMap<String, Object> obj =new HashMap<>();
//                obj.put("usrName", name);
//                obj.put("status", status);
//
//                database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).updateChildren(obj);
//            }
//        });
//
//         database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
//                 .addListenerForSingleValueEvent(new ValueEventListener() {
//                     @Override
//                     public void onDataChange(@NonNull DataSnapshot snapshot) {
//                         Users users = snapshot.getValue(Users.class);
//                         Picasso.get().load(users.getProfilepic()).placeholder(R.drawable.profile).into(binding.profileImage);
//                         binding.etStatus.setText(users.getStatus());
//                         binding.etName.setText(users.getUsrName());
//                     }
//
//                     @Override
//                     public void onCancelled(@NonNull DatabaseError error) {
//
//                     }
//                 });
//
//        binding.plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//                startActivityForResult(intent, 33);
//            }
//        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(data.getData() != null){
//
//            Uri sFile =data.getData();
//            binding.profileImage.setImageURI(sFile);
//
//            StorageReference reference = storage.getReference().child("Profile_photos")
//                    .child(FirebaseAuth.getInstance().getUid());
//            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(@NonNull Uri uri) {
//                            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
//                                    .child("profilepic").setValue(uri.toString());
//                        }
//                    });
//                }
//            });
//        }
//
//    }
}