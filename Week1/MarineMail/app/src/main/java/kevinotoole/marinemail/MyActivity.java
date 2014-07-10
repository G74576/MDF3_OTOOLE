package kevinotoole.marinemail;

/**
 * Author: Kevin OToole
 * MDF3 Term 1407
 * Week 1 Project
 * Project: Marine Mail
 * Package: kevinotoole.marinemail;
 * File: MainActivity.java
 * Purpose: The purpose of this app is to share photos using this application which will also attach
 *          a random military terminology, quote or other Marine Corp related message to the end
 *          of the message written.  After sharing the photo through Marine Mail the user will be
 *          asked to choose an email client to use to send the message.
 *          ** Working on making it so the user can send straight from this application without
 *             having to use another email client to send the message **
 */

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MyActivity extends Activity {

    //Global Variables:
    Context _context = this;
    EditText toEmail;
    EditText toSubject;
    EditText toMessage;
    TextView usmcText;
    TextView imageText;
    Uri imageUri;
    String sharedText;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //Declare Variables:
        toEmail = (EditText) findViewById(R.id.emailAddress);
        toSubject = (EditText) findViewById(R.id.subject);
        toMessage = (EditText) findViewById(R.id.message);
        usmcText = (TextView) findViewById(R.id.usmcText);
        imageText = (TextView) findViewById(R.id.attImgText);
        sendButton = (Button) findViewById(R.id.send);

        //Get Intent, String action & Mime type:
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null){
            if (type.startsWith("image/*")){
                attachedImage(intent);
            } else if ("text/plain".equals(type)){
                attachText(intent);
            }
        }else {
            //OnClickListener for Send Button:
            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Check to see if valid email:
                    String toEm = toEmail.getText().toString();
                    if (!isValidEmail(toEm)){
                        toEmail.setError("Please enter a valid email address.");
                    } else {
                        toEmail.setError(null);
                        sendEmailnoAtt();
                    }
                }
            });
        }

        //Set USMC Terminology & Quotes Random text so different with each refresh.
        int usmcRandom = (int) Math.ceil(Math.random() * 100);
        Random randomUSMC = new Random();
        int[] randomArray = new int[]{R.string.semperFi, R.string.devilDog, R.string.oohRah, R.string.quote1, R.string.theFew,
                R.string.once, R.string.jarHead, R.string.ega};
        int randomText = randomUSMC.nextInt(randomArray.length -1);
        if (usmcRandom < 100){
            usmcText.setText(randomArray[randomText]);
        }
        Log.i("USMCTEXT", usmcText.toString());

    }

    //Method to attach and send photo:
    void attachedImage(Intent intent){
        imageUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null){
            Log.i("IMAGEURI_TEXT", String.valueOf(imageUri));

            //Set attachement text to imageUri string:
            imageText.setText(String.valueOf(imageUri));

            //OnClickListener for Send Button:
            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Check to see if valid email:
                    String toEm = toEmail.getText().toString();
                    if (!isValidEmail(toEm)){
                        toEmail.setError("Please enter a valid email address.");
                    } else {
                        toEmail.setError(null);
                        sendEmail(imageUri);
                    }
                }
            });
        }
    }

    //Method to attach text:
    void attachText(Intent intent){
        sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null){
            Log.i("SHARED_TEXT", String.valueOf(sharedText));

            //Set attachement text to imageUri string:
            imageText.setText(String.valueOf(sharedText));

            //OnClickListener for Send Button:
            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Check to see if valid email:
                    String toEm = toEmail.getText().toString();
                    if (!isValidEmail(toEm)){
                        toEmail.setError("Please enter a valid email address.");
                    } else {
                        toEmail.setError(null);
                        sendEmailText(sharedText);
                    }
                }
            });
        }
    }

    void sendEmail(Uri imageUri){
        String to = toEmail.getText().toString();
        String subj = toSubject.getText().toString();
        String mess = toMessage.getText().toString();
        String termText = usmcText.getText().toString();
        String finalMsg = (mess + "\n\n" + termText);

        //To send the email with the photo attached:
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[] {to});
        email.putExtra(Intent.EXTRA_SUBJECT, subj);
        email.putExtra(Intent.EXTRA_TEXT, finalMsg);
        email.putExtra(Intent.EXTRA_STREAM, imageUri);

        //Returns user to the app after the message is sent:
        email.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Prompt for email client:
        email.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(email, "Choose an Email Client"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(_context, R.string.noEmail, Toast.LENGTH_LONG).show();
        }
    }

    void sendEmailText(String sharedText){
        String to = toEmail.getText().toString();
        String subj = toSubject.getText().toString();
        String mess = toMessage.getText().toString();
        String termText = usmcText.getText().toString();
        String finalMsg = (mess + "\n\n" + sharedText + "\n\n" + termText);

        //To send the email with the photo attached:
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[] {to});
        email.putExtra(Intent.EXTRA_SUBJECT, subj);
        email.putExtra(Intent.EXTRA_TEXT, finalMsg);

        //Returns user to the app after the message is sent:
        email.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Prompt for email client:
        email.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(email, "Choose an Email Client"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(_context, R.string.noEmail, Toast.LENGTH_LONG).show();
        }
    }

    void sendEmailnoAtt(){
        String to = toEmail.getText().toString();
        String subj = toSubject.getText().toString();
        String mess = toMessage.getText().toString();
        String termText = usmcText.getText().toString();
        String finalMsg = (mess + "\n\n" + termText);

        //To send the email with the photo attached:
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[] {to});
        email.putExtra(Intent.EXTRA_SUBJECT, subj);
        email.putExtra(Intent.EXTRA_TEXT, finalMsg);

        //Returns user to the app after the message is sent:
        email.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Prompt for email client:
        email.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(email, "Choose an Email Client"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(_context, R.string.noEmail, Toast.LENGTH_LONG).show();
        }
    }

    //Method to validate Email Address
    public static boolean isValidEmail(CharSequence target){
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
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
