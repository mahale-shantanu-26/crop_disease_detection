package bfskhgf.camapp;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

import static android.app.Activity.RESULT_OK;

public class MainActivity extends AppCompatActivity {

    private Button b1,b2,b3;
//    static final int REQUEST_CODE = 1;
Uri imageUri;

    private ImageView i1;
    private static final int REQUEST_IMAGE_CAPTURE = 101;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 202;
    private static final int STORAGE = 303;
    private static int RESULT_LOAD_IMAGE = 1;

    public static String loc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i1 = findViewById(R.id.imgv);



        b1 = findViewById(R.id.takePicture);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                    Toast.makeText(MainActivity.this, "Now the app has camera permission. Click again to take picture.",Toast.LENGTH_LONG).show();
                }
                else{
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, "New Picture");
                    values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                    imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });


        b2 = findViewById(R.id.getFromGallery);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        b3 = findViewById(R.id.diagnose);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent log = new Intent(MainActivity.this,classifier.class);
                startActivity(log);
            }
        });

    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }




    protected void onActivityResult(int rc, int resc, Intent data) {
        super.onActivityResult(rc,resc,data);
        if(rc==REQUEST_IMAGE_CAPTURE && resc==RESULT_OK){
            Bitmap bt; //= (Bitmap) data.getExtras().get("data");
            try{
                bt = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), imageUri);
                i1.setImageBitmap(bt);
                String imageurl = getRealPathFromURI(imageUri);
                loc = imageurl;
                Log.d("Image path: ",imageurl);

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        if (rc == RESULT_LOAD_IMAGE && resc == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.imgv);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            loc=picturePath;
            Log.d("Image path: ",picturePath);
        }
    }
}
