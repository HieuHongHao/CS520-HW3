import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import view.ExpenseTrackerView;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestUndoDisabled {
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
    public void undoDisabled(){
        //  Pre condition
        assertTrue(model.getTransactions().size() == 0);

        // execution
        AtomicBoolean clicked = new AtomicBoolean(false);
        view.addUndoBtnListener(e -> {
            clicked.set(true);
        });
        view.clickUndoBtn();

        // Post condition
        assertFalse(clicked.get());
        assertTrue(model.getTransactions().size() == 0);
    }

}
