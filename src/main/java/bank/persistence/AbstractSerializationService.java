package bank.persistence;

import bank.domains.BankAccount;
import bank.domains.Category;
import bank.domains.Operation;
import bank.domains.OperationType;
import bank.facades.AccountFacade;
import bank.facades.CategoryFacade;
import bank.facades.OperationFacade;
import lombok.RequiredArgsConstructor;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractSerializationService<T> {
    public final void serialize(List<T> items, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (T item : items) {
                writer.write(serializeItem(item));
                writer.newLine();
            }
        }
    }

    public final List<T> deserialize(String filePath) throws IOException {
        List<T> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.isEmpty()) {
                    continue;
                }
                items.add(deserializeItem(line));
            }
        }
        return items;
    }

    protected abstract String serializeItem(T item);

    protected abstract T deserializeItem(String data);
}
