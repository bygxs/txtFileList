package com.biniyam.txtfilelist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button save;
    ArrayList<String> myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myList = new ArrayList<>();
        final TextView output = findViewById(R.id.output);
        final EditText enterText = findViewById(R.id.enterText);
        save = findViewById(R.id.savebtn);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!enterText.getText().toString().isEmpty()){
                    File file = new File(MainActivity.this.getFilesDir(), "textmine");
                    if (!file.exists()){
                        file.mkdir();
                    }
                    try {
                        File bgxfile = new File(file,"mytextfile#1");
                        FileWriter writer = new FileWriter(bgxfile);
                        writer.append(enterText.getText().toString());
                        writer.flush();
                        writer.close();


                        output.setText(readFile());
                        Toast.makeText(MainActivity.this, "successfuly saved", Toast.LENGTH_SHORT).show();

                    }catch (Exception e){}
                }
            }
        });

    }
    private String readFile(){
        File fileEvents = new File(MainActivity.this.getFilesDir()+"/textmine/mytextfile#1");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileEvents));
            String line;

            while ((line = bufferedReader.readLine()) != null){
                myList.add(line);
                text.append(line);
                text.append(' ');

            }
            bufferedReader.close();
        }catch (IOException e){}
            String result = Arrays.toString(new ArrayList[]{myList});
            return result;
        }



    }
