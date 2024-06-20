package domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode

public abstract class Transaction implements Serializable {
    protected long id;
    protected ZonedDateTime date;
    protected double amount;
    protected String description;
    protected TypeTransaction typeTransaction;

}
