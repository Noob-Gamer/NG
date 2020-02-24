package com.example.apmarkertadmin.Fragment;


import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.apmarkertadmin.Activity.FullScreenImageActivity;
import com.example.apmarkertadmin.Activity.HomeActivity;
import com.example.apmarkertadmin.Activity.ViewActivity;
import com.example.apmarkertadmin.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddDetailFragment extends Fragment implements View.OnClickListener{
    private EditText Name;
    private LinearLayout chooseImg;
    private final int CODE_MULTIPLE_IMG_GALLERY = 2;
    private FusedLocationProviderClient client;
    private AutoCompleteTextView Region;
    private EditText LocationDa;
    private Toolbar toolbar;
    private ImageView imageViewAdd1,imageViewAdd2,imageViewAdd3,imageViewAdd4;
    private Bitmap bitmap;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;

    public AddDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        Name = view.findViewById(R.id.E_Name);
        Region = view.findViewById(R.id.Select_Region);
        LocationDa = view.findViewById(R.id.Edit_Add_Location);
        toolbar = view.findViewById(R.id.toolBar1);
        imageViewAdd1 = view.findViewById(R.id.img_add1);
        imageViewAdd2 = view.findViewById(R.id.img_add2);
        imageViewAdd3 = view.findViewById(R.id.img_add3);
        imageViewAdd4 = view.findViewById(R.id.img_add4);
        imageViewAdd1.setOnClickListener(this);
        imageViewAdd2.setOnClickListener(this);
        imageViewAdd3.setOnClickListener(this);
        imageViewAdd4.setOnClickListener(this);
        chooseImg = view.findViewById(R.id.choose_img_layout);
        collapsingToolbarLayout=view.findViewById(R.id.collapsing_toolbar1);
        appBarLayout = view.findViewById(R.id.appbar1);
        ((AppCompatActivity)requireActivity()).setSupportActionBar(toolbar);
        if(HomeActivity.f){
            toolbar.setTitle("Edit Info");
            Name.setText(getActivity().getIntent().getStringExtra("NAME"));
            Region.setText(getActivity().getIntent().getStringExtra("REGION"));
        }
        else {
            toolbar.setTitle("Add Info");
            LocationDa.setText(HomeActivity.loca);
        }
        //try {
/*
            bitmap = ((BitmapDrawable) imageViewAdd1.getContext().getDrawable()).getBitmap();
            getActivity().getWindow().setStatusBarColor(getDominantColor(bitmap));
            toolbar.setTitleTextColor(getDominantContrassColor(bitmap));
            Canvas canvas = new Canvas(bitmap);
            imageViewAdd1.getDrawable().setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            imageViewAdd1.getDrawable().draw(canvas);
        }catch (OutOfMemoryError e){
            e.printStackTrace();
        }*/


     /*   appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow= true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if(scrollRange == -1){
                    scrollRange=appBarLayout.getTotalScrollRange();
                }
                //When Scroll bar on the top
                if(scrollRange+i==0){
                    //collapsingToolbarLayout.setTitle(toolbar.getTitle());
                    toolbar.setBackgroundColor(getDominantColor(((BitmapDrawable)imageViewAdd1.getDrawable()).getBitmap()));
                    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.BLACK);
                    isShow= true;
                }
                else if(isShow){
                    //collapsingToolbarLayout.setTitle(toolbar.getTitle());
                    toolbar.setBackgroundColor(Color.TRANSPARENT);
                    collapsingToolbarLayout.setCollapsedTitleTextColor(getDominantContrassColor(((BitmapDrawable)imageViewAdd1.getDrawable()).getBitmap()));
                    isShow=false;
                }
            }
        });*/


        LocationDa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              getLocation();
            }
        });


    }
    private void getLocation(){
            client.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
//                            Toast.makeText(getContext(), "Wait a few second to get location address", Toast.LENGTH_SHORT).show();
                        LocationDa.setText(location.getLatitude() + "," + location.getLongitude());
                    }
                    else {
                        Toast.makeText(getContext(), "Open and Close location 3 times", Toast.LENGTH_SHORT).show();

                    }
                }
            });
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.addimage,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.img_one_insert){
            startActivityForResult(Intent.createChooser(new Intent().
                    setAction(Intent.ACTION_GET_CONTENT).
                    setType("image/*"),"Select only one Image"),1);
        }
        if(id == R.id.img_insert){
            Intent gallery= new Intent();
            gallery.setType("image/*");
            gallery.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
            gallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(gallery,"Choose Img"), 2);
            //ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},999);
        }
        return super.onOptionsItemSelected(item);
    }
    private byte[] imageTobyte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
       /* OutputStream stream = new FileOutputStream(String.valueOf(
                getContext().getFilesDir() + pathImage + "/" + idPicture + ".jpg"));*/
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }


   /* @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, 0);
            }
            else
            {
                Toast.makeText(getActivity(), "You don't have the permission to access file", Toast.LENGTH_SHORT).show();
            }
            return;
            //startActivityForResult(Intent.createChooser(intent,"Select Apartment Img"), CODE_MULTIPLE_IMG_GALLERY);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if(requestCode == 1){
                Uri imageUri = data.getData();
                if(imageUri != null){
                    imageViewAdd1.setImageURI(imageUri);
                }
            }
            else if (requestCode == 2) {
             // Uri imageUri = data.getData();

                    /*InputStream inputStream= getContext().getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageViewAdd1.setImageBitmap(bitmap);*/
// only one picture ko allow ma loke bu
                ClipData imgaeClip = data.getClipData();
                try {
                    if (imgaeClip != null) {
                        if(imgaeClip.getItemAt(0).getUri() != null || imgaeClip.getItemAt(1).getUri() != null
                        || imgaeClip.getItemAt(2).getUri() != null || imgaeClip.getItemAt(3).getUri() != null)
                        {
                            Toast.makeText(getActivity(),"one",Toast.LENGTH_LONG).show();
                            imageViewAdd1.setImageURI(imgaeClip.getItemAt(0).getUri());
                            imageViewAdd2.setImageURI(imgaeClip.getItemAt(1).getUri());
                            imageViewAdd3.setImageURI(imgaeClip.getItemAt(2).getUri());
                            imageViewAdd4.setImageURI(imgaeClip.getItemAt(3).getUri());
                        }
                        else {
                            Toast.makeText(getActivity(),"five",Toast.LENGTH_LONG).show();
                            imageViewAdd1.setImageURI(imgaeClip.getItemAt(0).getUri());
                        }
                   /* for (int i = 0; i < imgaeClip.getItemCount(); i++) {
                        ClipData.Item item = imgaeClip.getItemAt(i);
                        Uri uri = item.getUri();
                    }*/
                    }
                }catch (Exception e){
                }


            }

        }
    }

    public static final int getDominantColor(Bitmap bitmap){
        Bitmap gBitmap = Bitmap.createScaledBitmap(bitmap, 1,1,true);
        final int gColor = gBitmap.getPixel(0,0);
        gBitmap.recycle();
        return gColor;
    }
    public static final int getDominantContrassColor(Bitmap bitmap){
        Bitmap gBitmap = Bitmap.createScaledBitmap(bitmap, 1,1,true);
        final int gColor = gBitmap.getPixel(0,0);
        gBitmap.recycle();
        int red = Color.red(gColor);
        int green = Color.green(gColor);
        int blue = Color.blue(gColor);
        final int newColor = Color.rgb(255-red,255-green,255-blue);
        return newColor;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_add1:
                byte[] newEntryImg = imageTobyte(imageViewAdd1);
                Intent intent = new Intent(getActivity(),FullScreenImageActivity.class);
                intent.putExtra("img", newEntryImg);
                startActivity(intent);
                break;
            case R.id.img_add2:
                byte[] newEntryImg1 = imageTobyte(imageViewAdd2);
                Intent intent3 = new Intent(getContext(), FullScreenImageActivity.class);
                intent3.putExtra("img", newEntryImg1);
                startActivity(intent3);
                break;
            case R.id.img_add3:
                byte[] newEntryImg2 = imageTobyte(imageViewAdd3);
                Intent intent2 = new Intent(getContext(), FullScreenImageActivity.class);
                intent2.putExtra("img", newEntryImg2);
                startActivity(intent2);
                break;
            case R.id.img_add4:
                byte[] newEntryImg3 = imageTobyte(imageViewAdd4);
                Intent intent1 = new Intent(getContext(), FullScreenImageActivity.class);
                intent1.putExtra("img", newEntryImg3);
                startActivity(intent1);
                break;
        }
    }

}
