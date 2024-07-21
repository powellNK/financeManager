package database;

import domain.*;
import exceptions.TransactionTypeNotFoundException;
import factory.TransactionFactory;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Database implements Serializable {
    private static final long serialVersionUID = 1L;
    public static List<Transaction> transactions = new ArrayList();

    public static void saveDB() {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("db.serialization"))) {
            oos.writeObject(transactions);
        } catch (IOException exception) {
            System.out.println("Не удалось сохранить данные");
        }
    }

    public static void add(double amount, String description, TypeIncome typeIncome) {
        Transaction transaction = TransactionFactory.createTransaction(
                transactions.size() + 1,
                LocalDateTime.now(),
                amount,
                description,
                typeIncome
        );
        transactions.add(transaction);

        saveDB();
    }

    public static void add(double amount, String description, TypeExpense typeExpense) {
        Transaction transaction = TransactionFactory.createTransaction(
                transactions.size() + 1,
                LocalDateTime.now(),
                amount,
                description,
                typeExpense
        );

        transactions.add(transaction);
        saveDB();
    }

    public static void printTransactions() {
        for (Transaction value : transactions) {
            System.out.println(value);
        }
    }


    public static void searchByType(TypeExpense typeExpense) {
        transactions
                .stream()
                .filter(transaction -> transaction.getTypeTransaction().equals(TypeTransaction.EXPENSE))
                .map(transaction -> (Expense) transaction)
                .filter(expense -> expense.getTypeExpense().equals(typeExpense))
                .forEach(System.out::println);
    }


    public static void searchByType(TypeIncome typeIncome) {
        transactions
                .stream()
                .filter(transaction -> transaction.getTypeTransaction().equals(TypeTransaction.INCOME))
                .map(transaction -> (Income) transaction)
                .filter(income -> income.getTypeIncome().equals(typeIncome))
                .forEach(System.out::println);
    }

    public static void loadDB() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("db.serialization"))) {
            transactions = (List<Transaction>) ois.readObject();
            System.out.println("База данных успешно загружена");
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Не удалось загрузить базу данных: " + exception.getMessage());
        }
    }

    public static TypeIncome getIncomeType(String inputValue) {
        return Arrays.stream(TypeIncome.values())
                .filter(income -> income.name().equals(inputValue))
                .findFirst()
                .orElseThrow(() -> new TransactionTypeNotFoundException(STR."\{inputValue} not found in TypeIncome"));
    }

    public static TypeExpense getExpenseType(String inputValue) {
        return Arrays.stream(TypeExpense.values())
                .filter(expense -> expense.name().equals(inputValue))
                .findFirst()
                .orElseThrow(() -> new TransactionTypeNotFoundException(STR."\{inputValue} not found in TypeExpense"));
    }

    public static void printIncomes() {
        transactions
                .stream()
                .filter(income -> income.getTypeTransaction().equals(TypeTransaction.INCOME))
                .forEach(System.out::println);
    }

    public static void printExpenses() {
        transactions
                .stream()
                .filter(expense -> expense.getTypeTransaction().equals(TypeTransaction.EXPENSE))
                .forEach(System.out::println);
    }


    public Transaction getTransactionById(int id) {
        return transactions.get(id);
    }
}
