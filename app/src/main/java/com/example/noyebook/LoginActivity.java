package com.example.noyebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etPassword;
    private CheckBox cbIsRememberPass;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        //setListener();
        sharedPreferences = getSharedPreferences("rememberpassword", Context.MODE_PRIVATE);
        boolean isRemember = sharedPreferences.getBoolean("rememberpassword", false);
        if (isRemember) {
            String name = sharedPreferences.getString("name", "");
            String password = sharedPreferences.getString("password", "");
            etName.setText(name);
            etPassword.setText(password);
            cbIsRememberPass.setChecked(true);
        }
    }

    public void initViews() {
        etName = (EditText) findViewById(R.id.etName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        cbIsRememberPass = (CheckBox) findViewById(R.id.remember);
    }

    public void login(View view) {
        String name = etName.getText().toString();
        String password = etPassword.getText().toString();
        if ("hlh".equals(name) && "123456".equals(password)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (cbIsRememberPass.isChecked()) {       //记住密码
                editor.putBoolean("rememberpassword", true);
                editor.putString("name", name);
                editor.putString("password", password);
            } else {
                editor.clear();
            }
            editor.commit();

            Intent intent=new Intent();
            intent.setClass(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this,"账号或密码错误",Toast.LENGTH_LONG).show();
        }

    }
}