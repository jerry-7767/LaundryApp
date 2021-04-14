package com.example.laundryapp.DATA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.laundryapp.POJO.AddressPojo;

import java.util.ArrayList;

public class AddressDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG ="Databasehelper";
    private static final String TABLE_NAME = "Address_List";
    private static final String COLUMN_1 = "ID";
    private static final String COLUMN_2 = "Place";
    private static final String COLUMN_3 = "Address";

    public AddressDatabaseHelper(Context context)
    {
        super(context, TABLE_NAME, null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_2 + " TEXT,"
                + COLUMN_3 + " TEXT" + ");";
        Log.d(TAG, "Creating table " + createTable);
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Insert Data into database
    public void insertdata(String place, String address)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_2, place);
        contentValues.put(COLUMN_3, address);
//        Log.d(TAG, "insertData: Inserting " + place + " to " + TABLE_NAME);
            sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    //Delete data from database
    public void deletedata(String place, String address)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            String query = "DELETE FROM " + TABLE_NAME +" WHERE " + COLUMN_2 + "= '" + place + "'" +
                    " AND " + COLUMN_3 + "= '" + address + "'" ;
            sqLiteDatabase.execSQL(query);
            Log.d(TAG, "deleteItem: " + query);
            Log.d(TAG, "deleteData: Deleted " + place + " from database");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        sqLiteDatabase.close();
    }

    //fetch all data from database
    public ArrayList<AddressPojo> getAlldata()
    {
        ArrayList<AddressPojo> arrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query ="SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        while (cursor.moveToNext())
        {
            String place = cursor.getString(1);
            String address = cursor.getString(2);
            AddressPojo dataModel = new AddressPojo(place, address);
            arrayList.add(dataModel);
        }
        sqLiteDatabase.close();
        return arrayList;
    }
}
