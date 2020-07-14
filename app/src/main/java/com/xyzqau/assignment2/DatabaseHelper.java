package com.xyzqau.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "Data_db";
    private static final String TABLE_NAME="Data_tab";
    private static final String NAME="Name";
    private static final String USER_NAME="User_Name";
    private static final String PASSWORD="Password";
    private static final String AGE="Age";
    private static final String HOBBIES="Data_tab";
    private static final String GENDER="Gander";
    private static final String DATE_OF_BIRTH="Date_of_birth";
    private static final String IMAGE="Image";
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + NAME + " TEXT,"
                    + USER_NAME + " TEXT PRIMARY KEY,"
                    + PASSWORD + " TEXT,"
                    + AGE + " TEXT,"
                    + HOBBIES + " TEXT,"
                    + GENDER + " TEXT,"
                    + DATE_OF_BIRTH + " TEXT,"
                    + IMAGE + " BLOB"
                    + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void insert(String name, String user_name, String password, String age, String hobbies, String gender,
                       String date_of_birth, Bitmap photo) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(NAME,name);
        cv.put(USER_NAME,user_name);
        cv.put(PASSWORD,password);
        cv.put(AGE,age);
        cv.put(HOBBIES,hobbies);
        cv.put(GENDER,gender);
        cv.put(DATE_OF_BIRTH,date_of_birth);
        cv.put(IMAGE,getBytes(photo));
    }
    public static Bitmap getImage(byte[] image){
        return BitmapFactory.decodeByteArray(image,0,image.length);
    }
    public static byte[] getBytes(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);
        return stream.toByteArray();
    }

    public boolean CheckLogin(String lg_name, String lg_password) {
        SQLiteDatabase db=this.getWritableDatabase();
        boolean flag=false;
        Cursor cursor=db.query(TABLE_NAME,new String[]{USER_NAME,PASSWORD},USER_NAME + "=? AND "+ PASSWORD + "+?",
                new String[]{lg_name,lg_password},null,null,null,null);
        if(cursor != null && cursor.moveToFirst())
            flag=true;
        cursor.close();
        return flag;
    }
    public List<Users> UsersData() {
        List<Users> list_User = new ArrayList<>();
        String User_Name;
        String User_UserName;
        String User_password;
        String User_age;
        String User_hobbies;
        String User_DOB;
        String User_gender;
        Bitmap User_photo;
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToNext()) {
            do {

                User_Name = cursor.getString(cursor.getColumnIndex(NAME));
                User_UserName = cursor.getString(cursor.getColumnIndex(USER_NAME));
                User_password = cursor.getString(cursor.getColumnIndex(PASSWORD));
                User_age = cursor.getString(cursor.getColumnIndex(AGE));
                User_hobbies = cursor.getString(cursor.getColumnIndex(HOBBIES));
                User_DOB = cursor.getString(cursor.getColumnIndex(DATE_OF_BIRTH));
                User_gender = cursor.getString(cursor.getColumnIndex(GENDER));
                User_photo = BitmapFactory.decodeByteArray(cursor.getBlob(cursor.getColumnIndex(IMAGE)),
                        0, cursor.getBlob(cursor.getColumnIndex(IMAGE)).length);
                list_User.add(new Users(User_Name,User_UserName, User_password, User_age, User_hobbies, User_DOB, User_gender, User_photo));
            } while (cursor.moveToNext());
        }
        db.close();
        return list_User;
    }
}
