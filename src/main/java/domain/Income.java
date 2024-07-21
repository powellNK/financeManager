package domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Income extends Transaction {
    private TypeIncome typeIncome;

    public Income(long id, LocalDateTime date, double amount, String description, TypeTransaction typeTransaction, TypeIncome typeIncome){
        super(id, date, amount, description, typeTransaction);
        this.typeIncome = typeIncome;
    }
}
