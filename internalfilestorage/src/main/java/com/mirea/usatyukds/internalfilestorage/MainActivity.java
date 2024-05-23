package com.mirea.usatyukds.internalfilestorage;

import static android.os.Environment.DIRECTORY_DOCUMENTS;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private String fileName = "mirea.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText tv = findViewById(R.id.editTextText);
        Button buttonWrite = findViewById(R.id.buttonWriteToFile);
        Button buttonShow = findViewById(R.id.buttonShowFile);
        TextView textView = findViewById(R.id.textView);

        buttonShow.setOnClickListener(view -> {
            textView.setText(getTextFromFile());
        });

        buttonWrite.setOnClickListener(view -> {
            String data = tv.getText().toString();
            writeFileToExternalStorage(data);
        });
    }

    // открытие файла
    public String getTextFromFile() {
        FileInputStream fin = null;
        try {
            fin = openFileInput(fileName);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            Log.d(LOG_TAG, text);
            return text;
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }

    public void writeFileToExternalStorage(String data) {
        FileOutputStream outputStream = null;
        try {
            outputStream = openFileOutput(fileName, Context.MODE_APPEND); // Используем MODE_APPEND для добавления данных в конец файла
            String formattedData = data + System.getProperty("line.separator");
            outputStream.write(formattedData.getBytes());
            Toast.makeText(this, "Данные успешно сохранены", Toast.LENGTH_SHORT).show(); // Добавляем уведомление об успешном сохранении данных
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ошибка при сохранении данных", Toast.LENGTH_SHORT).show(); // Добавляем уведомление об ошибке при сохранении данных
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}