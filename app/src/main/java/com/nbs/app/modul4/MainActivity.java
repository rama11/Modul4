package com.nbs.app.modul4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    static final int READ_BLOCK_SIZE = 100;
    private Button btnSave, btnLoad, button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Simple File Storage (Internal)");

        btnSave = (Button) findViewById(R.id.btnSave);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        editText = (EditText) findViewById(R.id.editInput);
        button = (Button) findViewById(R.id.button);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fOut = openFileOutput("myText.txt", MODE_PRIVATE);
                    fOut.write(editText.getText().toString().getBytes());
                    fOut.close();
                    Toast.makeText(getBaseContext(), "File Saved Successfully", Toast.LENGTH_SHORT).show();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                editText.setText("");
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fIn = openFileInput("myText.txt");
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

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExternalStorage.class);
                startActivity(intent);
            }
        });
    }

}
