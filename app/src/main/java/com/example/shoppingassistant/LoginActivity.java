package com.example.shoppingassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingassistant.data.database.AppDatabaseHelper;
import com.example.shoppingassistant.data.service.UserService;

public class LoginActivity extends AppCompatActivity {

    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log in");

        EditText loginEmailView = (EditText) findViewById(R.id.register_email);
        EditText loginPasswordView = (EditText) findViewById(R.id.register_password);

        Button loginActionViewButton = (Button) findViewById(R.id.login_button);
        Button registerActionViewButton = (Button) findViewById(R.id.register_btn);

        userService = new UserService(new AppDatabaseHelper(getApplicationContext()));

        loginActionViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmailView.getText().toString();
                String password = loginPasswordView.getText().toString();

                if (userService.authenticate(email, password)) {
                    Intent startMainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startMainActivityIntent.putExtra("user_email", email);

                    startActivity(startMainActivityIntent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid user name or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerActionViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startRegisterActivityIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(startRegisterActivityIntent);
            }
        });
    }
}