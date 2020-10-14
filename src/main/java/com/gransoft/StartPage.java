package com.gransoft;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class StartPage page
 */
public class StartPage extends Composite {
    private static Logger logger = Logger.getLogger("");
    private final FlowPanel introContainer = new FlowPanel();
    private final Label questionLabel = new Label();
    private final TextBox numberField = new TextBox();
    private final Button enterButton = new Button("Enter");

    void build() {

        introContainer.addStyleName("startPageContainer");
        questionLabel.setText("How many numbers to display?");
        questionLabel.addStyleName("questionLabel");
        numberField.setMaxLength(4);

        introContainer.add(questionLabel);
        introContainer.add(numberField);
        introContainer.add(enterButton);
        initWidget(introContainer);

        numberField.addKeyUpHandler(keyUpEvent -> {
            if (keyUpEvent.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            }
        });
    }

}