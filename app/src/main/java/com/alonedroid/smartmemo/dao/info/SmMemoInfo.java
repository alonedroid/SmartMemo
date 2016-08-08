package com.alonedroid.smartmemo.dao.info;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SmMemoInfo extends RealmObject {

    @PrimaryKey
    private long id;
    private String memo;
    private String tag;
    private Date createdAt;
    private Date updatedAt;

    public SmMemoInfo() {

    }

    public SmMemoInfo(String _memo, String _tag) {
        this.id = Long.valueOf(String.valueOf(DateFormat.format("yyyyMMddkkmmss", Calendar.getInstance())));
        this.memo = _memo;
        this.tag = _tag;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public long getId() {
        return id;
    }

    public SmMemoInfo setId(long id) {
        this.id = id;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public SmMemoInfo setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public SmMemoInfo setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public SmMemoInfo setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public SmMemoInfo setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public SmMemoInfo clone(){
        return new SmMemoInfo()
                .setId(this.id)
                .setMemo(this.memo)
                .setTag(this.tag)
                .setCreatedAt(this.createdAt)
                .setUpdatedAt(this.updatedAt);
    }
}
