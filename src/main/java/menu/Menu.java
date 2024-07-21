package menu;

import database.Database;
import domain.*;
import exceptions.TransactionTypeNotFoundException;

import java.io.*;
import java.util.stream.Stream;

public class Menu {
    public static void printMenu() {
        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))
        ) {

            int numberOfMenu;
            do {
                System.out.println("Выберите действие:");
                System.out.println("1. Добавить новую транзакцию");
                System.out.println("2. Список всех транзакций");
                System.out.println("3. Список доходов");
                System.out.println("4. Список расходов");
                System.out.println("5. Фильтрация по определенной категории расходов");
                System.out.println("6. Фильтрация по определенной категории расходов");
                numberOfMenu = Integer.parseInt(bufferedReader.readLine());

                switch (numberOfMenu) {
                    case 1 -> addTransaction(bufferedReader);
                    case 2 -> Database.printTransactions();
                    case 3 -> Database.printIncomes();
                    case 4 -> Database.printExpenses();
                    case 5-> FilterByIncomeCategory(bufferedReader);
                    case 6 -> FilterByExpenseCategory(bufferedReader);
                }
            }
            while (numberOfMenu != 0);


        } catch (IOException exception) {

        }
    }

    private static void FilterByExpenseCategory(BufferedReader bufferedReader) throws IOException {
        String typeSearchExpence = bufferedReader.readLine().toUpperCase();
        TypeExpense inputTypeExpense = getExpenseType(typeSearchExpence);
        Database.searchByType(inputTypeExpense);
    }

    private static void FilterByIncomeCategory(BufferedReader bufferedReader) throws IOException {
        String typeSearchIncome = bufferedReader.readLine().toUpperCase();
        TypeIncome inputTypeIncome = getIncomeType(typeSearchIncome);
        Database.searchByType(inputTypeIncome);
    }

    private static void addTransaction(BufferedReader bufferedReader) throws IOException {
        System.out.println("Выберите тип транзакции: INCOME(Доход) или EXPENSE(Расход)");
        String inputTypeTransaction = bufferedReader.readLine();
        TypeTransaction typeTransaction = TypeTransaction.valueOf(inputTypeTransaction.toUpperCase());
        System.out.println("Количество переведенных денег:");
        double money = Double.parseDouble(bufferedReader.readLine());
        System.out.println("Введите описание:");
        String description = bufferedReader.readLine();

        String inputValue;
        Object typeValue;
        if (typeTransaction.equals(TypeTransaction.INCOME)) {
            printEnumValues(TypeIncome.values());
            inputValue = bufferedReader.readLine().toUpperCase();
            try {
                typeValue = getIncomeType(inputValue);
                Database.add(money, description, (TypeIncome) typeValue);

                System.out.println("Транзакция успешно добавлена");
            } catch (TransactionTypeNotFoundException e) {
                System.out.println(e.getMessage());
            }


        } else {

            printEnumValues(TypeExpense.values());
            inputValue = bufferedReader.readLine().toUpperCase();
            try {
                typeValue = getExpenseType(inputValue);
                Database.add(money, description, (TypeExpense) typeValue);

                System.out.println("Транзакция успешно добавлена");
            } catch (TransactionTypeNotFoundException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private static TypeIncome getIncomeType(String inputValue) {
        return Database.getIncomeType(inputValue);
    }

    private static TypeExpense getExpenseType(String inputValue) {
        return Database.getExpenseType(inputValue);
    }

    private static void printEnumValues(Enum<?>[] values) {
        Stream.of(values).forEach(type -> System.out.println(type.name()));
    }


    public static void loadDB() {
        Database.loadDB();
    }

}
