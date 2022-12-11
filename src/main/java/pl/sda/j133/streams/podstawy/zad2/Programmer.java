package pl.sda.j133.streams.podstawy.zad2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.j133.streams.podstawy.zad1.Person;

import java.util.List;

/**
 * @author Paweł Recław, AmeN
 * @project j133-streams
 * @created 10.12.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Programmer {
    private Person person;
    private List<String> languages;
}
