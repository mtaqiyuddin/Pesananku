package tiga.pesananku;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;




public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String JSON_URL = "http://pesananku.16mb.com/toko.php";
    Button button;
    private ListView listtokoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listtokoView = (ListView) findViewById(R.id.list);
        Spinner listmakananView = (Spinner)findViewById(R.id.spinner);
        Spinner listjumlahView = (Spinner)findViewById(R.id.spinner2);
        button = (Button)findViewById(R.id.bLogout);

        button.setOnClickListener(this);
        sendRequest();
        listtokoView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i, long l) {
                switch (i) {
                    case 0:
                        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
                                .getSystemService(LAYOUT_INFLATER_SERVICE);
                        View popupView = layoutInflater.inflate(R.layout.popup_layout,
                                null);
                        final PopupWindow popupWindow = new PopupWindow(popupView,
                                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                        String[] items = new String[] { "Chai Latte", "Green Tea", "Black Tea" };

                        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        //        android.R.layout.simple_spinner_item, items);
                        //listmakananView.setAdapter(adap);
                        //listjumlahView.setAdapter(cl);
                        Button btnDismiss = (Button) popupView
                                .findViewById(R.id.bPesan);
                        btnDismiss.setOnClickListener(new Button.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                popupWindow.dismiss();
                            }
                        });
                        popupWindow.showAsDropDown(button, 0, 0);
                        /**
                        Intent intent = new Intent(MainActivity.this, MenuMakanan.class);
                        startActivity(intent);
                         **/
                        break;
                    default:
                        showErrorMessage();
                }
            }
        });
    }

    private void sendRequest(){
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void  showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        CustomList cl = new CustomList(this, ParseJSON.toko);
        listtokoView.setAdapter(cl);
    }


    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bLogout:
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        dialogBuilder.setMessage("BAKAL ADA LIST UNTUK PRODUK SETIAP TOKO");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }
}

