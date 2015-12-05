package tiga.pesananku;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

public class MenuMakanan extends AppCompatActivity {
    private String[] toko1Array = { "NASI GORENG BIASA", "NASI GORENG TOILET", "SATE", "SOTO",
            "BATAGOR SIOMAY", "MASAKAN JEPANG", "MASAKAN JAWA", "NASI PADANG", "MIE GORENG",
            "NASI GILA", "MIE AYAM", "ROTI BAKAR" };
    private GridView grid;
    private ArrayAdapter arrayAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_makanan);
        grid = (GridView) findViewById(R.id.grid);
        arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, toko1Array);
        grid.setAdapter(arrayAdapter1);
        grid.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i, long l) {
                switch (i) {
                    case 0: showErrorMessage();
                    default: showErrorMessage();
                }
            }
        });
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MenuMakanan.this);
        dialogBuilder.setMessage("BAKAL ADA LIST UNTUK PRODUK SETIAP TOKO");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }
}
