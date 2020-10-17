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
    private List<Button> numberButtonsList = new ArrayList<>();
    private FlowPanel sortContainer = new FlowPanel();
    private int[] elements;
    private boolean isDescSort = true;
    private QuickSort quickSort = new QuickSort();
//    public List<SortStep> sortStepList;


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
            quickSort.sort(elements, isDescSort);
            visualizeSwap(quickSort.getSortStepList());
//            renewButtons(elements);
            isDescSort = !isDescSort;
        });

        resetButton.addClickHandler(clickEvent -> Window.Location.reload());

        return buttonBlockContainer;
    }

//    public void visualizeSwap (int i, int j){
//        Timer stepTimer = new Timer() {
//            public void run() {
//                Button tmpBtn = numberButtonsList.get(i);
//                numberButtonsList.set(i,numberButtonsList.get(j));
//                numberButtonsList.set(j,tmpBtn);
//                numberButtonsList.get(j).setStyleName("gwt-Sorted-Button");
//                numberButtonsList.get(i).setStyleName("gwt-Sorted-Button");
//                //renewButtons(elements);
//            }
//        };
//
//        stepTimer.scheduleRepeating(1000);
//    }

    public void visualizeSwap(List<SortStep> sortStepList) {
        for (SortStep step: sortStepList) {
            resetStyleShowSort(step);
            logger.log(Level.SEVERE, "Step sort:\n" + step);
            setStyleShowSort(step);
        }
//
//        Timer stepTimer = new Timer() {
//            public void run() {
//                if (!sortStepList.iterator().hasNext()) {
//                    this.cancel();
//                    return;
//                }
//                SortStep step = sortStepList.iterator().next();
//                resetStyleShowSort(step);
//                logger.log(Level.SEVERE, "Step sort:\n" + step);
//                setStyleShowSort(step);
//            }
//        };
////        setStyleShowSort(sortStepList, 0);
//        stepTimer.scheduleRepeating(100);
    }

    private void setStyleShowSort(SortStep step) {

        for (int k = step.getLow(); k <= step.getHigh(); k++) {
            numberButtonsList.get(k).addStyleName("sortArray");
        }
        int i = step.getI();
        int j = step.getJ();

        numberButtonsList.get(step.getPivot()).addStyleName("paviot");
        numberButtonsList.get(i).addStyleName("pointer");
        numberButtonsList.get(j).addStyleName("pointer");
        numberButtonsList.get(i).addStyleName("swap");
        numberButtonsList.get(j).addStyleName("swap");
        updateNumberButtonsAll(i,j);

    }

    private void resetStyleShowSort(SortStep step) {
        int i = step.getI();
        int j = step.getJ();

        for (int k = step.getLow(); k <= step.getHigh(); k++) {
            numberButtonsList.get(k).removeStyleName("sortArray");
        }
        numberButtonsList.get(i).removeStyleName("pointer");
        numberButtonsList.get(i).removeStyleName("swap");
        numberButtonsList.get(j).removeStyleName("pointer");
        numberButtonsList.get(j).removeStyleName("swap");
        numberButtonsList.get(step.getPivot()).removeStyleName("paviot");
    }

    private void updateNumberButtonsAll(int i, int j) {
        int tmp = i;
        numberButtonsList.get(i).setText(String.valueOf(j));
        numberButtonsList.get(j).setText(String.valueOf(tmp));
    }

}