package bank.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Provider<T> {
    List<T> getAll();

    Optional<T> get(int id);

    T add(T t);

    void save(T t);

    void delete(int id);
}