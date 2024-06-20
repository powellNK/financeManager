package domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, includeFieldNames = true, of = {"id", "amount", "description", "typeTransaction", "typeIncome"})
public class Income extends Transaction {
    private TypeIncome typeIncome;
}
