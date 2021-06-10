package com.example.pdmsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AdDetails extends AppCompatActivity implements View.OnClickListener {

    private Button selectbutton,savebutton;
    private EditText settitlebutton,locatiionEditText,addressEditText,nameEditText,phoneNumberEditText,emailEditText,rentEditText;
    private ProgressBar progressBarbutton;
    private ImageView imageViewbutton;
    private Uri imageuri;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    StorageTask storageTask;




//For Map
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private Double Latitude,Longitude;
    private Geocoder geocoder;
    private List<Address> addresses;



    private static final int IMAGE_REQUEST=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_details);






    databaseReference= FirebaseDatabase.getInstance().getReference("Upload");
        storageReference= FirebaseStorage.getInstance().getReference("Upload");
//settitle=detailsProperty
        locatiionEditText=findViewById(R.id.locationEditTextId);
        addressEditText=findViewById(R.id.addressEditTextId);
        rentEditText=findViewById(R.id.rentalRateId);
        nameEditText=findViewById(R.id.nameEditTextId);
        phoneNumberEditText=findViewById(R.id.phoneNumberEditTextId);
        emailEditText=findViewById(R.id.emailEditTextId);

        selectbutton=(Button)findViewById(R.id.select_image_id);
        savebutton=(Button)findViewById(R.id.save_image_id);

        settitlebutton=(EditText) findViewById(R.id.set_title_id);
        progressBarbutton=(ProgressBar) findViewById(R.id.progressbar_id);
        imageViewbutton=(ImageView) findViewById(R.id.imageview_id);

        selectbutton.setOnClickListener(this);
        savebutton.setOnClickListener(this);




//maps
        geocoder= new Geocoder(this, Locale.ENGLISH);



        locationCallback = new LocationCallback()
        {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);



                if(locationResult!=null)
                {
                    for(Location location : locationResult.getLocations())
                    {
                        Latitude =location.getLatitude();
                        Longitude =location.getLongitude();


                        try {
                            addresses = geocoder.getFromLocation(Latitude,Longitude,1);

                            String address = addresses.get(0).getAddressLine(0)+"\n"+addresses.get(0).getLocality()+"\n"
                                    +addresses.get(0).getPostalCode();


                            locatiionEditText.setText(address);
                            //Toast.makeText(getApplicationContext(),address,Toast.LENGTH_SHORT).show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        /*Toast.makeText(getApplicationContext(),Latitude+ " "+Longitude +"  ",Toast.LENGTH_SHORT).show();*/
                    }
                }
            }
        };
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);

        crateLocationREquest();


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.select_image_id:
                openfilechooser();
                break;
            case R.id.save_image_id:

                if(storageTask!=null && storageTask.isInProgress()){
                    Toast.makeText(getApplicationContext(),"upload is in progress",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Save Data Clicked",Toast.LENGTH_SHORT).show();
                    saveData();
                }
                break;

      /*      case R.id.show_image_id:
                Intent intent = new Intent(getApplicationContext(),ImageActivity.class);
                startActivity(intent);*/

    }
    }

    public String getFileExtention(Uri Imageuri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(Imageuri));
    }

    private void saveData() {

        final String imagename=settitlebutton.getText().toString().trim();
        final String location=locatiionEditText.getText().toString().trim();
        final String addresses=addressEditText.getText().toString().trim();
        final String rentAmount=rentEditText.getText().toString().trim();
        final String name=nameEditText.getText().toString().trim();
        final String phoneNumber=phoneNumberEditText.getText().toString().trim();
        final String email=emailEditText.getText().toString().trim();

        if(imagename.isEmpty()){

            settitlebutton.setError("Enter the image name");
            settitlebutton.requestFocus();
            return;

        }
        else if(location.isEmpty()){
            locatiionEditText.setError("Enter the Location");
            locatiionEditText.requestFocus();
            return;
        }else if(addresses.isEmpty()){
            addressEditText.setError("Enter the address");
            addressEditText.requestFocus();
            return;
        }else if(rentAmount.isEmpty()){
            rentEditText.setError("Enter the rent ammount");
            rentEditText.requestFocus();
            return;
        }else if(name.isEmpty()){
            nameEditText.setError("Enter the name");
            nameEditText.requestFocus();
            return;
        }else if(phoneNumber.isEmpty()){
            phoneNumberEditText.setError("Enter the phone number");
            phoneNumberEditText.requestFocus();
            return;
        }else if(email.isEmpty()){
            emailEditText.setError("Enter the email");
            emailEditText.requestFocus();
            return;
        }//Upload(String imagename, String imageuri, String location, String addresses, String rentAmount, String name, String phoneNumber, String email)
        StorageReference reference=storageReference.child(System.currentTimeMillis()+"."+getFileExtention(imageuri));
        reference.putFile(imageuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        // Get a URL to the uploaded content
                        Task<Uri> uriTasks=taskSnapshot.getStorage().getDownloadUrl();

                        while (!uriTasks.isSuccessful());
                        Uri uri=uriTasks.getResult();
                        //Upload upload=new Upload(imagename, uri.toString());
                        Upload upload = new Upload(imagename,uri.toString(),location,addresses,rentAmount,name,phoneNumber,email);







                        String uploadid=databaseReference.push().getKey();
                        databaseReference.child(uploadid).setValue(upload);

                        Toast.makeText(getApplicationContext(),"Advertisement  is posted successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        Toast.makeText(getApplicationContext(),"Ad is not stored successfully"+exception,Toast.LENGTH_LONG).show();
                    }
                });


    }

    private void openfilechooser() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null) {
            imageuri = data.getData();
            Picasso.with(this).load(imageuri).into(imageViewbutton);
        }
    }





    private void crateLocationREquest()
    {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);//time :1000ms

        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(5000);


        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},101);
            return;



        }

        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback,null);



    }

}
