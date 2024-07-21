package factory;

import domain.*;

import java.time.LocalDateTime;

public class TransactionFactory {
    public static Income createTransaction(long id, LocalDateTime date, double amount, String description, TypeIncome typeIncome) {
        return new Income(id, date, amount, description, TypeTransaction.INCOME, typeIncome);
    }

    public static Expense createTransaction(long id, LocalDateTime date, double amount, String description, TypeExpense typeExpense) {
        return new Expense(id, date, amount, description, TypeTransaction.EXPENSE, typeExpense);
    }
}
