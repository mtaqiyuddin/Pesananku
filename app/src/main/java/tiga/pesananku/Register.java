package tiga.pesananku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.HashMap;


public class Register extends AppCompatActivity implements View.OnClickListener {
    Button button;
    private EditText username,password,npm,nama;
    private static final String REGISTER_URL = "http://pesananku.16mb.com/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        npm = (EditText)findViewById(R.id.eNPM);
        nama = (EditText)findViewById(R.id.eNama);
        username = (EditText)findViewById(R.id.eUsername2);
        password = (EditText)findViewById(R.id.ePassword2);
        button = (Button)findViewById(R.id.bRegister);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bRegister:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String NPM2 = npm.getText().toString().trim().toLowerCase();
        String Nama2 = nama.getText().toString().trim().toLowerCase();
        String Username2 = username.getText().toString().trim().toLowerCase();
        String Password2 = password.getText().toString().trim().toLowerCase();

        register(NPM2, Nama2, Username2, Password2);
    }

    private void register(final String NPM2, final String Nama2, final String Username2, final String Password2) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc2 = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Register.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if (s.trim().equalsIgnoreCase("success")) {
                    Intent intent2 = new Intent(Register.this, Login.class);
                    finish();
                    startActivity(intent2);
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String, String>();
                data.put("npm",params[0]);
                data.put("nama",params[1]);
                data.put("username",params[2]);
                data.put("password",params[3]);

                String result = ruc2.sendPostRequest(REGISTER_URL,data);

                return  result;
            }
        }

        RegisterUser ru2 = new RegisterUser();
        ru2.execute(NPM2, Nama2, Username2, Password2);
    }
}
