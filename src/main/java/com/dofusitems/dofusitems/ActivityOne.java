package com.dofusitems.dofusitems;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityOne extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private String url = "https://raw.githubusercontent.com/Aissoquatre/Easy-data--Datas/master/json/";
    private ProgressDialog progressDialog;
    private List<Item> itemsList = new ArrayList<>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        String idName = getIntent().getStringExtra("idName");
        url = url + idName + ".json";

        listView = (ListView) findViewById(R.id.list);
        setSingleEvent(listView);
        adapter = new CustomListAdapter(this, itemsList);
        listView.setAdapter(adapter);


        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Chargement...");
        progressDialog.show();

        JsonArrayRequest itemReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d(TAG, response.toString());
                    hidePDialog();

                    for (int i = 0; i < response.length(); i++) {
                        try {

                            JSONObject jsonObject = response.getJSONObject(i);

                            Item item = new Item();
                            item.setName(jsonObject.getString("name"));
                            item.setImgItem(jsonObject.getString("imgPath"));

                            if(!jsonObject.getString("set").equals("[]")) {
                                JSONObject set = jsonObject.getJSONObject("set");
                                if(!set.equals("[]")){
                                    item.setSetName(set.getString("setName"));
                                }else {
                                    item.setSetName("Sans panoplie");
                                }
                            }else {
                                item.setSetName("Sans panoplie");
                            }

                            item.setType("Type : " + jsonObject.getString("type"));
                            item.setLevel(jsonObject.getString("level"));

                            if(!jsonObject.getString("condition").equals("[]")) {
                                item.setCondition("Condition : " + jsonObject.getString("condition"));
                            }else {
                                item.setCondition("Condition : Sans condition");
                            }

                            item.setStats(jsonObject.getString("stats").replace("[", "")
                                    .replace("\",", "\n").replace("\"", "")
                                    .replace("]" , ""));
                            item.setDescription(jsonObject.getString("description"));

                            itemsList.add(item);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    adapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            VolleyLog.d(TAG, "Error: " + error.getMessage());
            hidePDialog();
            }
        });
        AppController.getInstance().addToRequestQueue(itemReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    private void setSingleEvent(ListView listView) {
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ActivityDetail.class);
                intent.putExtra("ITEM", (Item) parent.getItemAtPosition(position));
                startActivity(intent);
            }
        });
    }
}
