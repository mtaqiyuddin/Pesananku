package tiga.pesananku;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.app.AlertDialog;
import android.util.Log;
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

import java.util.HashMap;

public class Login extends AppCompatActivity implements View.OnClickListener {
    public static final String USER_NAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    private static final String LOGIN_URL = "http://pesananku.16mb.com/login.php";

    private EditText username, password;
    Button button;
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
                login();
                break;
            case R.id.tLogin:
                Intent intent = new Intent(this, Register.class);
                startActivity(intent);
                break;
        }
    }

    public void login() {
        String Username = username.getText().toString();
        String Password = password.getText().toString();
        finalLogin(Username,Password);
    }

    private void finalLogin (final String Username, final String Password) {
        class LoginAsync extends AsyncTask<String, Void, String> {
            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(Login.this, "Please wait",null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loadingDialog.dismiss();
                if (s.trim().equalsIgnoreCase("success")) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), s.trim(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String> data = new HashMap<>();
                data.put("username",params[0]);
                data.put("password",params[1]);

                RegisterUserClass ruc = new RegisterUserClass();

                String result = ruc.sendPostRequest(LOGIN_URL, data);

                return result;
            }
        }
        LoginAsync la = new LoginAsync();
        la.execute(Username, Password);
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Salah Memasukkan Username atau Password");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

}
