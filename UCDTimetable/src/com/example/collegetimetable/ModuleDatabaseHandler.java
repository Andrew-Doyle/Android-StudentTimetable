package com.example.collegetimetable;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ModuleDatabaseHandler {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_MODULE_CODE = "module_code";
	public static final String KEY_MODULE_NAME = "module_name";
	public static final String KEY_LECTURE_PRACTICAL = "lecture_or_practical";
	public static final String KEY_LECTURE_PRACTICAL_SHORT = "lecture_or_practical_short";
	public static final String KEY_LECTURE_DAY = "day_of_week";
	public static final String KEY_LECTURE_DAY_SHORT = "day_of_week_short";
	public static final String KEY_START_TIME = "start_time";
	public static final String KEY_END_TIME = "endtime";
	public static final String KEY_LOCATION = "location";
	public static final String ADDITIONAL_INFO = "additional_info";

	private static final String DATABASE_NAME = "Modulesdb";
	private static final String DATABASE_MODULES = "moduleTable";
	private static final int DATABASE_VERSION = 1;

	private ModulesDbHelper instanceModulesHelper;
	private final Context ourContext; // /
	private SQLiteDatabase ourDatabase;
	private String[] results;
	private String[] results2;

	private static class ModulesDbHelper extends SQLiteOpenHelper {

		public ModulesDbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_MODULES + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_MODULE_CODE
					+ " TEXT NOT NULL, " + KEY_MODULE_NAME + " TEXT NOT NULL, "
					+ KEY_LECTURE_PRACTICAL + " TEXT NOT NULL, "
					+ KEY_LECTURE_PRACTICAL_SHORT + " TEXT NOT NULL, "
					+ KEY_LECTURE_DAY + " TEXT NOT NULL, "
					+ KEY_LECTURE_DAY_SHORT + " TEXT NOT NULL, "
					+ KEY_START_TIME + " TEXT NOT NULL, " + KEY_END_TIME
					+ " TEXT, " + KEY_LOCATION + " TEXT NOT NULL, "
					+ ADDITIONAL_INFO + " TEXT NOT NULL);"

			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP_TABLE_IF_EXISTS " + DATABASE_MODULES);
			onCreate(db);
		}

	}

//	public ModuleDatabaseHandler(AddModules addModules) {
//		// TODO Auto-generated constructor stub
//		ourContext = (Context) addModules;
//	}

	// public ModuleDatabaseHandler(OnClickListener onClickListener) {
	// // TODO Auto-generated constructor stub
	// ourContext = (Context) onClickListener;
	// }

//	public ModuleDatabaseHandler(ModuleDetails moduleDetails) {
//		// TODO Auto-generated constructor stub
//		ourContext = (Context) moduleDetails;
//	}

//	public ModuleDatabaseHandler(ViewModules viewModules) {
//		ourContext = (Context) viewModules;
//	}

//	 public ModuleDatabaseHandler(VivzAdapter vivzAdapter) {
//	 // TODO Auto-generated constructor stub
//	 ourContext = (Context) vivzAdapter;
//	 }
	
	//########################################### NEW ######################################################## //

	public ModuleDatabaseHandler(Context baseContext) {
		// TODO Auto-generated constructor stub
		ourContext = (Context) baseContext;
	}

	
	

	public ModuleDatabaseHandler open() throws SQLException {
		instanceModulesHelper = new ModulesDbHelper(ourContext);
		ourDatabase = instanceModulesHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		instanceModulesHelper.close();
	}

	public long createEntry(String modCode, String modName,
			String modLectOrPract, String modLectOrPractShort, String modDay,
			String modDayShort, String modStart, String modEnd,
			String modLocation, String modAddInfo) {

		ContentValues cv = new ContentValues();
		cv.put(KEY_MODULE_CODE, modCode);
		cv.put(KEY_MODULE_NAME, modName);
		cv.put(KEY_LECTURE_PRACTICAL, modLectOrPract);
		cv.put(KEY_LECTURE_PRACTICAL_SHORT, modLectOrPractShort);
		cv.put(KEY_LECTURE_DAY, modDay);
		cv.put(KEY_LECTURE_DAY_SHORT, modDayShort);
		cv.put(KEY_START_TIME, modStart);
		cv.put(KEY_END_TIME, modEnd);
		cv.put(KEY_LOCATION, modLocation);
		cv.put(ADDITIONAL_INFO, modAddInfo);

		return ourDatabase.insert(DATABASE_MODULES, null, cv);
	}

	public ArrayList<String> getData() {
		// TODO Auto-generated method stub

		String[] columns = new String[] { KEY_ROWID, KEY_MODULE_CODE,
				KEY_MODULE_NAME, KEY_LECTURE_PRACTICAL,
				KEY_LECTURE_PRACTICAL_SHORT, KEY_LECTURE_DAY,
				KEY_LECTURE_DAY_SHORT, KEY_START_TIME, KEY_END_TIME,
				KEY_LOCATION, ADDITIONAL_INFO };
		Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
				null, null, null);
		ArrayList<String> results = new ArrayList<String>();

		int indexModCode = c.getColumnIndex(KEY_MODULE_CODE);

		int indexLectPracShort = c.getColumnIndex(KEY_LECTURE_PRACTICAL_SHORT);

		int indexLectDayShort = c.getColumnIndex(KEY_LECTURE_DAY_SHORT);
		int indexLectStart = c.getColumnIndex(KEY_START_TIME);

		int indexLectLoc = c.getColumnIndex(KEY_LOCATION);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			results.add(c.getString(indexModCode) + " "
					+ c.getString(indexLectPracShort) + " "
					+ c.getString(indexLectDayShort) + " "
					+ c.getString(indexLectStart) + " "
					+ c.getString(indexLectLoc));
		}

		return results;
	}

	public ArrayList<String> getDescriptionData() {
		// TODO Auto-generated method stub

		String[] columns = new String[] { KEY_ROWID, KEY_MODULE_CODE,
				KEY_MODULE_NAME, KEY_LECTURE_PRACTICAL,
				KEY_LECTURE_PRACTICAL_SHORT, KEY_LECTURE_DAY,
				KEY_LECTURE_DAY_SHORT, KEY_START_TIME, KEY_END_TIME,
				KEY_LOCATION, ADDITIONAL_INFO };
		Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
				null, null, null);
		ArrayList<String> results = new ArrayList<String>();

		int indexModCode = c.getColumnIndex(KEY_MODULE_CODE);

		int indexLectPracShort = c.getColumnIndex(KEY_LECTURE_PRACTICAL_SHORT);

		int indexLectDayShort = c.getColumnIndex(KEY_LECTURE_DAY_SHORT);
		int indexLectStart = c.getColumnIndex(KEY_START_TIME);

		int indexLectLoc = c.getColumnIndex(KEY_LOCATION);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			results.add(c.getString(indexLectPracShort) + " "
					+ c.getString(indexLectDayShort) + " "
					+ c.getString(indexLectStart) + " "
					+ c.getString(indexLectLoc));
		}

		return results;
	}
	
	public ArrayList<String> getDay() {
		// TODO Auto-generated method stub

		String[] columns = new String[] { KEY_ROWID, KEY_MODULE_CODE,
				KEY_MODULE_NAME, KEY_LECTURE_PRACTICAL,
				KEY_LECTURE_PRACTICAL_SHORT, KEY_LECTURE_DAY,
				KEY_LECTURE_DAY_SHORT, KEY_START_TIME, KEY_END_TIME,
				KEY_LOCATION, ADDITIONAL_INFO };
		Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
				null, null, null);
		ArrayList<String> results = new ArrayList<String>();

		int indexLectDay = c.getColumnIndex(KEY_LECTURE_DAY);

		int indexLectPracShort = c.getColumnIndex(KEY_LECTURE_PRACTICAL_SHORT);

		int indexLectDayShort = c.getColumnIndex(KEY_LECTURE_DAY_SHORT);
		int indexLectStart = c.getColumnIndex(KEY_START_TIME);

		int indexLectLoc = c.getColumnIndex(KEY_LOCATION);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			results.add(c.getString(indexLectDay));
		}

		return results;
	}
	public ArrayList<String> getIDs() {
		// TODO Auto-generated method stub

		String[] columns = new String[] { KEY_ROWID, KEY_MODULE_CODE,
				KEY_MODULE_NAME, KEY_LECTURE_PRACTICAL,
				KEY_LECTURE_PRACTICAL_SHORT, KEY_LECTURE_DAY,
				KEY_LECTURE_DAY_SHORT, KEY_START_TIME, KEY_END_TIME,
				KEY_LOCATION, ADDITIONAL_INFO };
		Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
				null, null, null);
		ArrayList<String> results = new ArrayList<String>();
		int indexRow = c.getColumnIndex(KEY_ROWID);
		int indexModCode = c.getColumnIndex(KEY_MODULE_CODE);

		int indexLectPracShort = c.getColumnIndex(KEY_LECTURE_PRACTICAL_SHORT);

		int indexLectDayShort = c.getColumnIndex(KEY_LECTURE_DAY_SHORT);
		int indexLectStart = c.getColumnIndex(KEY_START_TIME);

		int indexLectLoc = c.getColumnIndex(KEY_LOCATION);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			results.add(c.getString(indexRow));
		}

		return results;
	}
	

	public ArrayList<String> getTitleData() {
		// TODO Auto-generated method stub

		String[] columns = new String[] { KEY_ROWID, KEY_MODULE_CODE,
				KEY_MODULE_NAME, KEY_LECTURE_PRACTICAL,
				KEY_LECTURE_PRACTICAL_SHORT, KEY_LECTURE_DAY,
				KEY_LECTURE_DAY_SHORT, KEY_START_TIME, KEY_END_TIME,
				KEY_LOCATION, ADDITIONAL_INFO };
		Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
				null, null, null);
		ArrayList<String> results = new ArrayList<String>();

		int indexModCode = c.getColumnIndex(KEY_MODULE_CODE);

		int indexLectPracShort = c.getColumnIndex(KEY_LECTURE_PRACTICAL_SHORT);

		int indexLectDayShort = c.getColumnIndex(KEY_LECTURE_DAY_SHORT);
		int indexLectStart = c.getColumnIndex(KEY_START_TIME);

		int indexLectLoc = c.getColumnIndex(KEY_LOCATION);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			results.add(c.getString(indexModCode));
		}

		return results;
	}
	
//	public ModuleDatabaseHandler(TimetableWidget widget){
//		ourContext = (Context) widget;
//	}
	
	public ArrayList<String> getTitleWidget() {
		// TODO Auto-generated method stub

		String[] columns = new String[] { KEY_ROWID, KEY_MODULE_CODE,
				KEY_MODULE_NAME, KEY_LECTURE_PRACTICAL,
				KEY_LECTURE_PRACTICAL_SHORT, KEY_LECTURE_DAY,
				KEY_LECTURE_DAY_SHORT, KEY_START_TIME, KEY_END_TIME,
				KEY_LOCATION, ADDITIONAL_INFO };
		Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
				null, null, null);
		ArrayList<String> results = new ArrayList<String>();

		int indexModCode = c.getColumnIndex(KEY_MODULE_CODE);
		int indexModName = c.getColumnIndex(KEY_MODULE_NAME);

		int indexLectPracShort = c.getColumnIndex(KEY_LECTURE_PRACTICAL_SHORT);

		int indexLectDayShort = c.getColumnIndex(KEY_LECTURE_DAY_SHORT);
		int indexLectStart = c.getColumnIndex(KEY_START_TIME);

		int indexLectLoc = c.getColumnIndex(KEY_LOCATION);
		
		

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			results.add(c.getString(indexModCode) + " "
					+ c.getString(indexModName));
		}

		return results;
	}
	
	public ArrayList<String> getDescriptionWidget() {
		// TODO Auto-generated method stub

		String[] columns = new String[] { KEY_ROWID, KEY_MODULE_CODE,
				KEY_MODULE_NAME, KEY_LECTURE_PRACTICAL,
				KEY_LECTURE_PRACTICAL_SHORT, KEY_LECTURE_DAY,
				KEY_LECTURE_DAY_SHORT, KEY_START_TIME, KEY_END_TIME,
				KEY_LOCATION, ADDITIONAL_INFO };
		Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
				null, null, null);
		ArrayList<String> results = new ArrayList<String>();

		int indexModCode = c.getColumnIndex(KEY_MODULE_CODE);
		int indexModName = c.getColumnIndex(KEY_MODULE_NAME);

		int indexLectPracShort = c.getColumnIndex(KEY_LECTURE_PRACTICAL_SHORT);

		int indexLectDayShort = c.getColumnIndex(KEY_LECTURE_DAY_SHORT);
		int indexLectStart = c.getColumnIndex(KEY_START_TIME);
		int indexLectEnd = c.getColumnIndex(KEY_END_TIME);

		int indexLectLoc = c.getColumnIndex(KEY_LOCATION);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			results.add(c.getString(indexLectStart) + " - "
					+ c.getString(indexLectEnd)+ ": " + c.getString(indexLectLoc) );
		}

		return results;
	}

// public String[] getDescriptionData() {
// // TODO Auto-generated method stub
//
// String[] columns = new String[] { KEY_ROWID, KEY_MODULE_CODE,
// KEY_MODULE_NAME, KEY_LECTURE_PRACTICAL,
// KEY_LECTURE_PRACTICAL_SHORT, KEY_LECTURE_DAY,
// KEY_LECTURE_DAY_SHORT, KEY_START_TIME, KEY_END_TIME,
// KEY_LOCATION, ADDITIONAL_INFO };
// Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
// null, null, null);
// results = null;
//
// int indexModCode = c.getColumnIndex(KEY_MODULE_CODE);
//
// int indexLectPracShort = c.getColumnIndex(KEY_LECTURE_PRACTICAL_SHORT);
//
// int indexLectDayShort = c.getColumnIndex(KEY_LECTURE_DAY_SHORT);
// int indexLectStart = c.getColumnIndex(KEY_START_TIME);
//
// int indexLectLoc = c.getColumnIndex(KEY_LOCATION);
// int i;
//
// for (c.moveToFirst(),i=0; !c.isAfterLast(); c.moveToNext(),i++) {
// results[i] = c.getString(indexLectPracShort) + " "
// + c.getString(indexLectDayShort) + " "
// + c.getString(indexLectStart) + " "
// + c.getString(indexLectLoc);
// }
//
// return results;
// }

// public String[] getTitleData() {
// // TODO Auto-generated method stub
//
// String[] columns = new String[] { KEY_ROWID, KEY_MODULE_CODE,
// KEY_MODULE_NAME, KEY_LECTURE_PRACTICAL,
// KEY_LECTURE_PRACTICAL_SHORT, KEY_LECTURE_DAY,
// KEY_LECTURE_DAY_SHORT, KEY_START_TIME, KEY_END_TIME,
// KEY_LOCATION, ADDITIONAL_INFO };
// Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
// null, null, null);
// results2 = null;
//
// int indexModCode = c.getColumnIndex(KEY_MODULE_CODE);
//
// int i;
//
// for (c.moveToFirst(),i=0; !c.isAfterLast(); c.moveToNext(),i++) {
// results2[i] = c.getString(indexModCode);
// }
//
// return results2;
// }

//	public String getAllData(int specified_position) {
//		// TODO Auto-generated method stub
//		int position = specified_position;
//
//		// String[] columns = new String[]{ KEY_ROWID, KEY_MODULE_CODE,
//		// KEY_MODULE_NAME, KEY_LECTURE_PRACTICAL, KEY_LECTURE_DAY,
//		// KEY_START_TIME,KEY_END_TIME, KEY_LOCATION, ADDITIONAL_INFO};
//		// Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
//		// null, null, null);
//
//		String[] columns = new String[] { KEY_MODULE_CODE, KEY_MODULE_NAME,
//				KEY_LECTURE_PRACTICAL, KEY_LECTURE_DAY, KEY_START_TIME,
//				KEY_END_TIME, KEY_LOCATION, ADDITIONAL_INFO };
//		Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
//				null, null, null);
//
//		// String result = "";
//		//
//		// int indexRow = c.getColumnIndex(KEY_ROWID);
//		// int indexModCode = c.getColumnIndex(KEY_MODULE_CODE);
//		// int indexModName = c.getColumnIndex(KEY_MODULE_NAME);
//		// int indexLectPrac = c.getColumnIndex(KEY_LECTURE_PRACTICAL);
//		//
//		//
//		// int indexLectDay = c.getColumnIndex(KEY_LECTURE_DAY);
//		//
//		// int indexLectStart = c.getColumnIndex(KEY_START_TIME);
//		// int indexLectEnd = c.getColumnIndex(KEY_END_TIME);
//		// int indexLectLoc = c.getColumnIndex(KEY_LOCATION);
//		// int indexLectInfo = c.getColumnIndex(ADDITIONAL_INFO);
//		//
//		//
//		// // for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
//		// if (c.moveToPosition(position)) {
//		// result = c.getString(indexModCode) + " " + c.getString(indexModName)
//		// + " " + c.getString(indexLectPrac)
//		// + " " + c.getString(indexLectDay) + " " + c.getString(indexLectStart)
//		// + " " + c.getString(indexLectEnd) + " "
//		// + c.getString(indexLectLoc) + " " + c.getString(indexLectInfo);
//		// return result;
//		// }
//		// else
//		// {
//		// throw new IllegalArgumentException("Row " + specified_position +
//		// " does not exist");
//		// }
//		//
//		if (c.moveToPosition(specified_position)) {
//			String result = "";
//			for (int columnIndex = 0; columnIndex < c.getColumnCount(); columnIndex++) {
//				result += c.getString(columnIndex) + " ";
//			}
//			return result;
//		} else {
//			throw new IllegalArgumentException("Row " + specified_position
//					+ " does not exist");
//		}
//
//	}

	public String getModCode(long l) {
		// TODO Auto-generated method stub
		int position = (int) l;

		String[] columns = new String[] { KEY_MODULE_CODE, KEY_MODULE_NAME,
				KEY_LECTURE_PRACTICAL, KEY_LECTURE_DAY, KEY_START_TIME,
				KEY_END_TIME, KEY_LOCATION, ADDITIONAL_INFO };
		Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
				null, null, null);

		String result = "";

		int indexRow = c.getColumnIndex(KEY_ROWID);
		int indexModCode = c.getColumnIndex(KEY_MODULE_CODE);
		int indexModName = c.getColumnIndex(KEY_MODULE_NAME);
		int indexLectPrac = c.getColumnIndex(KEY_LECTURE_PRACTICAL);

		int indexLectDay = c.getColumnIndex(KEY_LECTURE_DAY);

		int indexLectStart = c.getColumnIndex(KEY_START_TIME);
		int indexLectEnd = c.getColumnIndex(KEY_END_TIME);
		int indexLectLoc = c.getColumnIndex(KEY_LOCATION);
		int indexLectInfo = c.getColumnIndex(ADDITIONAL_INFO);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			if (c.moveToPosition(position)) {
				result = result + c.getString(indexModCode);
				
			}
		}
		return result;
	}
	
	public String getModName(int specified_position) {
		// TODO Auto-generated method stub
		int position = specified_position;

    	String[] columns = new String[] { KEY_MODULE_CODE, KEY_MODULE_NAME,
				KEY_LECTURE_PRACTICAL, KEY_LECTURE_DAY, KEY_START_TIME,
				KEY_END_TIME, KEY_LOCATION, ADDITIONAL_INFO };
		Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
				null, null, null);

		String result = "";

		int indexRow = c.getColumnIndex(KEY_ROWID);
		int indexModCode = c.getColumnIndex(KEY_MODULE_CODE);
		int indexModName = c.getColumnIndex(KEY_MODULE_NAME);
		int indexLectPrac = c.getColumnIndex(KEY_LECTURE_PRACTICAL);

		int indexLectDay = c.getColumnIndex(KEY_LECTURE_DAY);

		int indexLectStart = c.getColumnIndex(KEY_START_TIME);
		int indexLectEnd = c.getColumnIndex(KEY_END_TIME);
		int indexLectLoc = c.getColumnIndex(KEY_LOCATION);
		int indexLectInfo = c.getColumnIndex(ADDITIONAL_INFO);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			if (c.moveToPosition(position)) {
				result = result + c.getString(indexModName);
				
			}
		}
		return result;
	}
	
	public String getLectPrac(int specified_position) {
		// TODO Auto-generated method stub
		int position = specified_position;

    	String[] columns = new String[] { KEY_MODULE_CODE, KEY_MODULE_NAME,
				KEY_LECTURE_PRACTICAL, KEY_LECTURE_DAY, KEY_START_TIME,
				KEY_END_TIME, KEY_LOCATION, ADDITIONAL_INFO };
		Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
				null, null, null);

		String result = "";

		int indexRow = c.getColumnIndex(KEY_ROWID);
		int indexModCode = c.getColumnIndex(KEY_MODULE_CODE);
		int indexModName = c.getColumnIndex(KEY_MODULE_NAME);
		int indexLectPrac = c.getColumnIndex(KEY_LECTURE_PRACTICAL);

		int indexLectDay = c.getColumnIndex(KEY_LECTURE_DAY);

		int indexLectStart = c.getColumnIndex(KEY_START_TIME);
		int indexLectEnd = c.getColumnIndex(KEY_END_TIME);
		int indexLectLoc = c.getColumnIndex(KEY_LOCATION);
		int indexLectInfo = c.getColumnIndex(ADDITIONAL_INFO);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			if (c.moveToPosition(position)) {
				result = result + c.getString(indexLectPrac);
				
			}
		}
		return result;
	}
	
	public String getLectDay(int specified_position) {
		// TODO Auto-generated method stub
		int position = specified_position;

    	String[] columns = new String[] { KEY_MODULE_CODE, KEY_MODULE_NAME,
				KEY_LECTURE_PRACTICAL, KEY_LECTURE_DAY, KEY_START_TIME,
				KEY_END_TIME, KEY_LOCATION, ADDITIONAL_INFO };
		Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
				null, null, null);

		String result = "";

		int indexRow = c.getColumnIndex(KEY_ROWID);
		int indexModCode = c.getColumnIndex(KEY_MODULE_CODE);
		int indexModName = c.getColumnIndex(KEY_MODULE_NAME);
		int indexLectPrac = c.getColumnIndex(KEY_LECTURE_PRACTICAL);

		int indexLectDay = c.getColumnIndex(KEY_LECTURE_DAY);

		int indexLectStart = c.getColumnIndex(KEY_START_TIME);
		int indexLectEnd = c.getColumnIndex(KEY_END_TIME);
		int indexLectLoc = c.getColumnIndex(KEY_LOCATION);
		int indexLectInfo = c.getColumnIndex(ADDITIONAL_INFO);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			if (c.moveToPosition(position)) {
				result = result + c.getString(indexLectDay);
				
			}
		}
		return result;
	}
	
	public String getLectStart(int specified_position) {
		// TODO Auto-generated method stub
		int position = specified_position;

    	String[] columns = new String[] { KEY_MODULE_CODE, KEY_MODULE_NAME,
				KEY_LECTURE_PRACTICAL, KEY_LECTURE_DAY, KEY_START_TIME,
				KEY_END_TIME, KEY_LOCATION, ADDITIONAL_INFO };
		Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
				null, null, null);

		String result = "";

		int indexRow = c.getColumnIndex(KEY_ROWID);
		int indexModCode = c.getColumnIndex(KEY_MODULE_CODE);
		int indexModName = c.getColumnIndex(KEY_MODULE_NAME);
		int indexLectPrac = c.getColumnIndex(KEY_LECTURE_PRACTICAL);

		int indexLectDay = c.getColumnIndex(KEY_LECTURE_DAY);

		int indexLectStart = c.getColumnIndex(KEY_START_TIME);
		int indexLectEnd = c.getColumnIndex(KEY_END_TIME);
		int indexLectLoc = c.getColumnIndex(KEY_LOCATION);
		int indexLectInfo = c.getColumnIndex(ADDITIONAL_INFO);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			if (c.moveToPosition(position)) {
				result = result + c.getString(indexLectStart);
				
			}
		}
		return result;
	}
	
	public String getLectEnd(int specified_position) {
		// TODO Auto-generated method stub
		int position = specified_position;

    	String[] columns = new String[] { KEY_MODULE_CODE, KEY_MODULE_NAME,
				KEY_LECTURE_PRACTICAL, KEY_LECTURE_DAY, KEY_START_TIME,
				KEY_END_TIME, KEY_LOCATION, ADDITIONAL_INFO };
		Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
				null, null, null);

		String result = "";

		int indexRow = c.getColumnIndex(KEY_ROWID);
		int indexModCode = c.getColumnIndex(KEY_MODULE_CODE);
		int indexModName = c.getColumnIndex(KEY_MODULE_NAME);
		int indexLectPrac = c.getColumnIndex(KEY_LECTURE_PRACTICAL);

		int indexLectDay = c.getColumnIndex(KEY_LECTURE_DAY);

		int indexLectStart = c.getColumnIndex(KEY_START_TIME);
		int indexLectEnd = c.getColumnIndex(KEY_END_TIME);
		int indexLectLoc = c.getColumnIndex(KEY_LOCATION);
		int indexLectInfo = c.getColumnIndex(ADDITIONAL_INFO);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			if (c.moveToPosition(position)) {
				result = result + c.getString(indexLectEnd);
				
			}
		}
		return result;
	}
	
	public String getLectLoc(int specified_position) {
		// TODO Auto-generated method stub
		int position = specified_position;

    	String[] columns = new String[] { KEY_MODULE_CODE, KEY_MODULE_NAME,
				KEY_LECTURE_PRACTICAL, KEY_LECTURE_DAY, KEY_START_TIME,
				KEY_END_TIME, KEY_LOCATION, ADDITIONAL_INFO };
		Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
				null, null, null);

		String result = "";

		int indexRow = c.getColumnIndex(KEY_ROWID);
		int indexModCode = c.getColumnIndex(KEY_MODULE_CODE);
		int indexModName = c.getColumnIndex(KEY_MODULE_NAME);
		int indexLectPrac = c.getColumnIndex(KEY_LECTURE_PRACTICAL);

		int indexLectDay = c.getColumnIndex(KEY_LECTURE_DAY);

		int indexLectStart = c.getColumnIndex(KEY_START_TIME);
		int indexLectEnd = c.getColumnIndex(KEY_END_TIME);
		int indexLectLoc = c.getColumnIndex(KEY_LOCATION);
		int indexLectInfo = c.getColumnIndex(ADDITIONAL_INFO);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			if (c.moveToPosition(position)) {
				result = result + c.getString(indexLectLoc);
				
			}
		}
		return result;
	}
	
	public String getLectInfo(int specified_position) {
		// TODO Auto-generated method stub
		int position = specified_position;

    	String[] columns = new String[] { KEY_MODULE_CODE, KEY_MODULE_NAME,
				KEY_LECTURE_PRACTICAL, KEY_LECTURE_DAY, KEY_START_TIME,
				KEY_END_TIME, KEY_LOCATION, ADDITIONAL_INFO };
		Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
				null, null, null);

		String result = "";

		int indexRow = c.getColumnIndex(KEY_ROWID);
		int indexModCode = c.getColumnIndex(KEY_MODULE_CODE);
		int indexModName = c.getColumnIndex(KEY_MODULE_NAME);
		int indexLectPrac = c.getColumnIndex(KEY_LECTURE_PRACTICAL);

		int indexLectDay = c.getColumnIndex(KEY_LECTURE_DAY);

		int indexLectStart = c.getColumnIndex(KEY_START_TIME);
		int indexLectEnd = c.getColumnIndex(KEY_END_TIME);
		int indexLectLoc = c.getColumnIndex(KEY_LOCATION);
		int indexLectInfo = c.getColumnIndex(ADDITIONAL_INFO);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			if (c.moveToPosition(position)) {
				result = result + c.getString(indexLectInfo);
				
			}
		}
		return result;
	}

	public void deleteEntry(long rowid) {

		ourDatabase.delete(DATABASE_MODULES, KEY_ROWID + "=" + rowid, null);

	}

	// public int getRowID(int specified_position) {
	// String[] columns = new String[]{ KEY_MODULE_CODE, KEY_MODULE_NAME,
	// KEY_LECTURE_PRACTICAL, KEY_LECTURE_DAY, KEY_START_TIME,KEY_END_TIME,
	// KEY_LOCATION, ADDITIONAL_INFO};
	// Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null, null,
	// null, null);
	// int position = specified_position;
	// if(c.moveToPosition(position)) {
	// return c.getInt(0);
	// } else {
	// throw new IllegalArgumentException("Row " + position +
	// " does not exist");
	// }
	//
	// }

	public int getRowID(int specified_position) {
		String[] columns = new String[] { KEY_ROWID, KEY_MODULE_CODE,
				KEY_MODULE_NAME, KEY_LECTURE_PRACTICAL, KEY_LECTURE_DAY,
				KEY_START_TIME, KEY_END_TIME, KEY_LOCATION, ADDITIONAL_INFO };
		Cursor c = ourDatabase.query(DATABASE_MODULES, columns, null, null,
				null, null, null);
		int position = specified_position;
		if (c.moveToPosition(position)) {
			return c.getInt(c.getColumnIndex(KEY_ROWID));
		} else {
			throw new IllegalArgumentException("Row " + position
					+ " does not exist");
		}

	}
}
