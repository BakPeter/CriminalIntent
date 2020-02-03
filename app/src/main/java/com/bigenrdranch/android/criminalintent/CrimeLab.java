package com.bigenrdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.bigenrdranch.android.criminalintent.database.CrimeBaseHelper;
import com.bigenrdranch.android.criminalintent.database.CrimeCursorWrapper;
import com.bigenrdranch.android.criminalintent.database.CrimeDbSchema.CrimeTable;

public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null)
            sCrimeLab = new CrimeLab(context);

        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getReadableDatabase();

    }

    public void addCrime(Crime c) {
        ContentValues values = getContentValue(c);

        mDatabase.insert(CrimeTable.NAME, null, values);
    }

    public void deleteCrime(Crime crime) {
        mDatabase.delete(CrimeTable.NAME,
                CrimeTable.Cols.UUDI + " = ?",
                new String[]{crime.getId().toString()});
    }

    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return crimes;
    }

    public Crime getCrime(UUID id) {
        Crime crime = null;
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeTable.Cols.UUDI + " = ?",
                new String[]{id.toString()}
        );

        try {
//            if(cursor.getCount() == 0) {
//                crime = null;
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                crime = cursor.getCrime();
            }
        } finally {
            cursor.close();
        }

        return crime;
    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValue(crime);

        int count = mDatabase.update(CrimeTable.NAME, values,
                CrimeTable.Cols.UUDI + " = ?",
                new String[]{uuidString});
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new CrimeCursorWrapper(cursor);
    }

    private static ContentValues getContentValue(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUDI, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.DATE, crime.getDate().toString());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        values.put(CrimeTable.Cols.SUSPECT, crime.getnSuspect());

        return values;
    }
}