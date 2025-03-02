package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class JavaCollectionSynchronizedTest {
    @Test
    void create() {
        List<String> list = List.of("Fauzan", "Addien");
        List<String> syncList = Collections.synchronizedList(list);
    }
}
