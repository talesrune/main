package duke.task;

import java.util.ArrayList;

public class BudgetList {
    public static final float INITIAL_BUDGET = 0;
    private ArrayList<Float> budgetList;

    /**
     * Creates an empty budget list using an array list if there are no
     * list of budget found.
     */
    public BudgetList() {
        budgetList = new ArrayList<>();
        budgetList.add(INITIAL_BUDGET);
    }

    /**
     * Creates an updated budget list with the specified array list.
     *
     * @param list The budget array list.
     */
    public BudgetList(ArrayList<Float> list) {
        budgetList = list;
    }

    /**
     * Changes the budget by the amount stated.
     *
     * @param amount the amount to be added into the budget.
     */
    public void addToBudget(float amount) {
        float currentBudget = budgetList.get(0);
        budgetList.add(amount);
        budgetList.set(0, currentBudget + amount);
    }

    /**
     * Gets the current total budget that is stored in budgetList.
     *
     * @return returns the budget that is stored in budgetList.
     */
    public float getBudget() {
        return budgetList.get(0);
    }

    /**
     * Resets the current budget in budgetList with the amount stated.
     *
     * @param amount The budget amount that is to be reset to.
     */
    public void resetBudget(float amount) {
        budgetList.set(0, amount);
    }

    /**
     * Get the size of the budget list.
     *
     * @return The size of the budget list.
     */
    public int getSize() {
        return budgetList.size();
    }

    /**
     * Get the list of budgets.
     *
     * @return The list of budget.
     */
    public ArrayList<Float> getList() {
        return this.budgetList;
    }


}