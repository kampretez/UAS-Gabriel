package com.example.roombook;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PesanKamar extends Activity {
	
	EditText eStart, eEnd;
	Button TombolPesan;
	
	private String TAG = MainActivity.class.getSimpleName();
	private ProgressDialog pDialog;
	private static String url = "http://apilearninghotel.totopeto.com/reservations";
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pesankamar);
		ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#25ab31")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#25ab31")));
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		setTitle("Pemesanan");
		
		eStart = (EditText) findViewById(R.id.etstart);
		eEnd = (EditText) findViewById(R.id.Akhirtgl);
				
		TombolPesan = (Button) findViewById(R.id.btpesan);
		
		TombolPesan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//TODO Auto-generated method stub
				
				//Intent intent = new Intent(AddContacts.this, MainActivity.class);
				
				new pemesananKamar().execute();
				Toast.makeText(PesanKamar.this, "Selamat! Pemesanan anda berhasil", Toast.LENGTH_SHORT).show();
				PesanKamar.this.finish();
				
				//startActivity(intent);
			}
		}); 
	}

	
	private class pemesananKamar extends AsyncTask<Void, Void, Void> {
				
		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			
			final String roomId = getIntent().getStringExtra("id");
			String memberId = "2";
			
			String post_params = null;
			JSONObject param = new JSONObject();
			try {
				param.put("room_id", roomId);
				param.put("member_id", memberId);
				param.put("start_date", eStart.getText().toString());
				param.put("end_date", eEnd.getText().toString());
				
				post_params = param.toString();
			} catch (JSONException e){
				e.printStackTrace();
			}
			HttpHandler InputData = new HttpHandler();
			String jsonstr = InputData.makePostRequest(url, post_params);
			return null;
		}
		
		//protected void onPostExecute(Void result) {
			//super.onPostExecute(result);
			//AddContacts.this.finish();
		//}
		
	}
	
}
