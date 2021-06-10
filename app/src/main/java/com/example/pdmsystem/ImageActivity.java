package com.example.pdmsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends AppCompatActivity {



    private RecyclerView recyclerView;
    DatabaseReference databaseReference;
    private ProgressBar progressBar;
    private List<Upload> uploadList;
    MyAdapter myAdapter;
    private FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);



        recyclerView=(RecyclerView)findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar=findViewById(R.id.Recprogressbar_id);
        uploadList=new ArrayList<>();

        firebaseStorage= FirebaseStorage.getInstance();

        databaseReference= FirebaseDatabase.getInstance().getReference("Upload");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                uploadList.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Upload upload=dataSnapshot1.getValue(Upload.class);
                    upload.setKey(dataSnapshot1.getKey());
                    uploadList.add(upload);
                }


                myAdapter=new MyAdapter(ImageActivity.this,uploadList);
                recyclerView.setAdapter(myAdapter);

                myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        String phone =uploadList.get(position).getPhoneNumber();
                        /*String locations =uploadList.get(position).getLocation().toString();*/

                        Intent intent= new Intent(getApplicationContext(),RecyclerClick.class);

                        intent.putExtra("PhoneFromImageActivity",phone);
                        /*intent.putExtra("Location:","Location: "+locations);*/
                        startActivity(intent);


                       /* String text =uploadList.get(position).getImagename();
                        Toast.makeText(getApplicationContext(),text+" is selected"+position,Toast.LENGTH_LONG).show();

                        */

                    }

                    @Override
                    public void onDoAnyTask(int position) {


                        Toast.makeText(getApplicationContext(),"onDoAnyTask  is selected"+position,Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onDelete(int position) {

                        Upload selectedItem = uploadList.get(position);
                        final String key = selectedItem.getKey();


                        StorageReference storageReference = firebaseStorage.getReferenceFromUrl(selectedItem.getImageuri());
                        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {


                                databaseReference.child(key).removeValue();
                            }
                        });



                    }
                });
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error "+databaseError.getMessage(),Toast.LENGTH_SHORT).show();

                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
