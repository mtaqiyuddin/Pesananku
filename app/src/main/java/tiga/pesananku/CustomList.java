package tiga.pesananku;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by muhammad on 24/11/2015.
 */
public class CustomList extends ArrayAdapter<String> {
    private  String[] toko;
    private Activity context;

    public CustomList(Activity context, String[] toko){
        super(context,R.layout.list_toko, toko);
        this.context = context;
        this.toko = toko;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_toko, null, true);
        TextView textViewToko = (TextView) listViewItem.findViewById(R.id.namaToko);
        textViewToko.setText(toko[position]);
        return listViewItem;
    }
}