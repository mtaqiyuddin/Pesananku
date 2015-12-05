package tiga.pesananku;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

/**
 * Created by muhammad on 24/11/2015.
 */
public class ParseJSON {
    public static String[] toko;
    public static final String JSON_ARRAY = "result";
    public static final String KEY_TOKO = "toko";
    private JSONArray users = null;
    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            toko = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                toko[i] = jo.getString(KEY_TOKO);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
