package com.example.ali.panawachat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ali.panawachat.Model.UserData;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    TextView txtLogin;
    EditText etUsername, etEmail, etPassword, etRepassword;
    Button btRegister;
    ProgressBar pBar;

    UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        pBar = (ProgressBar) findViewById(R.id.pBar);
        txtLogin = (TextView) findViewById(R.id.txtLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etRepassword = (EditText) findViewById(R.id.etREPassword);
        btRegister = (Button) findViewById(R.id.btRegister);

        btRegister.setOnClickListener(this);
        txtLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btRegister:
                //validator
                if (etUsername.getText().toString().length() == 0){
                    etUsername.setError("Username can't be blank !");
                }
                else if (etUsername.getText().toString().length() < 2){
                    etUsername.setError("Username too short !");
                }
                else if (!etEmail.getText().toString().trim().contains("@") || !etEmail.getText().toString().trim().contains(".com")){
                    etEmail.setError("Email format should be valid !");
                }
                else if (etPassword.getText().toString().length() == 0){
                    etPassword.setError("Password can't be blank !");
                }
                else if (etPassword.getText().toString().length() < 8){
                    etPassword.setError("Password should be 8 character or more !");
                }
                else if (!Objects.equals(etPassword.getText().toString(), etRepassword.getText().toString())){
                    etRepassword.setError("Didn't match with your password !");
                }
                else {
                    pBar.setVisibility(View.VISIBLE);
                    userData = new UserData();
                    userData.setUsername(etUsername.getText().toString());
                    userData.setEmail(etEmail.getText().toString());
                    userData.setPassword(etPassword.getText().toString());

                    etUsername.setEnabled(false);
                    etEmail.setEnabled(false);
                    etPassword.setEnabled(false);
                    etRepassword.setEnabled(false);
                    btRegister.setEnabled(false);

                    Register();

//                    Toast.makeText(getApplicationContext(), userData.getUsername()+" "+ userData.getEmail() +" "+ userData.getPassword(), Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    public void Register(){
        Parse.initialize(this, "dEsMAdP0wMRdXuqX1J0JLcRE9FB44B2xMrX2Ot1j", "xBU5LSt9nFEwstEqZ7g9HEaDURzmKch1qUy2pmPD");

        final ParseUser user = new ParseUser();

        user.setUsername(userData.getUsername());
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Toast.makeText(getApplicationContext(), "Congratz ! You're now registred on Panawa Chat !", Toast.LENGTH_SHORT).show();
                    ParseUser.logInInBackground(userData.getUsername(), userData.getPassword(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null){
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                intent.putExtra("username", userData.getUsername());
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Failed to login ! "+e.getMessage()+" !", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "Failed to register ! "+e.getMessage()+" !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
