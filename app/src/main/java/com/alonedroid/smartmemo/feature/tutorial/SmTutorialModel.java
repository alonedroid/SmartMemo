package com.alonedroid.smartmemo.feature.tutorial;

import com.alonedroid.smartmemo.dao.info.SmMemoDao;
import com.alonedroid.smartmemo.util.SmKVSData;
import com.alonedroid.smartmemo.util.SmResourceManager;

public class SmTutorialModel {

    /**
     * メモ削除に関するチュートリアル
     */
    public static void putTutorialMemo1(){
        SmMemoDao dao = new SmMemoDao();
        dao.insertMemo(SmResourceManager.getTutorialMemo1(), "");
        SmKVSData.putTutorial1Done();
    }
}
