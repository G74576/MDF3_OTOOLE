package com.kevinotoole.marinegrub;

/**
 * Author: Kevin OToole
 * MDF3 Term 1407
 * Week 4 Project
 * Project: Marine Grub
 * Package: com.kevinotoole.marinegrub;
 * File: MyActivity.java
 * Purpose:
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

@SuppressLint("SetJavaScriptEnabled")
public class MyActivity extends Activity {

    WebView webView;
    String fName;
    String lName;
    String eMail;
    String pHone;

    String url = "file:///Users/kevinotoole/Desktop/MDF3_OTOOLE/Week4/MarineGrub/app/src/main/res/asset/index.html";

    @SuppressLint("AddJavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //Set WebView & JavaScript Interface:
        webView = (WebView)findViewById(R.id.webView);
        webView.loadUrl(url);
        //webView.setBackgroundColor(0x00000000);
        WebSettings webSettings = webView.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebInterface(this), "Android");
    }

    public class WebInterface {

        Context mContext;

        WebInterface(Context context){mContext = context;}
        @JavascriptInterface
        public void sendInfo(String fname, String lname, String email, String phone) {

            fName = fname;
            lName = lname;
            eMail = email;
            pHone = phone;

            String subject = "Please add me to Marine Grub mailing list.";

            String emailMsg = "My contact information: " + "\n\n"
                    + "First Name: " + fName + "\n\n"
                    + "Last Name: " + lName + "\n\n"
                    + "Email Address: " + eMail + "\n\n"
                    + "Phone Number: " + pHone + ".";

            Intent emailIntent = new Intent(Intent.ACTION_SEND);


            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"kmotoole78@fullsail.edu"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, emailMsg);

            emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            emailIntent.setType("message/rfc822");

            startActivity(Intent.createChooser(emailIntent, "Choose an Email Client"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
