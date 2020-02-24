package com.example.apmarkertadmin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apmarkertadmin.Fragment.AddDetailFragment;
import com.example.apmarkertadmin.R;

import java.io.ByteArrayOutputStream;

public class ViewActivity extends AppCompatActivity {
    private TextView tName,tRegion,tDate,tTown,tAddress,tType,tPhone,tFees,tArea,tContract,tDes;
    private ImageView img_View;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        setUiId();
        setSupportActionBar(toolbar);
        tName.setText(getIntent().getStringExtra("name"));
        tRegion.setText(getIntent().getStringExtra("region"));
        img_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] newEntryImg = imageTobyte(img_View);
                Intent intent = new Intent(ViewActivity.this,FullScreenImageActivity.class);
                intent.putExtra("img", newEntryImg);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);
        return true;
    }
    private byte[] imageTobyte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
         if(id == R.id.menu_edit) {
             Intent intent  = new Intent(ViewActivity.this, HomeActivity.class);
             intent.putExtra("EXTRA", "openFragment");
             intent.putExtra("NAME", tName.getText().toString());
             intent.putExtra("REGION",tRegion.getText().toString());
             startActivityForResult(intent,2);
             finish();

         }

        return true;
    }

    private void setUiId() {
        toolbar = findViewById(R.id.toolbar);
        tName = findViewById(R.id.view_name);
        tAddress = findViewById(R.id.view_address);
        tArea = findViewById(R.id.view_area);
        tContract = findViewById(R.id.view_contract);
        tDate = findViewById(R.id.view_date);
        tDes = findViewById(R.id.view_des);
        tFees = findViewById(R.id.view_fees);
        tRegion = findViewById(R.id.view_region);
        tTown = findViewById(R.id.view_township);
        tPhone = findViewById(R.id.view_phone);
        tType = findViewById(R.id.view_type);
        img_View = findViewById(R.id.img_view);

    }
}
