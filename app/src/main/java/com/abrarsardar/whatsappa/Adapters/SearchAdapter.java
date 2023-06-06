package com.abrarsardar.whatsappa.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abrarsardar.whatsappa.Models.MessageModel;
import com.abrarsardar.whatsappa.Models.SearchModel;
import com.abrarsardar.whatsappa.Models.Users;
import com.abrarsardar.whatsappa.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SearchAdapter extends FirebaseRecyclerAdapter<SearchModel, SearchAdapter.myviewholder> {

    ArrayList<Users> list;
    Context context;

    public SearchAdapter(@NonNull @NotNull FirebaseRecyclerOptions<SearchModel> options, ArrayList<Users> list, Context context) {
        super(options);
        this.list = list;
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull SearchModel searchModel) {
        final Users users = list.get(position);

        holder.img_search.setImageResource(R.drawable.user);
        holder.name_search.setText(users.getUsrName());


        holder.add_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, Findfriends.class);
//                intent.putExtra("userIdSearch", users.getUserId());
//                context.startActivity(intent);
//                Toast.makeText(context, "add_search...", Toast.LENGTH_SHORT).show();

                final MessageModel messageModel = new MessageModel(users.getUserId() + users.getUserId());
                FirebaseDatabase.getInstance().getReference().child("chats")
                        .child(FirebaseAuth.getInstance().getUid() + users.getUserId())
                        .push()
                        .setValue(messageModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void unused) {
                        FirebaseDatabase.getInstance().getReference().child("chats")
                                .child(users.getUserId() + FirebaseAuth.getInstance().getUid())
                                .push()
                                .setValue(messageModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(@NonNull Void unused) {

                            }
                        });
                    }
                });
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friendsample, parent, false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder {
        ImageView img_search;
        TextView name_search;
        Button add_search;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img_search = itemView.findViewById(R.id.userImageSearch);
            name_search = itemView.findViewById(R.id.userNameSearch);
            add_search = itemView.findViewById(R.id.btnAddSearch);
        }
    }
}
