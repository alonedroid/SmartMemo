package com.alonedroid.smartmemo.dao.info;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SmMemoInfo extends RealmObject {

    @PrimaryKey
    private long id;
    private String memo;
    private String tag;
    private Date createDate;
    private Date updateDate;

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

    public Date getCreateDate() {
        return createDate;
    }

    public SmMemoInfo setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public SmMemoInfo setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
        return this;
    }
}
