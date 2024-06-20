package domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = {"id", "date", "amount", "description", "typeTransaction"})
public class Expense extends Transaction {
    private TypeExpense typeExpense;
}
