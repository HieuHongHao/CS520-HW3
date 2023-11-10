import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import model.Transaction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import view.ExpenseTrackerView;

import javax.swing.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class testUndoEnabled {
    private ExpenseTrackerModel model;
    private ExpenseTrackerView view;
    private ExpenseTrackerController controller;

    @Before
    public void setup(){
        model = new ExpenseTrackerModel();
        view = new ExpenseTrackerView();
        controller = new ExpenseTrackerController(model, view);
        view.getSelectionModel().addListSelectionListener(e ->{
            List<Integer> rowsWithoutTotal = view.getSelectedRowsWithoutTotal();
            if(rowsWithoutTotal.size() == 0){
                view.disableUndoButton();
            }else{
                view.enableUndoButton();
            }
        });
        view.addUndoBtnListener(e -> {
            List<Integer> rowsWithoutTotal = view.getSelectedRowsWithoutTotal();
            System.out.println(rowsWithoutTotal.get(0));
            controller.removeTransaction(rowsWithoutTotal);
        });
    }

    @Test
    public void undoTransaction(){
        //Pre condition
        assertEquals(0, model.getTransactions().size());

        //Execution
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(100, "bills"));
        transactionList.add(new Transaction(100, "entertainment"));
        transactionList.add(new Transaction(300, "bills"));
        transactionList.add(new Transaction(300, "other"));
        for(Transaction transaction : transactionList){
            controller.addTransaction(transaction.getAmount(), transaction.getCategory());
        }
        JTable table = view.getTransactionsTable();
        //Programmatically select the second row
        table.setRowSelectionInterval(1, 1);
        //Programmatically click the undo button
        view.clickUndoBtn();

        //Post condition
        //Since the second transaction is removed, the current second transaction
        //should be the third transaction of the transaction table before the remove operation.
        double secondRowAmount = Double.parseDouble(table.getValueAt(1,1).toString());
        String secondRowCategory = table.getValueAt(1,2).toString();
        Date secondRowDate = null;
        Date thirdTransactionDate = null;
        try {
            secondRowDate = Transaction.dateFormatter.parse(table.getValueAt(1,3).toString());
            thirdTransactionDate = Transaction.dateFormatter.parse(transactionList.get(2).getTimestamp());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertNotNull(secondRowDate);
        assertNotNull(thirdTransactionDate);
        assertEquals(transactionList.get(2).getAmount(), secondRowAmount, 0.01);
        assertEquals(transactionList.get(2).getCategory(), secondRowCategory);
        assertTrue(thirdTransactionDate.getTime() - secondRowDate.getTime() < 60000);

        double rowWithTotal = Double.parseDouble(table.getValueAt(table.getRowCount() - 1, 3).toString());
        assertEquals(700, rowWithTotal, 0.01);

    }
}
