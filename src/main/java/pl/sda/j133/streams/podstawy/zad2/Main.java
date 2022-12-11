package pl.sda.j133.streams.podstawy.zad2;

import pl.sda.j133.streams.podstawy.zad1.Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Paweł Recław, AmeN
 * @project j133-streams
 * @created 10.12.2022
 */
public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Jacek", "Kowalski", 18, true);
        Person person2 = new Person("Jacek", "Górski", 15, true);
        Person person3 = new Person("Andżelika", "Dżoli", 25, false);
        Person person4 = new Person("Wanda", "Ibanda", 12, false);
        Person person5 = new Person("Marek", "Marecki", 17, true);
        Person person6 = new Person("Johny", "Brawo", 25, true);
        Person person7 = new Person("Stary", "Pan", 80, true);
        Person person8 = new Person("Newbie", "Noob", 12, true);
        Person person9 = new Person("Newbies", "Sister", 19, false);
        List<String> languages1 = Arrays.asList("Java;Cobol;Cpp;Lisp".split(";"));
        List<String> languages2 = Arrays.asList("Java;Lisp".split(";"));
        List<String> languages3 = Arrays.asList("Java;Cobol;Cpp;Lisp;C#".split(";"));
        List<String> languages4 = Arrays.asList("C#;C;Cpp".split(";"));
        List<String> languages5 = Arrays.asList("Java;Assembler;Scala;Cobol".split(";"));
        List<String> languages6 = Arrays.asList("Java;Scala".split(";"));
        List<String> languages7 = Arrays.asList("C#;C".split(";"));
        List<String> languages8 = Collections.emptyList();
        List<String> languages9 = Arrays.asList("Java");
        Programmer programmer1 = new Programmer(person1, languages1);
        Programmer programmer2 = new Programmer(person2, languages2);
        Programmer programmer3 = new Programmer(person3, languages3);
        Programmer programmer4 = new Programmer(person4, languages4);
        Programmer programmer5 = new Programmer(person5, languages5);
        Programmer programmer6 = new Programmer(person6, languages6);
        Programmer programmer7 = new Programmer(person7, languages7);
        Programmer programmer8 = new Programmer(person8, languages8);
        Programmer programmer9 = new Programmer(person9, languages9);
        List<Programmer> programmers = Arrays.asList(
                programmer1,
                programmer2,
                programmer3,
                programmer4,
                programmer5,
                programmer6,
                programmer7,
                programmer8,
                programmer9);
        System.out.println(programmers);


        // a. uzyskaj listę programistów, którzy są mężczyznami
        List<Programmer> resultA = programmers.stream()
                .filter(programmer -> programmer.getPerson().isMale())
//                .collect(Collectors.toList());
                .toList();
        System.out.println("Przykład a.: ");
        resultA.forEach(System.out::println);
        System.out.println();

        // b. uzyskaj listę niepełnoletnich programistów (obydwóch płci), którzy piszą w Cobolu
        List<Programmer> resultB = programmers.stream()
                .filter(programmer -> programmer.getPerson().getAge() < 18)
                .filter(programmer -> programmer.getLanguages().contains("Cobol"))
                .toList();
        System.out.println("Przykład b.: ");
        resultB.forEach(System.out::println);
        System.out.println();

        // c. uzyskaj listę programistów, którzy znają więcej, niż jeden język programowania
        List<Programmer> resultC = programmers.stream()
                .filter(programmer -> programmer.getLanguages().size() > 1)
                .toList();
        System.out.println("Przykład c.: ");
        resultC.forEach(System.out::println);
        System.out.println();

        // d. uzyskaj listę programistek, które piszą w Javie i Cpp
        List<Programmer> resultD = programmers.stream()
                .filter(programmer -> !programmer.getPerson().isMale())
                .filter(programmer -> programmer.getLanguages().containsAll(List.of("Java", "Cpp")))
                .toList();
        System.out.println("Przykład d.: ");
        resultD.forEach(System.out::println);
        System.out.println();

        // e. uzyskaj listę męskich imion
        List<String> resultE = programmers.stream()
                .filter(programmer -> programmer.getPerson().isMale())
                .map(programmer -> programmer.getPerson())
                .map(person -> person.getLastName())
                .toList();

        resultE = programmers.stream()
                .filter(programmer -> programmer.getPerson().isMale())
                .map(programmer -> programmer.getPerson().getLastName())
                .toList();
        System.out.println("Przykład e.: ");
        resultE.forEach(System.out::println);
        System.out.println();

        // f. uzyskaj set wszystkich języków opanowanych przez programistów
        Set<String> resultF = programmers.stream()
                .map(Programmer::getLanguages)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        resultF = programmers.stream()
                .flatMap(programmer -> programmer.getLanguages().stream())
                .collect(Collectors.toSet());
        System.out.println("Przykład f.: ");
        resultF.forEach(System.out::println);
        System.out.println();

        // g. * sprawdź, czy istnieje chociaż jedna osoba, która nie zna żadnego języka
        Optional<Person> resultH = programmers.stream()
                .filter(programmer -> programmer.getLanguages().isEmpty())
                .map(Programmer::getPerson)
                .findAny();
        System.out.println("Przykład g.: ");
        resultH.ifPresent(System.out::println);
        System.out.println();

        // i. * uzyskaj ilość wszystkich języków opanowanych przez programistki
        long resultI = programmers.stream()
                .filter(programmer -> !programmer.getPerson().isMale())
                .flatMap(programmer -> programmer.getLanguages().stream())
                .distinct()
                .count();

        resultI = programmers.stream()
                .filter(programmer -> !programmer.getPerson().isMale())
                .flatMap(programmer -> programmer.getLanguages().stream())
                .collect(Collectors.toSet())
                .size();
        System.out.println("Przykład i.: ");
        System.out.println(resultI);
        System.out.println();

        // j. **Używając streamów znajdź długość najdłuższej linii w wybranym przez ciebie pliku.
        try (BufferedReader reader = new BufferedReader(new FileReader("plik.txt"))) {
            // ^ otwieramy plik

            // używamy readera i metody `lines` która zwraca stream linii w pliku:
//            Optional<String> najdluzszaLinia = reader.lines()
//                    .max(Comparator.comparingInt(String::length));

            Optional<String> najdluzszaLinia = reader.lines()
                    .max((o1, o2) -> {
                        return Integer.compare(o1.length(), o2.length());
                    });

            System.out.println("Przykład j.: ");
            najdluzszaLinia.ifPresent(System.out::println);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
