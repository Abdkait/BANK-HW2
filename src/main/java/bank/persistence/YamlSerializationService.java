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

import java.util.Map;

public abstract class YamlSerializationService<T> extends AbstractSerializationService<T> {
    private final Yaml yaml = new Yaml();

    @Override
    protected String serializeItem(T item) {
        return yaml.dump(createMap(item));
    }

    @Override
    protected T deserializeItem(String data) {
        Map<String, Object> map = yaml.load(data);
        return createFromMap(map);
    }

    protected abstract T createFromMap(Map<String, Object> data);

    protected abstract Map<String, Object> createMap(T item);
}
