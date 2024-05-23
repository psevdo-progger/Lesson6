package com.mirea.usatyukds.employeedb;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

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

        TextView SuperHero = findViewById(R.id.SuperHero);
            AppDatabase db = App.getInstance().getDatabase();
            EmployeeDao employeeDao = db.employeeDao();
            Employee employee = new Employee();
            employee.name = "John Smith ";
            employee.superPower = "is cool, gets money";
            // запись сотрудников в базу
            employeeDao.insert(employee);
            // Загрузка всех работников
            List<Employee> employees = employeeDao.getAll();
            // Получение определенного работника с id = 1
//            employee = employeeDao.getById(1);
            // Обновление полей объекта
//            employee.superPower = "go for a walk";
//            employeeDao.update(employee);
            String hero_text = employee.name + employee.superPower;
            SuperHero.setText(hero_text);
            Log.d(TAG, employee.name + " " + employee.superPower);
    }
}