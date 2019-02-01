package interfaces;

import model.Journal;
import model.Person;
import java.io.IOException;

public interface Saveable {
    void save(Person p, Journal j) throws IOException;
}
