package com.alonedroid.smartmemo.dao.info;

import java.util.HashSet;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.Sort;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action2;
import rx.subjects.BehaviorSubject;
import rx.subjects.ReplaySubject;

public class SmMemoDao {

    private Realm mListenRealm;

    public ReplaySubject<SmMemoInfo> fetchAllMemo() {
        final ReplaySubject<SmMemoInfo> subject = ReplaySubject.create();
        async((_realm, _query) ->
                Observable.from(_query.findAllSorted(SmMemoInfo.FIELD_CREATED_AT, Sort.DESCENDING))
                        .subscribe(info -> {
                            subject.onNext(info.clone());
                        }));
        return subject;
    }

    public BehaviorSubject<SmMemoInfo> fetchMemo(long id) {
        final BehaviorSubject<SmMemoInfo> subject = BehaviorSubject.create();
        async((_realm, _query) ->
                subject.onNext(_query.equalTo(SmMemoInfo.FIELD_ID, id).findFirst().clone()));
        return subject;
    }

    public void insertMemo(final String memo, final String tag) {
        async((_realm, _query) ->
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

    public void setOnChangeListener(Action0 action) {
        mListenRealm = Realm.getDefaultInstance();
        mListenRealm.addChangeListener(_realm -> action.call());
    }

    public void removeOnChangeListener() {
        if (mListenRealm == null) return;

        mListenRealm.removeAllChangeListeners();
        mListenRealm.close();
        mListenRealm = null;
    }

    public void removeMemo(final HashSet<Long> ids) {
        async((_realm, _query) ->
                _realm.executeTransaction(realm -> {
                    for (Long id : ids) {
                        _query.equalTo(SmMemoInfo.FIELD_ID, id).findFirst().deleteFromRealm();
                    }
                }));
    }

    public void updateMemo(long id, String memo) {
        async((_realm, _query) ->
                _realm.executeTransaction(realm -> {
                    SmMemoInfo info = _query.equalTo(SmMemoInfo.FIELD_ID, id).findFirst();
                    info.setMemo(memo);
                    realm.copyToRealmOrUpdate(info);
                }));
    }
}
