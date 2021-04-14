package com.example.laundryapp.DATA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.laundryapp.FRAGMENT.OrderFragment;
import com.example.laundryapp.POJO.AddressPojo;
import com.example.laundryapp.POJO.CartPojo;

import java.util.ArrayList;

public class WashFoldDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG ="Databasehelper";
    public static final String TABLE_NAME = "Wash_Fold_Data";
    private static final String COLUMN_1 = "ID";
    private static final String COLUMN_2 = "Clth_name";
    private static final String COLUMN_3 = "Clth_per_piece_price";
    private static final String COLUMN_4 = "Clth_total_price";
    private static final String COLUMN_5 = "Clth_quantity";
    private static final String COLUMN_6 = "Clth_catg_name";

    private OrderFragment orderFragment;

    public WashFoldDatabaseHelper(Context context)
    {
        super(context, TABLE_NAME, null,5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_2 + " TEXT,"
                + COLUMN_3 + " TEXT,"
                + COLUMN_4 + " TEXT,"
                + COLUMN_5 + " TEXT,"
                + COLUMN_6 + " TEXT" + ");";
        Log.d(TAG, "Creating table " + createTable);
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Insert Data into database
    public void insertdata(String clth_name, String clth_per_piece_price,
                           String clth_total_price, String clth_quantity,
                           String clth_catg_name)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_2, clth_name);
        contentValues.put(COLUMN_3, clth_per_piece_price);
        contentValues.put(COLUMN_4, clth_total_price);
        contentValues.put(COLUMN_5, clth_quantity);
        contentValues.put(COLUMN_6, clth_catg_name);
//        Log.d(TAG, "insertData: Inserting " + place + " to " + TABLE_NAME);
            sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    //Delete data from database
    public void deletedatawf(String clth_name, String clth_per_piece_price,
                           String clth_total_price, String clth_quantity,
                           String clth_catg_name)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            String query = "DELETE FROM " + TABLE_NAME +" WHERE " + COLUMN_2 + "= '" + clth_name + "'" +
                    " AND " + COLUMN_3 + "= '" + clth_per_piece_price + "'" +
                    " AND " + COLUMN_4 + "= '" + clth_total_price + "'" +
                    " AND " + COLUMN_5 + "= '" + clth_quantity + "'" +
                    " AND " + COLUMN_6 + "= '" + clth_catg_name + "'" ;
            sqLiteDatabase.execSQL(query);
            Log.d(TAG, "deleteItem: " + query);
            Log.d(TAG, "deleteData: Deleted " + clth_name + " from database");

//            int tx = (int) Long.parseLong(orderFragment.totalprice.getText().toString().replace("$",""));
//            long tPricee = tx - Long.parseLong(clth_total_price.replace("$",""));
//            orderFragment.alltotalprice = 0;
//            orderFragment.totalprice.setText("$"+tPricee);

//            long tx = Long.parseLong((((OrderFragment)context).totalprice.getText().toString().trim().replace("$","")));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        sqLiteDatabase.close();
    }

    public void deleteAllData ()
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME);
    }

}


