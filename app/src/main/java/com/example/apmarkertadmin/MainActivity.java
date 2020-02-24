package com.example.apmarkertadmin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.apmarkertadmin.Activity.HomeActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private ImageView Lan;
private Button Login,Myan,Eng;
private TextInputEditText user,pass;
  /*  public static SharedPreferences.Editor editor;
    public static SharedPreferences language_setting;
    public static String local_lang;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);
        Lan = findViewById(R.id.imgLan);
        Login = findViewById(R.id.Login_Btn);
        user = findViewById(R.id.LName);
        pass = findViewById(R.id.LPass);
        Lan.setOnClickListener(this);
        Login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgLan:
                chooseLanguage();
                break;
            case R.id.Login_Btn:
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void chooseLanguage() {
            int n ;
            final String[] LangChang = {"Myanmar", "English"};
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            //LayoutInflater inflater=getLayoutInflater();
           /* View diaView= inflater.inflate(R.layout.choose_language,null);
            builder.setView(diaView);*/
//            Myan =diaView.findViewById(R.id.MLang);
//            Eng =diaView.findViewById(R.id.ELang);
            builder.setTitle("Choose Language");
            builder.setSingleChoiceItems(LangChang, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(which == 0){
                                //Myanmar
                                Toast.makeText(getApplicationContext(),"You Choose",Toast.LENGTH_SHORT).show();
                                setLocale("my");
                                recreate();
                            }
                            else if (which == 1){
                                setLocale("en");
                                recreate();
                            }
                            dialog.dismiss();
                        }
                    });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
           /* Myan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setLocale("my");
                    Toast.makeText(getApplicationContext(),"You Choose Myanmar",Toast.LENGTH_SHORT).show();
                    recreate();
                    alertDialog.dismiss();
                }
            });
            Eng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"You Choose English",Toast.LENGTH_SHORT).show();
                    setLocale("en");
                    recreate();
                    alertDialog.dismiss();
                }
            });*/

        }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        /*Resources resources = getResources();
      DisplayMetrics dm = resources.getDisplayMetrics();
      Configuration config = resources.getConfiguration();
      if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
         config.setLocale(new Locale(localeCode.toLowerCase()));
      } else {
         config.locale = new Locale(localeCode.toLowerCase());
      }
      resources.updateConfiguration(config, dm);*/
        Configuration configuration = new Configuration();
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        configuration = resources.getConfiguration();
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            configuration.setLocale(new Locale(lang.toLowerCase()));
        } else {
            configuration.locale = new Locale(lang.toLowerCase());
        }
      //  configuration.locale = locale;
      //  getBaseContext().getResources().updateConfiguration(configuration, MainActivity.this.getBaseContext().getResources().getDisplayMetrics());
        //save data to sharepreferences
        resources.updateConfiguration(configuration,dm);
        SharedPreferences.Editor editor =getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();

    }
    private void loadLocale(){
        SharedPreferences preferences =getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_Lang","");
        setLocale(language);
    }

    /*  private void updateViews(String languageCode) {
          Context context = LocaleHelper.setLocale(this, languageCode);
          Resources resources = context.getResources();

          mTitleTextView.setText(resources.getString(R.string.main_activity_title));
          mDescTextView.setText(resources.getString(R.string.main_activity_desc));
          mAboutTextView.setText(resources.getString(R.string.main_activity_about));
          mToHIButton.setText(resources.getString(R.string.main_activity_to_tr_button));
          mToENButton.setText(resources.getString(R.string.main_activity_to_en_button));
          setTitle(resources.getString(R.string.main_activity_toolbar_title));
      }*/
    private void AutoChooseMyLang() {

    }
}
