package com.saman.rrc.app;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper {
	 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name = Child Command Variable Manager
    private static final String DATABASE_NAME = "ccvManager";
 
    // Child table name
    private static final String CH_TABLE = "childs"; 
    // Child Table Columns names
    private static final String CH_KEY_ID = "id";
    private static final String CH_KEY_CODE = "code";
    private static final String CH_KEY_NAME = "name";
    
 // Command table name
    private static final String CM_TABLE = "commands"; 
    // Command Table Columns names
    private static final String CM_KEY_ID = "id";
    private static final String CM_KEY_CODE = "code";
    private static final String CM_KEY_CHID = "childid";
    private static final String CM_KEY_NAME = "name";
    
    //Variable Table name
    private static final String VA_TABLE = "variables"; 
    // Variable Table Columns names
    private static final String VA_KEY_ID = "id";
    private static final String VA_KEY_CODE = "code";
    private static final String VA_KEY_CMID = "commandid";
    private static final String VA_KEY_NAME = "name";
    
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
    	
    //--Child table-------------------------------    
        String CREATE_CHILDS_TABLE = "CREATE TABLE " + CH_TABLE + "("
                + CH_KEY_ID + " INTEGER PRIMARY KEY,"
        		+ CH_KEY_CODE + " INTEGER,"
                + CH_KEY_NAME + " TEXT"
                +")";
        db.execSQL(CREATE_CHILDS_TABLE);
        
      //--Command table-------------------------------    
        String CREATE_COMMANDS_TABLE = "CREATE TABLE " + CM_TABLE + "("
                + CM_KEY_ID + " INTEGER PRIMARY KEY,"
        		+ CM_KEY_CODE + " INTEGER,"
        		+ CM_KEY_CHID + " INTEGER,"
                + CM_KEY_NAME + " TEXT"
                +")";
        db.execSQL(CREATE_COMMANDS_TABLE);
        
      //--Variable table-------------------------------    
        String CREATE_VARIABLES_TABLE = "CREATE TABLE " + VA_TABLE + "("
                + VA_KEY_ID + " INTEGER PRIMARY KEY,"
        		+ VA_KEY_CODE + " INTEGER,"
        		+ VA_KEY_CMID + " INTEGER,"
                + VA_KEY_NAME + " TEXT"
                +")";
        db.execSQL(CREATE_VARIABLES_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + CH_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + VA_TABLE);
        
        // Create tables again
        onCreate(db);
    }
    
    // Adding new child
    public void addChild(Child child) {
        SQLiteDatabase db = this.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        values.put(CH_KEY_CODE, child.getCode()); // Child Code
        values.put(CH_KEY_NAME, child.getName()); // Child Name        
     
        // Inserting Row
        db.insert(CH_TABLE, null, values);
        db.close(); // Closing database connection
    }
     
    // Getting single child
    public Child getChild(int id) {
    	SQLiteDatabase db = this.getReadableDatabase();
    	 
        Cursor cursor = db.query(CH_TABLE, new String[] { CH_KEY_ID,
                CH_KEY_CODE, CH_KEY_NAME }, CH_KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        Child child = new Child(Integer.parseInt(cursor.getString(0)),
        		Integer.parseInt(cursor.getString(1)), cursor.getString(2));
        // return child
        return child;
    }
     
    // Getting All childs
    public List<Child> getAllChilds() {
    	
    	List<Child> childList = new ArrayList<Child>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + CH_TABLE;
     
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Child child = new Child();
                child.setID(Integer.parseInt(cursor.getString(0)));
                child.setCode(Integer.parseInt(cursor.getString(1)));
                child.setName(cursor.getString(2));
                // Adding child to list
                childList.add(child);
            } while (cursor.moveToNext());
        }
     
        // return child list
        return childList;
    	
    }
     
    // Getting childs Count
    public int getChildsCount() {
    	String countQuery = "SELECT  * FROM " + CH_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
    
    // Updating single child
    public int updateChild(Child child) {
    	
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(CH_KEY_CODE, child.getCode());
        values.put(CH_KEY_NAME, child.getName());        
     
        // updating row
        return db.update(CH_TABLE, values, CH_KEY_ID + " = ?",
                new String[] { String.valueOf(child.getID()) });
    }
     
    // Deleting single child
    public void deleteChild(Child child) {
    	SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CH_TABLE, CH_KEY_ID + " = ?",
                new String[] { String.valueOf(child.getID()) });
        db.close();
    }
    
    
    // Adding new command
    public void addCommand(Command command) {}
     
    // Getting single command
    public Command getCommand(int id) {}
     
    // Getting All commands
    public List<Command> getAllCommands(int childID) {}
     
    // Getting commands Count
    public int getCommandsCount() {}
    
    // Updating single command
    public int updateCommand(Command command) {}
     
    // Deleting single command
    public void deleteCommand(Command command) {}
}