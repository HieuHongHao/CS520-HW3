import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import model.Filter.CategoryFilter;
import model.Transaction;
import org.junit.Before;
import org.junit.Test;
import view.ExpenseTrackerView;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TestCategoryFilter {
    private ExpenseTrackerModel model;
    private ExpenseTrackerView view;
    private ExpenseTrackerController controller;

    @Before
    public void setup() {
        model = new ExpenseTrackerModel();
        view = new ExpenseTrackerView();
        controller = new ExpenseTrackerController(model, view);
    }

    public void assertColorLightGreen(Color color){
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        assertEquals(173, red);
        assertEquals(255, green);
        assertEquals(168, blue);
    }

    @Test
    public void filterByCategory(){
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
        controller.setFilter(new CategoryFilter("bills"));
        controller.applyFilter();
        //Post condition
        JTable table = view.getTransactionsTable();
        int countTransaction = 0;
        for(int i = 0; i < table.getRowCount() - 1; i ++){
            String category = table.getValueAt(i,2).toString();
            if(!category.equals("bills")){
                continue;
            }
            countTransaction ++;
            Component render = table.prepareRenderer(table.getCellRenderer(i,0), i, 0);
            Color backgroundColor = render.getBackground();
            //Check if background color of filtered row is light green
            assertColorLightGreen(backgroundColor);
        }
        assertEquals(2, countTransaction);
    }
}
