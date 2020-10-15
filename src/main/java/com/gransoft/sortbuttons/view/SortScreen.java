package com.gransoft.sortbuttons.view;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.gransoft.sortbuttons.logic.QuickSort;
import com.gransoft.sortbuttons.logic.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortScreen extends Composite {

    private static Logger logger = Logger.getLogger("");

    private final Randomizer randomizer = new Randomizer();
    private final QuickSort quickSort = new QuickSort();
    private final Button sortButton = new Button("Sort");
    private final Button resetButton = new Button("Reset");
    private List<Button> numberButtonsList = new ArrayList<>();
    private FlowPanel sortContainer = new FlowPanel();
    private int[] elements;
    private boolean isDescSort = true;

    void buildSortWindow(int numbersOfDisplay) {
        sortContainer.addStyleName("sortContainer");
        elements = randomizer.generateArrayInt(numbersOfDisplay);
        sortContainer.add(addButtonsToView(elements));
        sortContainer.add(buildButtonBlock());
        initWidget(sortContainer);
    }

    private Widget addButtonsToView(int[] elements) {
        numberButtonsList.clear();
        FlowPanel numbersBlockContainer = new FlowPanel();
        numbersBlockContainer.addStyleName("numbersBlockContainer");
        FlowPanel numberBlockGroup = null;
        for (int i = 0; i < elements.length; i++) {
            if (i % 10 == 0) {
                numberBlockGroup = new FlowPanel();
                numberBlockGroup.addStyleName("numberBlock");
                numbersBlockContainer.add(numberBlockGroup);
            }
            Button numberBtn = new Button(String.valueOf(elements[i]));
            numberBtn.addClickHandler(clickEvent -> {
                isDescSort = false;
                int numberClick = Integer.parseInt(clickEvent.getRelativeElement().getInnerText());
                logger.log(Level.SEVERE, "Clicked number button with number=" + numberClick);
                if (numberClick > 30) {
                    PopupPanel popupPanel = new PopupPanel(true);
                    popupPanel.add(new Label("Please select a value smaller or equal to 30."));
                    popupPanel.show();
                    int popupPanelX = clickEvent.getClientX();
                    int popupPanelY = clickEvent.getClientY() - popupPanel.getElement().getAbsoluteBottom();
                    popupPanel.setPopupPosition(popupPanelX, popupPanelY);
                } else {
                    this.elements = randomizer.generateArrayInt(numberClick);
                    renewButtons(this.elements);
                }
            });
            numberButtonsList.add(numberBtn);
            numberBlockGroup.add(numberBtn);
        }
        return numbersBlockContainer;
    }

    public void renewButtons(int[] elements) {
        sortContainer.remove(0);
        sortContainer.insert(addButtonsToView(elements), 0);
    }

    private Widget buildButtonBlock() {
        FlowPanel buttonBlockContainer = new FlowPanel();
        buttonBlockContainer.addStyleName("buttonBlock");
        sortButton.addStyleName("btnSort");
        resetButton.addStyleName("btnSort");
        buttonBlockContainer.add(sortButton);
        buttonBlockContainer.add(resetButton);

        sortButton.addClickHandler(clickEvent -> {
            quickSort.sort(elements, 0, elements.length - 1, isDescSort);
            renewButtons(elements);
            isDescSort = !isDescSort;
        });

        resetButton.addClickHandler(clickEvent -> Window.Location.reload());

        return buttonBlockContainer;
    }

}