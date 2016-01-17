package com.example.ali.panawachat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etUsername, etPassword;
    Button btLogin;
    TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Parse.initialize(this, "dEsMAdP0wMRdXuqX1J0JLcRE9FB44B2xMrX2Ot1j", "xBU5LSt9nFEwstEqZ7g9HEaDURzmKch1qUy2pmPD");
        ParseUser currentUser = ParseUser.getCurrentUser();

        txtRegister = (TextView) findViewById(R.id.txtRegister);

        txtRegister.setOnClickListener(this);

        if (currentUser != null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtRegister:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
