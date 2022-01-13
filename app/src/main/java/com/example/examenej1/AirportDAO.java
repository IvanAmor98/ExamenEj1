package com.example.examenej1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class AirportDAO extends SQLiteOpenHelper {

    private Context context;

    public AirportDAO(Context context) {
        super(context, "AirportsDB", null, 1);
        this.context = context;
    }

    private void checkTables(SQLiteDatabase db) {
        try {
            db.execSQL("DROP TABLE IF EXISTS Monumentos;");
            db.execSQL("CREATE TABLE IF NOT EXISTS Monumentos(" +
                    "idMonument INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR," +
                    "type INTEGER," +
                    "address VARCHAR," +
                    "phone INTEGER," +
                    "url VARCHAR," +
                    "comment VARCHAR," +
                    "image BLOB," +
                    "imageFullSize VARCHAR);");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Airports(" +
                "idAirport INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR," +
                "latitude INTEGER," +
                "longitude INTEGER," +
                "shield VARCHAR);");
    }

    public boolean saveAirport(AirportDTO airportDTO) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            //checkTables(db);
            ContentValues values = new ContentValues();

            values.put("name", airportDTO.getName());
            values.put("latitude", airportDTO.getPosition()[0]);
            values.put("longitude", airportDTO.getPosition()[1]);
            values.put("shield", airportDTO.getShield().getAbsolutePath());

            db.insert("Airports", null, values);
            return true;
        } catch (Exception ex) {
            Toast.makeText(context, "ERROR: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean deleteAirport(int id) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            db.delete("Airports", "idAirport = ?", new String[]{String.valueOf(id)});
            return true;
        } catch (Exception ex) {
            Toast.makeText(context, "ERROR: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public ArrayList<AirportDTO> listAirports() {
        try (SQLiteDatabase db = this.getReadableDatabase()) {

            ArrayList<AirportDTO> airportList = new ArrayList<>();

            Cursor cursor = db.query("Airports",
                    null,    //columns to return
                    null,        //columns for the WHERE clause
                    null,        //The values for the WHERE clause
                    null,       //group the rows
                    null,       //filter by row groups
                    null);

            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("idAirport"));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    long latitude = cursor.getLong(cursor.getColumnIndexOrThrow("latitude"));
                    long longitude = cursor.getLong(cursor.getColumnIndexOrThrow("longitude"));
                    String shield = cursor.getString(cursor.getColumnIndexOrThrow("shield"));

                    airportList.add(new AirportDTO(id, name, new long[]{latitude, longitude}, new File(shield)));
                } while (cursor.moveToNext());
            }
            return airportList;
        } catch (Exception ex) {
            Log.e("AirportDAO", "ERROR: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
