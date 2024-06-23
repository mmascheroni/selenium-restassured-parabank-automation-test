package models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {
    private int id;
    private int customerId;
    private String type;
    private float balance;

}
