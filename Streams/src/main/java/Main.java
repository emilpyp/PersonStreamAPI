import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        Random rng = new Random(77);
        IntStream
                .range(1,10)
                .mapToObj(i -> new
                        Person(i,
                        "name" + i,
                        "surname" + i,
                        String.valueOf(rng.nextInt(30))))
                .forEach(people::add);

        // Custom functional interface
        OlderThan18<List<Person>, Stream<Person>> olderThan18 =
                lst -> lst.stream()
                .filter(p -> Integer.parseInt(p.getAge()) >= 18);

        // Print people older than 18.
        olderThan18
                .getStream(people)
                .forEach(System.out::println);

        // Sum of ages of people 18 and older.
        int s = olderThan18
                .getStream(people)
                .reduce(0, ((sum, p) -> sum += Integer.parseInt(p.getAge())), ((sum1, sum2) -> sum1 += sum2));

        System.out.printf("Sum of ages over 18: %d\n", s);

        // Average age.
        people.stream()
                .mapToInt(p -> Integer.parseInt(p.getAge()))
                .average()
                .ifPresent(average -> System.out.printf("Average of all ages: %.2f\n", average));
    }


}
