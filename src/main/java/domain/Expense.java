package domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Expense extends Transaction {
    private TypeExpense typeExpense;

    public Expense(long id, LocalDateTime date, double amount, String description, TypeTransaction typeTransaction, TypeExpense typeExpense){
        super(id, date, amount, description, typeTransaction);
        this.typeExpense = typeExpense;
    }
}
