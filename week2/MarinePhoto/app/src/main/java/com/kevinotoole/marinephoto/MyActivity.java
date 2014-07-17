package com.kevinotoole.marinephoto;

/**
 * Author: Kevin OToole
 * MDF3 Term 1407
 * Week 2 Project
 * Project: Marine Photo
 * Package: com.kevinotoole.marinephoto;
 * File: MyActivity.java
 * Purpose: this is just a simple camera app with a Marine Corp theme:  The app will allow the user
 * to take a photo - that photo will be saved into the gallery under the application name as well
 * as show the photo in an imageView within the application.  A notification will appear in the
 * notification bar, letting the user know that the photo was saved to the gallery and they can
 * click on that notification which will take them to the gallery.
 */

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class MyActivity extends Activity {

    //Global Variables:
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    Button photoBtn;
    Button clearBtn;
    ImageView photoImg;
    TextView usmcTermText;
    EditText photoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        photoImg = (ImageView)findViewById(R.id.photoImage);
        photoBtn = (Button) findViewById(R.id.photoButton);
        photoBtn.setOnClickListener(cameraListener);
        clearBtn = (Button) findViewById(R.id.clearButton);
        usmcTermText = (TextView) findViewById(R.id.usmcText);
        photoName = (EditText) findViewById(R.id.photoName);

        //Create random quote/terminology for usmcTermText:
        //Set USMC Terminology & Quotes Random text so different with each refresh.
        int usmcRandom = (int) Math.ceil(Math.random() * 100);
        Random randomUSMC = new Random();
        int[] randomArray = new int[]{R.string.semperFi, R.string.devilDog, R.string.oohRah, R.string.quote1, R.string.theFew,
                R.string.once, R.string.jarHead, R.string.ega};
        int randomText = randomUSMC.nextInt(randomArray.length -1);
        if (usmcRandom < 100){
            usmcTermText.setText(randomArray[randomText]);
        }
        Log.i("USMCTEXT", usmcTermText.getText().toString());

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoImg.setImageBitmap(null);
            }
        });

    }

    //Create file Uri for saving image to:
    public static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    //Create file for saving image:
    public static File getOutputMediaFile(int type) {

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MarinePhoto");

        //Create storage directory if does not exist:
        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                Log.d("MarinePhoto", "failed to create directory");
                return null;
            }
        }

        //Create media file name:
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp  + ".jpg");
        }
        else if (type == MEDIA_TYPE_VIDEO){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_" + timeStamp + ".mp4");
        }
        else {
            return null;
        }
        return mediaFile;
    }

    //OnClickListener for take photo button:
    private View.OnClickListener cameraListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            takePhoto();
        }
    };

    //Method to take picture
    private void takePhoto(){

        //Create intent to take picture:
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Create file to save images to, set the image file name:
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        Log.i("PHOTO", fileUri.toString());

        //Start the image capture intent:
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == Activity.RESULT_OK){

            //Intent to save file to gallery:
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);

            Uri selectedImage = fileUri;
            getContentResolver().notifyChange(selectedImage, null);

            mediaScanIntent.setData(fileUri);
            sendBroadcast(mediaScanIntent);

            //Save Image to ImageView:
            photoImg = (ImageView) findViewById(R.id.photoImage);
            ContentResolver cr = getContentResolver();
            Bitmap bitmap;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(cr, selectedImage);
                photoImg.setImageBitmap(bitmap);

            } catch (Exception e){
                Log.e("SAVE_IMAGE", e.toString());
            }

            //Create Notification when photo is saved:
            Notification notification;
            NotificationCompat.Builder nb = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.notification) // Set small icon:
                    .setLargeIcon(((BitmapDrawable) this.getResources().getDrawable(R.drawable.icon)).getBitmap()) //Set large icon:
                    .setContentTitle("Marine Photo")  //Set Title:
                    .setContentText("Your photo has been saved.")  //Set Text:
                    .setSubText("Click to view photo in gallery");  //Set the sub text:

            //Create Intent to open photo in photo gallery from notification bar:
            String file = fileUri.toString();
            Intent photoIntent = new Intent();
            photoIntent.setAction(Intent.ACTION_VIEW);
            photoIntent.setDataAndType(Uri.parse(file), "image/*");

            //Create Pending Intent:
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, photoIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            nb.setContentIntent(pendingIntent);

            //Set notification and set flag to auto cancel so notification is removed when clicked:
            notification = nb.build();
            notification.flags = Notification.FLAG_AUTO_CANCEL;

            //Set notification manager:
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            int SMI_NOTIFICATION = 1;
            nm.notify(SMI_NOTIFICATION, notification);

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
