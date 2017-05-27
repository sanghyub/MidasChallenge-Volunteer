package com.example.sangh.midasparactice.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.sangh.midasparactice.AppLog;
import com.example.sangh.midasparactice.Model.Donation;
import com.example.sangh.midasparactice.Model.UserInfo;
import com.example.sangh.midasparactice.Model.Volunteer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by bgh29 on 2017-05-27.
 */

public class DbAdapter {
    public static final String DATABASE_NAME = "appDb.db";

    public static final String VOLUNTEER_TABLE = "volunteerTable";
    public static final String DONATION_TABLE = "donationTable";
    public static final String USER_TABLE = "userTable";

    public static final String VOLUNTEER_FOLDER = "/volunteerImg/";
    public static final String DONATION_FOLDER = "/donationImg/";
    public static final String USER_FOLDER = "/userImg/";

    public static final String KEY_NUMBER = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_POINT = "point";
    public static final String KEY_JOIN_POINT = "jpoint";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_HISTORY = "history";
    public static final String KEY_START_DATE = "sdate";
    public static final String KEY_END_DATE = "edate";

    public static final String[] VOLUNTEER_COL = {KEY_NUMBER, KEY_TITLE, KEY_IMAGE, KEY_POINT, KEY_JOIN_POINT, KEY_CONTENT, KEY_START_DATE, KEY_END_DATE};
    public static final String[] DONATION_COL = {KEY_NUMBER, KEY_TITLE, KEY_POINT, KEY_JOIN_POINT, KEY_CONTENT, KEY_HISTORY};
    public static final String[] USER_COL = {KEY_NUMBER, KEY_NAME, KEY_POINT};

    public static final int FIND_BY_NUM = 0;

    public static final int FIND_BY_VOLUNTEER_TITLE = 1;
    public static final int FIND_BY_VOLUNTEER_IMAGE = 2;
    public static final int FIND_BY_VOLUNTEER_POINT = 3;
    public static final int FIND_BY_VOLUNTEER_JOIN_POINT = 4;
    public static final int FIND_BY_VOLUNTEER_CONTENT = 5;
    public static final int FIND_BY_VOLUNTEER_START_DATE = 6;
    public static final int FIND_BY_VOLUNTEER_END_DATE = 7;

    public static final int FIND_BY_DONATION_TITLE = 1;
    public static final int FIND_BY_DONATION_POINT = 2;
    public static final int FIND_BY_DONATION_JOIN_POINT = 3;
    public static final int FIND_BY_DONATION_CONTENT = 4;
    public static final int FIND_BY_DONATION_HISTORY = 5;

    public static final int FIND_BY_USER_NAME = 1;
    public static final int FIND_BY_USER_POINT = 2;

    private static final String TAG = "DbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb; // �����ͺ��̽��� ����

    private static final String VOLUNTEER_TABLE_CREATE = "create table "+VOLUNTEER_TABLE+"("+KEY_NUMBER+" integer primary key autoincrement,"+KEY_TITLE+" text not null,"+KEY_IMAGE+" text not null,"
            +KEY_POINT+" integer not null,"+KEY_JOIN_POINT+" integer not null,"+KEY_CONTENT+" text not null,"+KEY_START_DATE+" text not null,"+KEY_END_DATE+" text not null);";

    private static final String DONATION_TABLE_CREATE = "create table "+DONATION_TABLE+"("+KEY_NUMBER+" integer primary key autoincrement,"+KEY_TITLE+" text not null,"
            +KEY_POINT+" integer not null,"+KEY_JOIN_POINT+" integer not null,"+KEY_CONTENT+" text not null,"+KEY_HISTORY+" text not null);";

    private static final String USER_TABLE_CREATE = "create table "+USER_TABLE+"("+KEY_NUMBER+" integer primary key autoincrement,"+KEY_NAME+" text not null,"
            +KEY_POINT+" integer not null);";

    private static final int DATABASE_VERSION = 1;

    private final Context mCtx; // ?

    private static long lastVId, lastDId, lastUId;
    private static final SimpleDateFormat dbDataFormat = new SimpleDateFormat("yyyy/MM/dd");

    private static DbAdapter mInstance = null;

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(VOLUNTEER_TABLE_CREATE);
            db.execSQL(DONATION_TABLE_CREATE);
            db.execSQL(USER_TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

    }

    public DbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public static DbAdapter getInstance(Context ctx) {
        if(mInstance==null) {
            mInstance = new DbAdapter(ctx);
        }
        return mInstance;
    }
    public static DbAdapter getInstance() {
        return mInstance;
    }

    public DbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public String saveBitmaptoPng(Bitmap image, String folder, String name){
        Boolean isSave = false;
        String folderDir = mCtx.getFilesDir().getAbsolutePath()+folder;
        String file_name = name+".png";
        int minImageSize = 118;


        int scale = 1;
        if( image.getHeight() > minImageSize || image.getWidth() > minImageSize ) {
            scale = (int)Math.pow(  2,  (int)Math.round( Math.log( minImageSize / (double)Math.max( image.getHeight(), image.getWidth() ) ) / Math.log( 0.5 ) ) );
        }
        Bitmap newImage = Bitmap.createScaledBitmap(image, image.getWidth()/scale, image.getHeight()/scale, true);
        File file_path;
        try{
            file_path = new File(folderDir);
            if(!file_path.isDirectory()){
                file_path.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(folderDir+file_name);

            newImage.compress(Bitmap.CompressFormat.PNG, 8, out);
            out.flush();
            out.close();

            isSave = true;
        }catch(FileNotFoundException exception){
            AppLog.e("FileNotFoundException", exception.getMessage());
        }catch(IOException exception){
            AppLog.e("IOException", exception.getMessage());
        }
        AppLog.i("Img", folderDir+file_name);
        if(isSave) return folderDir+file_name;
        else return null;
    }

    public Date StringToDate(String stringDate) {
        Date sDate=new Date(System.currentTimeMillis());
        try {
            sDate = dbDataFormat.parse(stringDate);
        }
        catch (ParseException ex) {
            System.out.println(ex.toString());
        }
        return sDate;

    }
    public String DateToString(Date stringDate) {
        return dbDataFormat.format(stringDate);
    }

    public long createVolunteer(String title, Bitmap image, int point, String content, Date startDate, Date endDate) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);

        lastVId = getLastID(VOLUNTEER_TABLE, VOLUNTEER_COL);
        String imagePath = saveBitmaptoPng(image, VOLUNTEER_FOLDER, Long.toString(lastVId));
        if(imagePath!=null){
            initialValues.put(KEY_IMAGE, imagePath);
        }
        initialValues.put(KEY_POINT, point);
        initialValues.put(KEY_JOIN_POINT, 0);
        initialValues.put(KEY_CONTENT, content);
        initialValues.put(KEY_START_DATE, DateToString(startDate));
        initialValues.put(KEY_END_DATE, DateToString(endDate));

        return mDb.insert(VOLUNTEER_TABLE, null, initialValues);
    }

    public boolean deleteVolunteer(long num) {
        return mDb.delete(VOLUNTEER_TABLE, KEY_NUMBER + "=" + "'" + Long.toString(num) + "'",
                null) > 0;
    }

    public long getLastID(String table, String[] columns){
        Cursor all = fetchAllRows(table, columns);
        return all.getCount();
    }

    public Cursor fetchAllRows(String table, String[] columns) {
        return mDb.query(table, columns, null, null, null, null, null);
    }

    public Cursor fetchRow(String table, String[] columns, long num) throws SQLException {
        Cursor mCursor = mDb.query(table, columns, KEY_NUMBER + "=" + "'" + Long.toString(num) + "'", null, null, null, null, null);
        if (mCursor != null)
            mCursor.moveToFirst();
        return mCursor;
    }
    public long getNumber(Cursor cur) {
        return cur.getLong(FIND_BY_NUM);
    }

    private String getVolunteerTitle(Cursor cur) {
        return cur.getString(FIND_BY_VOLUNTEER_TITLE);
    }
    public String getVolunteerTitle(long num) {
        return getVolunteerTitle(fetchRow(VOLUNTEER_TABLE, VOLUNTEER_COL, num));
    }

    private Bitmap getVolunteerImage(Cursor cur) {
        Bitmap image = BitmapFactory.decodeFile(cur.getString(FIND_BY_VOLUNTEER_IMAGE));
        return image;
    }
    public Bitmap getVolunteerImage(long num) {
        return getVolunteerImage(fetchRow(VOLUNTEER_TABLE, VOLUNTEER_COL, num));
    }

    private int getVolunteerPoint(Cursor cur) {
        return cur.getInt(FIND_BY_VOLUNTEER_POINT);
    }
    public int getVolunteerPoint(long num) {
        return getVolunteerPoint(fetchRow(VOLUNTEER_TABLE, VOLUNTEER_COL, num));
    }

    private String getVolunteerContent(Cursor cur) {
        return cur.getString(FIND_BY_VOLUNTEER_CONTENT);
    }
    public String getVolunteerContent(long num) {
        return getVolunteerContent(fetchRow(VOLUNTEER_TABLE, VOLUNTEER_COL, num));
    }

    private boolean getVolunteerJoin(Cursor cur) {
        return cur.getInt(FIND_BY_VOLUNTEER_JOIN_POINT)!=0;
    }
    public boolean getVolunteerJoin(long num) {
        return getVolunteerJoin(fetchRow(VOLUNTEER_TABLE, VOLUNTEER_COL, num));
    }

    public boolean changeVolunteerJoin(long num) {
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_JOIN_POINT, 1);
        return mDb.update(VOLUNTEER_TABLE, newValues, KEY_NUMBER + "="+"'" + Long.toString(num) + "'", null) > 0;
    }

    private Date getVolunteerStartDate(Cursor cur) {
        return StringToDate(cur.getString(FIND_BY_VOLUNTEER_START_DATE));
    }
    public Date getVolunteerStartDate(long num) {
        return getVolunteerStartDate(fetchRow(VOLUNTEER_TABLE, VOLUNTEER_COL, num));
    }

    private Date getVolunteerEndDate(Cursor cur) {
        return StringToDate(cur.getString(FIND_BY_VOLUNTEER_END_DATE));
    }
    public Date getVolunteerEndDate(long num) {
        return getVolunteerStartDate(fetchRow(VOLUNTEER_TABLE, VOLUNTEER_COL, num));
    }

    private Volunteer getVolunteer(Cursor cur) {
        Volunteer newVol = new Volunteer();
        newVol.setNumber(getNumber(cur));
        newVol.setTitle(getVolunteerTitle(cur));
        newVol.setImg(getVolunteerImage(cur));
        newVol.setPoint(getVolunteerPoint(cur));
        newVol.setJoin(getVolunteerJoin(cur));
        newVol.setContents(getVolunteerContent(cur));
        newVol.setStartDate(getVolunteerStartDate(cur));
        newVol.setEndDate(getVolunteerEndDate(cur));
        return newVol;
    }
    public Volunteer getVolunteer(long num){
        return getVolunteer(fetchRow(VOLUNTEER_TABLE, VOLUNTEER_COL, num));
    }

    public ArrayList<Volunteer> getVolunteerList(){
        int count;
        Cursor cursorNow = fetchAllRows(VOLUNTEER_TABLE, VOLUNTEER_COL);
        count = cursorNow.getCount();
        ArrayList<Volunteer> volunteerList = new ArrayList<>();
        cursorNow.moveToFirst();
        for (int i = 0; i < count; i++) {
            volunteerList.add(getVolunteer(cursorNow));
            cursorNow.moveToNext();
        }

        return volunteerList;
    }
    public long createDonation(String title, long point, long totalPoint, String content, String history) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_JOIN_POINT, point);
        initialValues.put(KEY_POINT, totalPoint);
        initialValues.put(KEY_CONTENT, content);
        initialValues.put(KEY_HISTORY, history);

        return mDb.insert(DONATION_TABLE, null, initialValues);
    }


    public boolean addDonationPoint(long num, int point){
        if(!addUserPoint(1, -1*point)) return false;

        ContentValues newValues = new ContentValues();
        newValues.put(KEY_JOIN_POINT, getDonationJoinPoint(num)+point);
        newValues.put(KEY_POINT, getDonationPoint(num)+point);

        return mDb.update(DONATION_TABLE, newValues, KEY_NUMBER + "="+"'" + Long.toString(num) + "'", null) > 0;
    }

    public boolean deleteDonation(long num) {
        return mDb.delete(DONATION_TABLE, KEY_NUMBER + "=" + "'" + Long.toString(num) + "'",
                null) > 0;
    }

    private String getDonationTitle(Cursor cur) {
        return cur.getString(FIND_BY_DONATION_TITLE);
    }
    public String getDonationTitle(long num) {
        return getVolunteerTitle(fetchRow(DONATION_TABLE, DONATION_COL, num));
    }

    private int getDonationPoint(Cursor cur) {
        return cur.getInt(FIND_BY_DONATION_POINT);
    }
    public int getDonationPoint(long num) {
        return getDonationPoint(fetchRow(DONATION_TABLE, DONATION_COL, num));
    }

    private int getDonationJoinPoint(Cursor cur) {
        return cur.getInt(FIND_BY_DONATION_JOIN_POINT);
    }
    public int getDonationJoinPoint(long num) {
        return getDonationJoinPoint(fetchRow(DONATION_TABLE, DONATION_COL, num));
    }

    private String getDonationContent(Cursor cur) {
        return cur.getString(FIND_BY_DONATION_CONTENT);
    }
    public String getDonationContent(long num) {
        return getDonationContent(fetchRow(DONATION_TABLE, DONATION_COL, num));
    }

    private String getDonationHistory(Cursor cur) {
        return cur.getString(FIND_BY_DONATION_HISTORY);
    }
    public String getDonationHistory(long num) {
        return getDonationHistory(fetchRow(DONATION_TABLE, DONATION_COL, num));
    }

    private Donation getDonation(Cursor cur) {
        Donation newDon = new Donation();
        newDon.setNumber(getNumber(cur));
        newDon.setTitle(getDonationTitle(cur));
        newDon.setPoint(getDonationJoinPoint(cur));
        newDon.setTotalPoint(getDonationPoint(cur));
        newDon.setContents(getDonationContent(cur));
        newDon.setDonationHistory(getDonationHistory(cur));
        return newDon;
    }
    public Donation Donation(long num){
        return getDonation(fetchRow(DONATION_TABLE, DONATION_COL, num));
    }

    public ArrayList<Donation> getDonationList(){
        int count;
        Cursor cursorNow = fetchAllRows(DONATION_TABLE, DONATION_COL);
        count = cursorNow.getCount();
        ArrayList<Donation> donationList = new ArrayList<>();
        cursorNow.moveToFirst();
        for (int i = 0; i < count; i++) {
            donationList.add(getDonation(cursorNow));
            cursorNow.moveToNext();
        }

        return donationList;
    }

    public long createUserInfo(String name, int point) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);

        initialValues.put(KEY_POINT, point);

        return mDb.insert(USER_TABLE, null, initialValues);
    }

    public boolean deleteUserInfo(long num) {
        return mDb.delete(DONATION_TABLE, KEY_NUMBER + "=" + "'" + Long.toString(num) + "'",
                null) > 0;
    }

    private String getUserName(Cursor cur) {
        return cur.getString(FIND_BY_USER_NAME);
    }
    public String getUserName(long num) {
        return getUserName(fetchRow(USER_TABLE, USER_COL, num));
    }

    private int getUserPoint(Cursor cur) {
        return cur.getInt(FIND_BY_USER_POINT);
    }
    public int getUserPoint(long num) {
        return getUserPoint(fetchRow(USER_TABLE, USER_COL, num));
    }

    public boolean addUserPoint(long num, int newPoint){
        ContentValues newValues = new ContentValues();
        int oriPoint = getUserPoint(num);
        if(oriPoint+newPoint<0) return false;
        newValues.put(KEY_POINT, oriPoint+newPoint);
        return mDb.update(USER_TABLE, newValues, KEY_NUMBER + "="+"'" + Long.toString(num) + "'", null) > 0;
    }

    public UserInfo getUserInfo(Cursor cur) {
        UserInfo newUser= new UserInfo();
        newUser.setNumber(getNumber(cur));
        newUser.setPoint(getUserPoint(cur));
        return newUser;
    }
}
