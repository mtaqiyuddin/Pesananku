package tiga.pesananku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.app.AlertDialog;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button button;
    EditText username, password;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = (Button) findViewById(R.id.bLogin);
        username = (EditText) findViewById(R.id.eUsername);
        password = (EditText) findViewById(R.id.ePassword);
        register = (TextView) findViewById(R.id.tLogin);

        button.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bLogin:
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else
                    showErrorMessage();
                break;
            case R.id.tLogin:
                Intent intent = new Intent(this, Register.class);
                startActivity(intent);
                break;
        }
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Salah Memasukkan Username atau Password");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    public void md5() throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(password.getText().toString().getBytes(),0,password.length());
        System.out.println(new BigInteger(1,m.digest()).toString(16));
    }
}
