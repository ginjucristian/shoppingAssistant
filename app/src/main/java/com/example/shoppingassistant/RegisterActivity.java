package com.example.shoppingassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingassistant.data.database.AppDatabaseHelper;
import com.example.shoppingassistant.data.model.User;
import com.example.shoppingassistant.data.service.UserService;

public class RegisterActivity extends AppCompatActivity {

    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");

        userService = new UserService(new AppDatabaseHelper(getApplicationContext()));

        EditText registerFirstNameView = (EditText) findViewById(R.id.register_first_name);
        EditText registerLastNameView = (EditText) findViewById(R.id.register_last_name);
        EditText registerEmailView = (EditText) findViewById(R.id.register_email);
        EditText registerPasswordView = (EditText) findViewById(R.id.register_password);

        Button registerUserActionButton = (Button) findViewById(R.id.register_btn);
        Button backToLoginActionButton = (Button) findViewById(R.id.register_back_to_log_in);

        backToLoginActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startLoginActivityIntent = new Intent(getApplicationContext(), LoginActivity.class);

                startActivity(startLoginActivityIntent);
            }
        });

        registerUserActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(
                        0, registerFirstNameView.getText().toString(),
                        registerLastNameView.getText().toString(),
                        registerEmailView.getText().toString(),
                        registerPasswordView.getText().toString()
                );

                User inserted = userService.registerUser(user);

                if (inserted == null) {
                    Toast.makeText(getApplicationContext(), "Could not register user", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent loginActivityIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginActivityIntent);
            }
        });
    }
}