import java.util.List;
import java.util.stream.Stream;

@FunctionalInterface
public interface OlderThan18<List, Stream>{
    Stream getStream(List lst);
}
