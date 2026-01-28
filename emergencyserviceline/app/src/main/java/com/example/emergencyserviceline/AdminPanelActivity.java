package com.example.emergencyserviceline;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminPanelActivity extends AppCompatActivity {

    TextView tvAdminData, tvUserCount;
    EditText etDeleteId;
    Button btnDeleteUser;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        tvAdminData = findViewById(R.id.tvAdminData);
        tvUserCount = findViewById(R.id.tvUserCount);
        etDeleteId = findViewById(R.id.etDeleteId);
        btnDeleteUser = findViewById(R.id.btnDeleteUser);

        dbHelper = new DBHelper(this);

        loadUsers();

        // DELETE USER
        btnDeleteUser.setOnClickListener(v -> {
            String idStr = etDeleteId.getText().toString().trim();

            if (idStr.isEmpty()) {
                Toast.makeText(this, "Enter User ID", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean deleted = dbHelper.deleteUserById(Integer.parseInt(idStr));

            if (deleted) {
                Toast.makeText(this, "User Deleted", Toast.LENGTH_SHORT).show();
                etDeleteId.setText("");
                loadUsers();
            } else {
                Toast.makeText(this, "User Not Found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadUsers() {
        Cursor cursor = dbHelper.getAllUsers();

        int totalUsers = dbHelper.getUserCount();
        tvUserCount.setText("Total Registered Users : " + totalUsers);

        if (cursor.getCount() == 0) {
            tvAdminData.setText("No users registered yet");
            cursor.close();
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("REGISTERED USERS\n\n");

        while (cursor.moveToNext()) {
            sb.append("ID : ").append(cursor.getInt(0)).append("\n");
            sb.append("Name : ").append(cursor.getString(1)).append("\n");
            sb.append("Email : ").append(cursor.getString(2)).append("\n");
            sb.append("Password : ******\n"); // hidden
            sb.append("Mobile : ").append(cursor.getString(4)).append("\n");
            sb.append("State : ").append(cursor.getString(5)).append("\n");
            sb.append("Country : ").append(cursor.getString(6)).append("\n");
            sb.append("Age : ").append(cursor.getString(7)).append("\n");
            sb.append("Gender : ").append(cursor.getString(8)).append("\n");
            sb.append("Registered On : ").append(cursor.getString(9)).append("\n");
            sb.append("--------------------------\n\n");
        }

        cursor.close();
        tvAdminData.setText(sb.toString());
    }
}
