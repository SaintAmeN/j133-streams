package pl.sda.j133.streams.podstawy.zad1;

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

        List<Person> people = new ArrayList<Person>(Arrays.asList(person1, person2, person3, person4, person5, person6, person7, person8, person9));

        //  a. uzyskaj listę mężczyzn
//        List<Person> wynik0 = people.stream()
//                .filter(Person::isMale)
//                .collect(Collectors.toList());

        List<Person> wynik1 = people.stream()
                .filter(person -> person.isMale())
                .collect(Collectors.toList());
        System.out.println("Wynik zadanie a: " + wynik1);

//        List<Person> wynik2 = people.stream()
//                .filter(new Predicate<Person>() {
//                    @Override
//                    public boolean test(Person person) {
//                        return person.isMale();
//                    }
//                })
//                .collect(Collectors.toList());

        // b. uzyskaj listę dorosłych kobiet (filter)
//        List<Person> wynikB = people.stream()
//                .filter(person -> !person.isMale())
//                .filter(person -> person.getAge() >= 18)
//                .collect(Collectors.toList());
        List<Person> wynikB = people.stream()
                .filter(person -> !person.isMale() && person.getAge() >= 18)
                .collect(Collectors.toList());
        System.out.println("Wynik zadanie b: " + wynikB);

        // c. uzyskaj Optional<Person> z dorosłym Jackiem findAny/findfirst
        Optional<Person> wynikC = people.stream()
                .filter(person -> person.getAge() >= 18 && person.getFirstName().equals("Jacek"))
                .findFirst();
        if (wynikC.isPresent()) {
            Person personC = wynikC.get();
            System.out.println("Wynik zadanie c: " + personC);
        }

        // d. uzyskaj listę wszystkich nazwisk osób, które są w przedziale wiekowym: 15-19 (filter)
        List<String> nazwiska = people.stream()
                .filter(person -> person.getAge() >= 15 && person.getAge() <= 19)
                .map(person -> person.getLastName())
                .collect(Collectors.toList());
        System.out.println("Wynik zadanie d:" + nazwiska);


        // e. * uzyskaj sumę wieku wszystkich osób (sum)
        double sumaWieku = people.stream()
                .mapToDouble(person -> person.getAge()) /// DoubleStream
                .sum();
        System.out.println("Wynik zadanie e: " + sumaWieku);

        // f. * uzyskaj średnią wieku wszystkich mężczyzn (average)
        OptionalDouble sredniaWieku = people.stream()
                .filter(person -> person.isMale())
                .mapToDouble(person -> person.getAge())
                .average();
        if (sredniaWieku.isPresent()) {
            System.out.println("Wynik f: " + sredniaWieku.getAsDouble());
        }

        // g. ** znajdź najstarszą osobę w liście (findFirst)
//        people.stream()
//                .max((o1, o2) -> {
//                    return Double.compare(o1.getAge(), o2.getAge());
//                })...

        Optional<Person> maxAgePerson = people.stream()
                .max(Comparator.comparingDouble(Person::getAge));
        if (maxAgePerson.isPresent()) {
            Person person = maxAgePerson.get();
            System.out.println("Wynik g:" + person);
        }
    }
}
