package com.alonedroid.smartmemo.dao.info;

import io.realm.Realm;
import io.realm.RealmQuery;
import rx.Observable;
import rx.functions.Action2;
import rx.subjects.ReplaySubject;

public class SmMemoDao {

    public ReplaySubject<SmMemoInfo> fetchAllMemo() {
        final ReplaySubject<SmMemoInfo> subject = ReplaySubject.create();
        async((_realm, _query) -> Observable.from(_query.findAll()).subscribe(info -> {
            subject.onNext(info.clone());
        }));
        return subject;
    }

    public void insertMemo(final String memo, final String tag) {
        async((_realm, _where) ->
                _realm.executeTransaction(realm -> {
                    SmMemoInfo info = new SmMemoInfo(memo, tag);
                    realm.copyToRealmOrUpdate(info);
                }));
    }

    private void async(Action2<Realm, RealmQuery<SmMemoInfo>> action) {
        new Thread(() -> {
            Realm realm = Realm.getDefaultInstance();
            action.call(realm, realm.where(SmMemoInfo.class));
            realm.close();
        }).start();
    }
}
