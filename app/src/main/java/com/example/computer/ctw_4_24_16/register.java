package com.example.computer.ctw_4_24_16;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final DatabaseHelper db= new DatabaseHelper(this);
        setContentView(R.layout.register);
        Button submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userField = (EditText)findViewById(R.id.userField);
                EditText emailField = (EditText)findViewById(R.id.emailField);
                EditText passField = (EditText)findViewById(R.id.passField);
                String username = userField.getText().toString();
                String email = emailField.getText().toString();
                String password = passField.getText().toString();
                if(username == "" || email =="" || password == "")
                {
                    displayError();

                }
                else
                {
                    User newUser = new User(username,email,password);
                    db.insertUser(newUser);
                    finish();
                }

            }
        });


    }
    public void displayError()
    {
        Toast.makeText(this, "You are misssing required fields!", Toast.LENGTH_LONG).show();
    }
    }

