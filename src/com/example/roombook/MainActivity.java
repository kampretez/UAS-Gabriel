package com.example.roombook;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText Password, Username;
    CheckBox Showbox;
    Button alogin;
    private boolean backPressedToExitOnce = false;
    String pass, user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#25ab31")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#25ab31")));
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        Username = (EditText) findViewById(R.id.edusername);
        Password = (EditText) findViewById(R.id.edpassword);
        Showbox = (CheckBox) findViewById(R.id.showpass);
        alogin = (Button) findViewById(R.id.btlogin);        
                                       
        Showbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked) {
                    Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        
        alogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//TODO Auto-generated method stub
				
				user = Username.getText().toString();
		        pass = Password.getText().toString(); 
				
				if(user.equals("Gabriel") && pass.equals("gabrieldaulat")) {	
					
						Intent intent = new Intent(MainActivity.this, Rooms.class);					
						MainActivity.this.finish();					
						startActivity(intent);
						finish();
						
				} else {
					Toast.makeText(MainActivity.this, "Username salah", Toast.LENGTH_SHORT).show();																							
				}
				
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
