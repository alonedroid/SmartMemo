package com.alonedroid.smartmemo.dao.info;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import rx.functions.Action0;
import rx.subjects.BehaviorSubject;

public class SmMemoDao {

    private Realm mRealm;
    private RealmQuery<SmMemoInfo> mQuery;

    public SmMemoDao() {
        mRealm = Realm.getDefaultInstance();
        mQuery = mRealm.where(SmMemoInfo.class);
    }

    public BehaviorSubject<RealmResults<SmMemoInfo>> fetchAllMemo() {
        final BehaviorSubject<RealmResults<SmMemoInfo>> subject = BehaviorSubject.create();
        async(() -> subject.onNext(mQuery.findAll()));
        return subject;
    }

    public void close() {
        if (mRealm == null) return;
        mRealm.close();
    }

    public boolean isClosed() {
        return (mRealm == null || mRealm.isClosed());
    }

    private void async(Action0 action) {
        new Thread(action::call).start();
    }
}
