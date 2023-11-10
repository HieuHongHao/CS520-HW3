import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import model.Transaction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import view.ExpenseTrackerView;

import javax.swing.*;
import java.text.ParseException;
import java.util.Date;

public class TestAddTransaction {
    private ExpenseTrackerModel model;
    private ExpenseTrackerView view;
    private ExpenseTrackerController controller;

    @Before
    public void setup(){
        model = new ExpenseTrackerModel();
        view = new ExpenseTrackerView();
        controller = new ExpenseTrackerController(model, view);
    }

    @Test
    public void addTransaction(){
//        Pre condition
        JTable table = view.getTransactionsTable();
        assertEquals(0, table.getRowCount());

//        Execution
        controller.addTransaction(50.00, "food");

//        Post Condition
        assertTrue(table.getRowCount() >= 2);
        int lastTransactionRow = table.getRowCount() - 2;
        double amount = Double.parseDouble(table.getValueAt(lastTransactionRow, 1).toString());
        String category =  table.getValueAt(lastTransactionRow, 2).toString();
        Date date = null;
        try {
            date =  Transaction.dateFormatter.parse(table.getValueAt(lastTransactionRow, 3).toString());
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        assertNotNull(date);
        assertEquals(50.00, amount, 0.01);
        assertEquals("food", category);
        assertTrue(new Date().getTime() - date.getTime() < 60000);
    }
}
