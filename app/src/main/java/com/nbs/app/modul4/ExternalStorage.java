package com.nbs.app.modul4;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ExternalStorage extends AppCompatActivity {

    static final int READ_BLOCK_SIZE = 100;
    private Button btnSave, btnLoad;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        btnSave = (Button) findViewById(R.id.btnSave2);
        btnLoad = (Button) findViewById(R.id.btnLoad2);
        editText = (EditText) findViewById(R.id.editInput2);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    File sdCard = Environment.getExternalStorageDirectory();
                    File dir = new File(sdCard.getAbsolutePath() + "/MyFiles");

                    if (!dir.exists())
                        dir.mkdir();

                    File file = new File(dir, "textfile.txt");
                    FileOutputStream fOut = new FileOutputStream(file);
                    OutputStreamWriter osw = new OutputStreamWriter(fOut);

                    osw.write(editText.getText().toString());
                    osw.flush();
                    osw.close();

                    Toast.makeText(getBaseContext(), "File Saved Successfully", Toast.LENGTH_SHORT).show();

                    editText.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    File sdCard = Environment.getExternalStorageDirectory();
                    File dir = new File(sdCard.getAbsolutePath() + "/MyFiles");
                    File file = new File(dir, "textFile.txt");

                    FileInputStream fIn = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(fIn);
                    char[] inputBuffer = new char[READ_BLOCK_SIZE];
                    String s = "";
                    int charRead;
                    while ((charRead = isr.read(inputBuffer)) > 0) {
                        s += String.copyValueOf(inputBuffer, 0, charRead);
                        inputBuffer = new char[READ_BLOCK_SIZE];
                    }
                    editText.setText(s);
                    Toast.makeText(getBaseContext(), "File loaded successfully", Toast.LENGTH_SHORT).show();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        });

    }
}
