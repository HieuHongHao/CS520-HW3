# hw2

## Folder structure

### expense_tracker/src/controller

#### ExpenseTrackerController

removeTransaction():

Remove transation based on row index, then rerenders the page.

### expense_tracker/src/view

#### ExpenseTrackerView

enableUndoButton(), disableUndoButton(), addUndoBtnListener():

Enable undo button, disable undo button, and add event listener for undo button.


### ExpenseTrackerApp

We use highLightRows() method to highlight filtered row to green color.

## Design pattern

We use strategy design pattern.

We enable the organization of individual filter algorithms into reusable classes. Adding new filters is straightforward by introducing new methods. Also, Users have the flexibility to filter by either "amount" or "category" individually.

