import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import model.Transaction;
import org.junit.Before;
import org.junit.Test;
import view.ExpenseTrackerView;

public class TestInvalidInput {
    private ExpenseTrackerModel model;
    private ExpenseTrackerView view;
    private ExpenseTrackerController controller;

    @Before
    public void setup(){
        model = new ExpenseTrackerModel();
        view = new ExpenseTrackerView();
        controller = new ExpenseTrackerController(model, view);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addInvalidTransaction(){
        Transaction invalidAmountTransaction = new Transaction(-10.00, "bills");
        model.addTransaction(invalidAmountTransaction);
    }

}
