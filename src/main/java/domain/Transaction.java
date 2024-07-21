package domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString()
@EqualsAndHashCode

public abstract class Transaction implements Serializable {
    protected long id;
    protected LocalDateTime date;
    protected double amount;
    protected String description;
    protected TypeTransaction typeTransaction;

}
