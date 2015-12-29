package com.libgdx.subin.tangled;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    database mydb;
    private static final int SELECT_IMAGE =1;
    Uri selectedImage;
    private ImageView imageView;
    final Context context = this;
    String tag1;
    EditText et,t;
    Button btn;
    ImageView image;
    private String path;
    private android.app.FragmentTransaction fragmentTransaction;
    private FragmentTransaction fragmentTransaction1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.imageViewGallery1);
        mydb = new database(this);


    }








           /* public void click(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("ADD TAG");

                // set the custom dialog components - text, image and button

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tag1 = t.getText().toString();//read(t.getText().toString());
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }*/


        //String tag =et.getText().toString();



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void open(MenuItem item) {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, SELECT_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        setContentView(R.layout.tagimage);
        if(resultCode == RESULT_OK&&requestCode==SELECT_IMAGE)
        {
            selectedImage = data.getData();
            path = getPath(selectedImage);
            imageView = (ImageView) findViewById(R.id.imageViewGallery);
            btn = (Button)findViewById(R.id.add);
            t = (EditText) findViewById(R.id.tagline);
            imageView.setImageBitmap(BitmapFactory.decodeFile(path));
        }

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tag1 = t.getText().toString();
                    Toast.makeText(getApplicationContext(),tag1,Toast.LENGTH_SHORT ).show();
                  boolean success= mydb.insert("shruti","fgh" );
                    if(success == true)
                        Toast.makeText(getApplicationContext(),"inserted",Toast.LENGTH_SHORT).show();
                    else if (success == false)
                        Toast.makeText(getApplicationContext(),"not inserted",Toast.LENGTH_SHORT).show();

                   Intent myint = new Intent(getApplicationContext(), MainActivity2.class);
                    myint.putExtra("s",path);
                    startActivity(myint);
                }
            });

    }

    public String getPath(Uri uri)
    {
        String[] filepathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri,filepathColumn,null,null,null);
        cursor.moveToFirst();
        int ColumnIndex = cursor.getColumnIndex(filepathColumn[0]);
        return cursor.getString(ColumnIndex);
    }
}
