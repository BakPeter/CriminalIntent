package com.bigenrdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private String nSuspect;

    public Crime() {
        this(UUID.randomUUID());
    }

    public Crime(UUID id) {
        setId(id);
        setDate(new Date());
    }

    public UUID getId() {
        return mId;
    }

    private void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public String getnSuspect() {
        return nSuspect;
    }

    public void setnSuspect(String nSuspect) {
        this.nSuspect = nSuspect;
    }

    @Override
    public String toString() {
        return "Crime{" +
                "mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                ", mDate=" + mDate +
                ", mSolved=" + mSolved +
                '}';
    }
}
