import java.io.IOException;
import java.util.List;

@FunctionalInterface
public interface Filter {
    List<String> search(List<String> list2) throws IOException;
}
