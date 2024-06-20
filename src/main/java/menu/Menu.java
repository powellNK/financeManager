package menu;

import database.Database;
import domain.*;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Menu {
    public static void printMenu() {
        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ) {

            Integer numberOfMenu = 0;
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
                    case 1:

                        System.out.println("Выберите тип транзакции: INCOME(Доход) или EXPENSE(Расход)");
                        String inputTypeTransaction = bufferedReader.readLine();
                        TypeTransaction typeTransaction = TypeTransaction.valueOf(inputTypeTransaction.toUpperCase());
                        System.out.println("Количество переведенных денег:");
                        Double money = Double.parseDouble(bufferedReader.readLine());
                        System.out.println("Введите описание:");
                        String description = bufferedReader.readLine();

                        if (typeTransaction.equals(TypeTransaction.INCOME)) {
                            addIncomeTransaction(bufferedReader, money, description);
                        } else {
                            addExpenseTransaction(bufferedReader, money, description);
                        }

                        System.out.println("Транзакция успешно добавлена");

                        break;

                    case 2:
                        Database.printTransactions();
                        break;
                    case 3:
                        Database.printIncomes();
                        break;
                    case 4:
                        Database.printExpenses();
                        break;
                    case 5:
                        String typeSearchExpence = bufferedReader.readLine().toUpperCase();
                        TypeExpense typeExpense = TypeExpense.valueOf(typeSearchExpence.toUpperCase());
                        Database.searchByType(typeExpense);
                        break;
                    case 6:
                        String typeSearchIncome = bufferedReader.readLine().toUpperCase();
                        TypeIncome typeIncome = TypeIncome.valueOf(typeSearchIncome.toUpperCase());
                        Database.searchByType(typeIncome);
                        break;
                }            }
                while (numberOfMenu != 0) ;


        } catch (IOException exception) {

        }
    }

    private static void addExpenseTransaction(BufferedReader bufferedReader, Double money, String description) throws IOException {
        final TypeExpense[] valuesExpense = TypeExpense.values();
        System.out.println("Выберите тип расхода: ");

        for (var value : valuesExpense) {
            System.out.println(value.name());
        }
        String inputTypeExpense = bufferedReader.readLine();
        TypeExpense typeExpense = TypeExpense.valueOf(inputTypeExpense.toUpperCase());
        Database.add(Database.createExpenseTransaction(typeExpense), money, description);
    }

    private static void addIncomeTransaction(BufferedReader bufferedReader, Double money, String description) throws IOException {
        final TypeIncome[] valuesIncome = TypeIncome.values();
        System.out.println("Выберите тип дохода: ");

        for (var value : valuesIncome) {
            System.out.println(value.name());
        }
        String inputTypeIncome = bufferedReader.readLine();
        TypeIncome typeIncome = TypeIncome.valueOf(inputTypeIncome.toUpperCase());
        Database.add(Database.createIncomeTransaction(typeIncome), money, description);
    }


    public static void loadDB() {
Database.loadDB();
    }

}
