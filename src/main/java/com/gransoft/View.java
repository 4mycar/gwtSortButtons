package com.gransoft;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.core.client.EntryPoint;

import java.util.logging.Logger;


public class View implements EntryPoint {
    private static Logger logger = Logger.getLogger("");

    public void onModuleLoad() {
        StartPage startPage = new StartPage();
        startPage.build();
        RootPanel.get("mainContent").add(startPage);
    }

}
