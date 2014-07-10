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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends Activity {

    //Global Variables:
    Context _context = this;
    EditText toEmail;
    EditText toSubject;
    EditText toMessage;
    TextView usmcText;
    TextView imageText;
    Uri imageUri;
    Button sendButton;
    Boolean value;

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
            }
        }else {
            //Set Enabled:
            value = false;
            setEnabled(value);

            //Create an Alert Dialog here **************************************
        }

    }

    //Method to attach and send photo:
    void attachedImage(Intent intent){
        imageUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null){
            Log.i("IMAGEURI_TEXT", String.valueOf(imageUri));

            //Set attachement text to imageUri string:
            imageText.setText(String.valueOf(imageUri));

            //Set Enabled Value:
            value = true;
            setEnabled(value);

            //OnClickListener for Send Button:
            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendEmail(imageUri) ;
                }
            });
        }
    }

    void sendEmail(Uri imageUri){
        String to = toEmail.getText().toString();
        String subj = toSubject.getText().toString();
        String mess = toMessage.getText().toString();

        //To send the email with the photo attached:
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[] {to});
        email.putExtra(Intent.EXTRA_SUBJECT, subj);
        email.putExtra(Intent.EXTRA_TEXT, mess);
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

    //Set Enabled of to,subj,mess and button:
    void setEnabled(Boolean value){
        toEmail.setEnabled(value);
        toSubject.setEnabled(value);
        toMessage.setEnabled(value);
        sendButton.setEnabled(value);
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
