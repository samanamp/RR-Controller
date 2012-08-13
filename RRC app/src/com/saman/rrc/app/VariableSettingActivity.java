package com.saman.rrc.app;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class VariableSettingActivity extends Activity {
	List<Command> commands;
	List<Variable> variables;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variable_setting);
        spinMake();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_variable_setting, menu);
        return true;
    }
    
    public void newone(View view){
    	//Spinner spcom = (Spinner) findViewById(R.id.spinner1);
    	Spinner spcm = (Spinner) findViewById(R.id.spinner2);
    	EditText nameTxt = (EditText) findViewById(R.id.editText1);
    	EditText codeTxt = (EditText) findViewById(R.id.editText2);
    	DatabaseHandler db = new DatabaseHandler(this);
    	
    	String name = nameTxt.getText().toString();
    	String codet = codeTxt.getText().toString();
    	
    	if( name.length()>1 && codet.length()==1 && spcm.getSelectedItemPosition()>0){
    		int code = Integer.parseInt(codet);
    	
    	
    		Variable tempcm = new Variable( 1, code, commands.get(spcm.getSelectedItemPosition()-1).getID(), name);    		
    		db.addVariable(tempcm);
    	
    		spinMake();
    		nameTxt.setText("");
    		codeTxt.setText("");
    	
    		Toast.makeText(getApplicationContext(), "متغیر جدید ذخیره شد", 10).show();
    	}else{
    		Toast.makeText(getApplicationContext(), "فیلدها را کامل انتخاب نمایید. کد می تواند یک رقمی باشد!", 10).show();
    	}
    }
    
    public void edit(View view){

    	Spinner spvar = (Spinner) findViewById(R.id.spinner1);
    	Spinner spcm = (Spinner) findViewById(R.id.spinner2);
    	EditText nameTxt = (EditText) findViewById(R.id.editText1);
    	EditText codeTxt = (EditText) findViewById(R.id.editText2);
    	DatabaseHandler db = new DatabaseHandler(this);
    	
    	String name = nameTxt.getText().toString();
    	String codet = codeTxt.getText().toString();
    	int code = Integer.parseInt(codet);
    	
    	if(spvar.getSelectedItemPosition() != -1)
    	if(spcm.getSelectedItemPosition() != -1 || spcm.getSelectedItemPosition() != 0){
    		Variable tempcm = variables.get(spvar.getSelectedItemPosition()-1);
    		tempcm.setCode(code);
    		tempcm.setName(name);
    		tempcm.setCommandID(commands.get(spcm.getSelectedItemPosition()-1).getID());    				    		
    		db.updateVariable(tempcm);
    	}
    	spinMake();
    	nameTxt.setText("");
    	codeTxt.setText("");
    }
    
    public void del(View view){
    	Spinner sp = (Spinner) findViewById(R.id.spinner1);
    	DatabaseHandler db = new DatabaseHandler(this);
    	
    	if(sp.getSelectedItemPosition() != -1){
    		Variable tempcm = variables.get(sp.getSelectedItemPosition()-1);
    		db.deleteVariable(tempcm);    		
    	}
    	spinMake();
    }
    
    
    
    private void spinMake(){
    	DatabaseHandler db = new DatabaseHandler(this);
    	Spinner spvar = (Spinner) findViewById(R.id.spinner1);
    	Spinner spcom = (Spinner) findViewById(R.id.spinner2);
    	
    	variables = db.getAllVariables();
    	ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
        
        spinnerArrayAdapter.add("--یک متقیر را انتخاب نمایید---");
        for (Variable cn : variables) {
        	Command tempcom = db.getCommand(cn.getCommandID());
        	String txt = "Name :" + cn.getName() + " |Code :" + cn.getCode()+ " |COM.:" + tempcom.getName();
            spinnerArrayAdapter.add(txt);            
            }
        
        spvar.setAdapter(spinnerArrayAdapter);
        
        //---command spinner make---------
        commands = db.getAllCommands();
    	ArrayAdapter<String> spinnerArrayAdaptercom = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item);
        spinnerArrayAdaptercom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
        
        spinnerArrayAdaptercom.add("--یک فرمان را انتخاب نمایید---");
        for (Command cn : commands) {
        	Child tempch = db.getChild(cn.getChildID());
            String txt = "Name :" + cn.getName() + " |Code :" + cn.getCode()+ " |Child :" + tempch.getName();
            spinnerArrayAdaptercom.add(txt);            
            }
        
        spcom.setAdapter(spinnerArrayAdaptercom);
        
    }
    
}
