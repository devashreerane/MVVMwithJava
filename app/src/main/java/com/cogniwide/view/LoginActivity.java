package com.cogniwide.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.cogniwide.R;


public class LoginActivity extends AppCompatActivity {
    
    private EditText emailInput,passwordInput;
    private Button btn_submit;
    
    private String str_email,str_pass;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.edt_email);
        passwordInput = findViewById(R.id.edt_password);
        btn_submit = findViewById(R.id.btn_submit);
        
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });
        
    }

    private void validation() {


        if (emailInput.getText() == null) {
            emailInput.setVisibility(View.VISIBLE);
            emailInput.setError(getResources().getString(R.string.email_mobile_empty));
        } else if (emailInput.getText().toString().trim().length() == 0) {
            emailInput.setVisibility(View.VISIBLE);
            emailInput.setError(getResources().getString(R.string.email_mobile_empty));
        } else if (!TextUtils.isDigitsOnly(emailInput.getText()) && !Patterns.EMAIL_ADDRESS.matcher(emailInput.getText()).matches()) {
            emailInput.setVisibility(View.VISIBLE);
            emailInput.setError(getResources().getString(R.string.email_not_valid));
        }
        else if (passwordInput.getText() == null) {
            passwordInput.setVisibility(View.VISIBLE);
            passwordInput.setError(getResources().getString(R.string.password_empty));
        } else if (passwordInput.getText().toString().trim().length() == 0) {
            passwordInput.setVisibility(View.VISIBLE);
            passwordInput.setError(getResources().getString(R.string.password_empty));
        } else if (passwordInput.getText().toString().trim().length() < 6) {
            passwordInput.setVisibility(View.VISIBLE);
            passwordInput.setError(getResources().getString(R.string.password_not_valid));
        }
        else
        {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }


}