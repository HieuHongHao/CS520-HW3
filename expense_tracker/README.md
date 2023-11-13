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

addListSelectionListener(): disable or enable undo button based on number of rows without total.

addUndoBtnListener(): remove transactions in rows without total.

## Architecture pattern

We use Model-View Controller as architecture for our project.

