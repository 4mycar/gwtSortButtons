package com.gransoft.sortbuttons.view;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.gransoft.sortbuttons.logic.QuickSort;
import com.gransoft.sortbuttons.logic.Randomizer;
import com.gransoft.sortbuttons.pojo.SortStep;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortScreen extends Composite {

    private static Logger logger = Logger.getLogger("");
    private final Randomizer randomizer = new Randomizer();
    private final Button sortButton = new Button("Sort");
    private final Button resetButton = new Button("Reset");
    private final CheckBox checkBox = new CheckBox("Visualize sorting");
    private List<Button> numberButtonsList = new ArrayList<>();
    private FlowPanel sortContainer = new FlowPanel();
    private int[] elements;
    private boolean isDescSort = true;
    private boolean isVisualize = true;
    private QuickSort quickSort = new QuickSort();


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
        checkBox.setValue(true);
        buttonBlockContainer.add(sortButton);
        buttonBlockContainer.add(resetButton);
        buttonBlockContainer.add(checkBox);

        checkBox.addClickHandler(clickEvent -> isVisualize = ((CheckBox) clickEvent.getSource()).getValue());

        sortButton.addClickHandler(clickEvent -> {
            quickSort.sort(elements, isDescSort);
            if (isVisualize) {
                visualizeSwap(quickSort.getSortStepList());
            } else {
                renewButtons(elements);
            }
            isDescSort = !isDescSort;
        });

        resetButton.addClickHandler(clickEvent -> Window.Location.reload());

        return buttonBlockContainer;
    }

    public void visualizeSwap(List<SortStep> sortStepList) {
        Timer stepTimer = new Timer() {
            int i = 0;

            public void run() {
                if (i == sortStepList.size()) {
                    resetStyleAllButtons();
                    this.cancel();
                    return;
                }
                SortStep step = sortStepList.get(i);
                logger.log(Level.SEVERE, "Step sort:\n" + step);
                resetStyleAllButtons();
                setStyleShowSort(step);
                i++;
            }
        };
        stepTimer.scheduleRepeating(100);
    }

    private void setStyleShowSort(SortStep step) {

        for (int k = step.getLow(); k <= step.getHigh(); k++) {
            numberButtonsList.get(k).addStyleName("sortArray");
        }
        int i = step.getI();
        int j = step.getJ();

        numberButtonsList.get(step.getPivot()).addStyleName("pivot");
        numberButtonsList.get(i).addStyleName("pointer");
        numberButtonsList.get(j).addStyleName("pointer");
        if (step.isSwap()) {
            numberButtonsList.get(i).addStyleName("swap");
            numberButtonsList.get(j).addStyleName("swap");
            updateButtonNumbers(i, j);
        }
    }

    private void resetStyleAllButtons() {

        for (Button button : numberButtonsList) {
            button.removeStyleName("sortArray");
            button.removeStyleName("pointer");
            button.removeStyleName("swap");
            button.removeStyleName("pivot");
        }
    }

    private void updateButtonNumbers(int i, int j) {
        String tmp = numberButtonsList.get(i).getText();
        numberButtonsList.get(i).setText(numberButtonsList.get(j).getText());
        numberButtonsList.get(j).setText(tmp);
    }

}