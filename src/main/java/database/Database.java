package database;

import domain.*;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.*;
public class Database implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Map<Long, Transaction> transactions = new HashMap<>();
    private static HashSet<Income> incomes = new HashSet<>();
    private static List<Expense> expenses = new ArrayList<>();

    public static void saveDB() {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("db.serialization"))) {
            oos.writeObject(transactions);
            oos.writeObject(incomes);
            oos.writeObject(expenses);
        } catch (IOException exception) {
            System.out.println("Не удалось сохранить данные");
        }
    }

    public static Transaction add(Transaction transaction, double amount, String description) {
        transaction.setId(transactions.size() + 1);
        transaction.setDate(ZonedDateTime.now());
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transactions.put(transaction.getId(), transaction);
        saveDB();
        return transaction;
    }

    public static Transaction createIncomeTransaction(TypeIncome typeIncome) {
        Income incomeTransaction = new Income();
        incomeTransaction.setTypeTransaction(TypeTransaction.INCOME);
        incomeTransaction.setTypeIncome(typeIncome);
        incomes.add(incomeTransaction);
        return incomeTransaction;
    }

    public static Transaction createExpenseTransaction(TypeExpense typeExpense) {
        Expense expenseTransaction = new Expense();
        expenseTransaction.setTypeTransaction(TypeTransaction.EXPENSE);
        expenseTransaction.setTypeExpense(typeExpense);
        expenses.add(expenseTransaction);
        return expenseTransaction;
    }

    public static void printTransactions() {
        for (Transaction value : transactions.values()) {
            System.out.println(value);
        }
    }

    public static void printIncomes() {
        for (Income value : incomes) {
            System.out.println(value);
        }
    }

    public static void printExpenses() {
        for (Expense value : expenses) {
            System.out.println(value);
        }
    }

    public static void searchByType(TypeExpense typeExpense) {
        expenses.stream()
                .filter(expense -> expense.getTypeExpense().equals(typeExpense))
                .forEach(System.out::println);

    }


    public static void searchByType(TypeIncome typeIncome) {
        incomes.stream()
                .filter(income -> income.getTypeIncome().equals(typeIncome))
                .forEach(System.out::println);
    }

    public static void loadDB() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("db.serialization"))) {
            transactions = (Map<Long, Transaction>) ois.readObject();
            incomes = (HashSet<Income>) ois.readObject();
            expenses = (List<Expense>) ois.readObject();
            System.out.println("База данных успешно загружена");
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Не удалось загрузить базу данных: " + exception.getMessage());
        }
    }


    public Transaction getTransactionById(int id) {
        return transactions.get(id);
    }
}
