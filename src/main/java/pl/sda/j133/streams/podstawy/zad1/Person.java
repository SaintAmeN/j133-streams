package pl.sda.j133.streams.podstawy.zad1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Paweł Recław, AmeN
 * @project j133-streams
 * @created 10.12.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private double age;
    private boolean male;
}
