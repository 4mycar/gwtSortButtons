package com.gransoft.sortbuttons.view;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.*;

import java.util.logging.Level;
import java.util.logging.Logger;


public class IntroScreen extends Composite {

    private static Logger logger = Logger.getLogger("");
    private final SortScreen sortScreen = new SortScreen();
    private final FlowPanel introContainer = new FlowPanel();
    private final Label questionLabel = new Label();
    private final TextBox numberField = new TextBox();
    private final Label errorLabel = new Label();
    private final Button enterButton = new Button("Enter");
    private final int RANGEMIN = 1;
    private final int RANGEMAX = 1000;

    void build() {

        introContainer.addStyleName("startPageContainer");
        questionLabel.setText("How many numbers to display?");
        questionLabel.addStyleName("questionLabel");
        numberField.setMaxLength(4);

        introContainer.add(questionLabel);
        introContainer.add(numberField);
        introContainer.add(enterButton);

        enterButton.addClickHandler(clickEvent -> buildSortScreen());
        numberField.addKeyUpHandler(keyUpEvent -> {
            if (keyUpEvent.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                buildSortScreen();
            }
        });
        initWidget(introContainer);
    }

    private void buildSortScreen(){
        errorLabel.setText("");
        String inputNumbers = numberField.getText();
        if (isValidNumber(inputNumbers, RANGEMIN, RANGEMAX)) {
            sortScreen.buildSortWindow(Integer.parseInt(inputNumbers));
            showWindow(sortScreen);
        } else {
            errorLabel.setText("Please enter number from 1 to 1000 [1,1000]");
            numberField.setFocus(true);
            numberField.selectAll();
        }
    }

    private void showWindow(Composite showWindow) {
        RootPanel.get("mainContent").remove(0);
        RootPanel.get("mainContent").add(showWindow);
    }

    private boolean isValidNumber(String inputNumber, int minNumber, int maxNumber) {
        logger.log(Level.SEVERE, "Enter numbers of display: " + inputNumber);
        try {
            int numbersToDisplay = Integer.parseInt(inputNumber);
            if (numbersToDisplay < minNumber || numbersToDisplay > maxNumber) return false;
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

}