package com.example.roombook;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RoomDetail extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailroom);
		ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#25ab31")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#25ab31")));
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		setTitle("Detail Kamar");
		
		TextView Kamar = (TextView)findViewById(R.id.tkamar);
		TextView Harga = (TextView)findViewById(R.id.tharga);
		TextView Kapasitas = (TextView)findViewById(R.id.tkapasitas);
		Button bpesan = (Button)findViewById(R.id.btpesan);
		
		Kamar.setText(getIntent().getStringExtra("room_name"));
		Harga.setText("Rp." + getIntent().getStringExtra("description"));
		Kapasitas.setText("Maksimal " + getIntent().getStringExtra("capacity") + " orang");
		
		final String Id = getIntent().getStringExtra("id");
		
		bpesan.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//TODO Auto-generated method stub
					
					Intent intent = new Intent(RoomDetail.this, PesanKamar.class);
														
					intent.putExtra("id", Id);
					
					startActivity(intent);
				}
			});
		
		
	}

}

