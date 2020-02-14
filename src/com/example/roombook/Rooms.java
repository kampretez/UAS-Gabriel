package com.example.roombook;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Rooms extends Activity {
	
    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
 
    // URL to get contacts JSON
    private static String url = "http://apilearninghotel.totopeto.com/rooms";
 
    ArrayList<HashMap<String, String>> roomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roomslayout);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#25ab31")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#25ab31")));
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        setTitle("Daftar Kamar");
        
        roomList = new ArrayList<HashMap<String, String>>();
        
        lv = (ListView) findViewById(R.id.list);
 
        new GetRooms().execute();
        
        
        lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//Toast.makeText(MainActivity.this, "Tested!", Toast.LENGTH_SHORT).show();
				HashMap<String, String> hm = roomList.get(arg2);
				
				Intent intent = new Intent(Rooms.this, RoomDetail.class);
				
				intent.putExtra("id", hm.get("id"));
				intent.putExtra("room_name", hm.get("room_name"));
				intent.putExtra("capacity", hm.get("capacity"));
				intent.putExtra("description", hm.get("description"));				
				
				startActivity(intent);
				
				//Toast.makeText(MainActivity.this, hm.get("email"), Toast.LENGTH_SHORT).show();
				
			}
		});
    }


    private class GetRooms extends AsyncTask<Void, Void, Void> {
    	 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Rooms.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
 
        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
 
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
 
            Log.e(TAG, "Response from url: " + jsonStr);
 
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
 
                    // Getting JSON Array node
                    JSONArray rooms = jsonObj.getJSONArray("rooms");
 
                    // looping through All Contacts
                    for (int i = 0; i < rooms.length(); i++) {
                        JSONObject c = rooms.getJSONObject(i);
 
                        String Id = c.getString("id");
                        String roomName = c.getString("room_name");
                        String capacity = c.getString("capacity");
                        String deskripsi = c.getString("description");                        
 
                        // Phone node is JSON Object
                        //JSONObject phone = c.getJSONObject("phone");
                        //String mobile = phone.getString("mobile");
                        //String home = phone.getString("home");
                        //String office = phone.getString("office");
 
                        // tmp hash map for single contact
                        HashMap<String, String> room = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        room.put("id", Id);
                        room.put("room_name", roomName);
                        room.put("capacity", capacity);
                        room.put("description", deskripsi);                        
 
                        // adding contact to contact list
                        roomList.add(room);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
 
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
 
            }
 
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    Rooms.this, roomList,
                    R.layout.list_item, new String[]{"room_name", "capacity",
                    "description"}, new int[]{R.id.namaroom,
                    R.id.kapasitas, R.id.deskripsi});
 
            lv.setAdapter(adapter);
        }
 
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}

