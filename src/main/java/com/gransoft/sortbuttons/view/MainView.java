package com.gransoft.sortbuttons.view;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.core.client.EntryPoint;


public class MainView implements EntryPoint {

    private final IntroScreen introScreen = new IntroScreen();

    public void onModuleLoad() {
        introScreen.build();
        RootPanel.get("mainContent").add(introScreen);
    }

}
