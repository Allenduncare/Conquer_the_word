package com.example.computer.ctw_4_24_16;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class login extends Activity{
    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button register =(Button)findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        Button login = (Button)findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            List<User> users = db.getAllUsers();
                EditText userField = (EditText)findViewById(R.id.userField);
                EditText passwordField = (EditText)findViewById(R.id.passwordField);
                String userName = userField.getText().toString();
                String password = passwordField.getText().toString();
                if(userName == ""||password == "")
                {
                    displayMessage("Missing field.");
                }
                else
                {
                    for(int i = 0; i < users.size();i++)
                    {
                        if(users.get(i).getName().equals(userName) && users.get(i).getpassword().equals(password))
                        {
                            displayMessage("Welcome "+ userName.toString() +"!");
                            SharedPreferences sp=getSharedPreferences("Login", 0);
                            SharedPreferences.Editor Ed=sp.edit();
                            Ed.putString("Username",userName );
                            Ed.putString("Password",password);
                            Ed.commit();
                            finish();
                            return;
                        }
                    }
                    displayMessage("Incorrect username or password");
                }
            }
        });

    }
    public void registerUser()
    {
        Intent i = new Intent(this, register.class);
        startActivity(i);
    }
    public void displayMessage(String str)
    {

        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
}

