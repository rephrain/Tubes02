package com.example.tubes2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pertemuan.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tbl_dokter";
    private static final String FIELD_ID_PERTEMUAN = "id";
    private static final String FIELD_JUDUL = "judul";
    private static final String FIELD_TANGGAL_PERTEMUAN = "tanggalPertemuan";
    private static final String FIELD_WAKTU_PERTEMUAN = "waktuPertemuan";
    private static final String FIELD_PARTISIPAN = "partisipan";
    private static final String FIELD_DESKRIPSI = "deskripsi";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                FIELD_ID_PERTEMUAN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_JUDUL + " TEXT, " +
                FIELD_TANGGAL_PERTEMUAN + " TEXT, " +
                FIELD_WAKTU_PERTEMUAN + " TEXT, " +
                FIELD_PARTISIPAN+ " TEXT, " +
                FIELD_DESKRIPSI + " TEXT); " ;
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);
    }

    public long insertPertemuan(String judul, String tanggalPertemuan, String waktuPertemuan, String partisipan, String deskripsi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_JUDUL, judul);
        cv.put(FIELD_TANGGAL_PERTEMUAN, tanggalPertemuan);
        cv.put(FIELD_WAKTU_PERTEMUAN, waktuPertemuan);
        cv.put(FIELD_PARTISIPAN, partisipan);
        cv.put(FIELD_DESKRIPSI, deskripsi);

        long eksekusi = db.insert(TABLE_NAME, null, cv);

        return eksekusi;
    }

//    public long editPertemuan(String id, String judul, String tanggalPertemuan, String waktuPertemuan, String partisipan, String deskripsi){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put(FIELD_JUDUL, judul);
//        cv.put(FIELD_TANGGAL_PERTEMUAN, tanggalPertemuan);
//        cv.put(FIELD_WAKTU_PERTEMUAN, waktuPertemuan);
//        cv.put(FIELD_PARTISIPAN, partisipan);
//        cv.put(FIELD_DESKRIPSI, deskripsi);
//
//        long eksekusi = db.update(TABLE_NAME, cv, "id = ?", new String[]{id});
//
//        return eksekusi;
//    }
//
//    public long deletePertemuan(Integer id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//        long eksekusi = db.delete(TABLE_NAME, "id = ?", new String[]{Integer.toString(id)});
//
//        return eksekusi;
//    }

    public Cursor lihatDataPertemuan(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }
}
