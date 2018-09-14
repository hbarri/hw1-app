package com.test.hw1;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;

/**
 * Haneen Barri
 * Software Engineering - Professor Derek Doran
 * HW1; implement random number generator to change text & implement draw pad.
 */
public class MainActivity extends AppCompatActivity {
    //declare canvas for draw pad
    private PaintView paintView;

    /**
     * onCreate method to launch main activity xml
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set screen to main activity page
        setContentView(R.layout.activity_main);
    }

    /**
     * method to launch part 1
     * @param view
     */
    public void part1action(View view) {
        //when part1 button is pressed, change layout
        setContentView(R.layout.part1);
    }

    /**
     * creates random hex color
     * @return random hex value
     */
    public String randomColor()
    {
        //create random numbers for RGB values
        Random rnd = new Random();
        int r = rnd.nextInt(255) + 0;
        int g = rnd.nextInt(255) + 0;
        int b = rnd.nextInt(255) + 0;
        String rgb = r + "r, " + g + "g, " + b + "b, ";

        //convert to hex values
        String rHex = Integer.toHexString(r);
        if (rHex.length() < 2) { rHex = "0" + rHex; }
        String gHex = Integer.toHexString(g);
        if (gHex.length() < 2) { gHex = "0" + gHex; }
        String bHex = Integer.toHexString(b);
        if (bHex.length() < 2) { bHex = "0" + bHex; }
        String hex = "#" + rHex + gHex + bHex;

        //set textbox to reflect random value picked
        TextView tv = (TextView)findViewById(R.id.colorValueTxt);
        tv.setText("COLOR: " + rgb + hex);

        //return hex value
        return hex;
    }

    /**
     * method to change text color
     * @param view
     */
    public void part1Button(View view) {
        //if color change button is pressed, edit text to change color to that value
        EditText myEditText = (EditText)findViewById(R.id.colorChangeTxt);
        myEditText.setTextColor(Color.parseColor(randomColor()));
    }

    /**
     * method to go back to main screen
     * @param view
     */
    public void homeButton(View view) {
        //set content view to main screen if home button is pressed
        setContentView(R.layout.activity_main);
    }

    /**
     * method to launch part 2
     * @param view
     */
    public void part2action(View view) {
        //set content view to part2 screen when button is pressed
        setContentView(R.layout.part2);
        //find draw pad
        paintView = (PaintView)findViewById(R.id.paintView);

        //find RGB value colors chosen
        final NumberPicker np1 = (NumberPicker) findViewById(R.id.np1);
        final NumberPicker np2 = (NumberPicker) findViewById(R.id.np2);
        final NumberPicker np3 = (NumberPicker) findViewById(R.id.np3);

        //set max and min RGB values
        np1.setMaxValue(255);
        np1.setMinValue(0);
        np1.setWrapSelectorWheel(false);
        np2.setMaxValue(255);
        np2.setMinValue(0);
        np2.setWrapSelectorWheel(false);
        np3.setMaxValue(255);
        np3.setMinValue(0);
        np3.setWrapSelectorWheel(false);

        //listener for R value change
        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //update new color for paint
                PaintView paintView = (PaintView)findViewById(R.id.paintView);
                paintView.setColor(Color.rgb(newVal, np2.getValue(), np3.getValue()));
            }
        });

        //listener for G value change
        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //update new color for paint
                PaintView paintView = (PaintView)findViewById(R.id.paintView);
                paintView.setColor(Color.rgb(np1.getValue(), newVal, np3.getValue()));
            }
        });

        //listener for B value change
        np3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //update new color for paint
                PaintView paintView = (PaintView)findViewById(R.id.paintView);
                paintView.setColor(Color.rgb(np1.getValue(), np2.getValue(), newVal));
            }
        });
    }

    /**
     * method to erase part 2 canvas
     * @param view
     */
    public void eraseBoard(View view) {
        //clear paint canvas
        part2action(view);
    }

    /**
     * method to save part 2 canvas
     * @param view
     */
    public void saveCanvas(View view) {
        try {
            //create random number for file name
            Random rnd = new Random();
            int imageNum = rnd.nextInt();

            //get bitmap from draw pad
            Bitmap b = paintView.getBm();

            //find location to save picture
            String root = Environment.getExternalStorageDirectory().toString();
            //create file
            File myDir = new File(root);
            myDir.mkdirs();
            String fname = "Image-" + imageNum + ".jpg";
            File file = new File(myDir, fname);
            //if file exists, find new file name
            while (file.exists()) {
                imageNum = rnd.nextInt();
                fname = "Image-" + imageNum + ".jpg";
                file = new File(myDir, fname);
            }
            //store log info
            Log.i("LOAD", root + fname);
            //create output stream
            FileOutputStream out = new FileOutputStream(file);
            //compress bitmap
            b.compress(Bitmap.CompressFormat.JPEG, 90, out);
            //flush and close
            out.flush();
            out.close();

            //have media scanner find new file to display to gallery
            MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String path, Uri uri) {
                    Log.i("ExternalStorage", "Scanned " + path + ":");
                    Log.i("ExternalStorage", "-> uri=" + uri);
                }
            });
        }
        //catch exception if error occurs
        catch(Exception e) {
            String error = e.toString();
        }
    }
}
