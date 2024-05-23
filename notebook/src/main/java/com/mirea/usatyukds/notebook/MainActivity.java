package com.mirea.usatyukds.notebook;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public EditText fileName;
    public EditText quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fileName = findViewById(R.id.NameOfFile);
        quote = findViewById(R.id.Quote);

        Button buttonSave = findViewById(R.id.ToFileButton);
        Button buttonLoad = findViewById(R.id.FromFileButton);

        buttonSave.setOnClickListener(v -> WriteToFileFromForm());
        buttonLoad.setOnClickListener(v -> ReadFileFromFile());
    }

    public	void	WriteToFileFromForm()	{
        String NameOfFile = fileName.getText().toString().trim();
        String Quote = quote.getText().toString().trim();
        File	path	=	Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, NameOfFile + ".txt");
        try	{
            FileOutputStream	fileOutputStream	=	new	FileOutputStream(file.getAbsoluteFile());
            OutputStreamWriter output	=	new	OutputStreamWriter(fileOutputStream);
            //	Запись строки в файл
            output.write(Quote);
            //	Закрытие потока записи
            output.close();

        }	catch	(IOException	e)	{
            Log.w("ExternalStorage",	"Error	writing	"	+	file,	e);
        }
    }

    private	void ReadFileFromFile() {
        String NameOfFile = fileName.getText().toString().trim();
        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, NameOfFile + ".txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            List<String> lines = new ArrayList<String>();
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            quote.setText(lines.toString().trim());
            Log.w("ExternalStorage", String.format("Read	from	file	%s	successful", lines.toString()));
        } catch (Exception e) {
            Log.w("ExternalStorage", String.format("Read	from	file	%s	failed", e.getMessage()));
        }
    }
}