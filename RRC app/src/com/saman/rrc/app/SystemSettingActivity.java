package com.saman.rrc.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class SystemSettingActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_setting);
        
    }
    
    public void changeSettings(View view){
    	EditText passTxt = (EditText) findViewById(R.id.editText2);
    	EditText codeTxt = (EditText) findViewById(R.id.editText1);
    	DatabaseHandler db = new DatabaseHandler(this);
    	
    	String pass = passTxt.getText().toString();
    	String tel = codeTxt.getText().toString();
    	//int tel = Integer.parseInt(codet);
    	
    	db.changeSysSettings(new SysSetting(tel, pass));
    	passTxt.setText("");
    	codeTxt.setText("");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_system_setting, menu);
        return true;
    }
}
