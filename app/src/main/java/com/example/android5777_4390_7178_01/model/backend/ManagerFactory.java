package com.example.android5777_4390_7178_01.model.backend;

import com.example.android5777_4390_7178_01.model.datasource.ListDsManager;
import com.example.android5777_4390_7178_01.model.datasource.MySQL_DSManager;

/**
 * Created by יונתן on 04/12/2016.
 */

public class ManagerFactory {

    static IDSManager manager = null;

    public static IDSManager getManager() {
        if (manager == null)
            manager = new MySQL_DSManager();
        return manager;
    }
}

