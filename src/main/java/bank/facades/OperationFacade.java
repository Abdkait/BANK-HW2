package bank.facades;

import bank.domains.BankAccount;
import bank.domains.Category;
import bank.domains.Operation;
import bank.domains.OperationType;
import bank.interfaces.Provider;
import bank.factories.FinanceFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Operation management service
 */
@Component
public class OperationFacade {
    private final Provider<Operation> operationsProvider;
    private final AccountFacade accountFacade;
    private final CategoryFacade categoryFacade;
    private static int defaultIDCounter = 0;

    public OperationFacade(Provider<Operation> operationsProvider, @Lazy AccountFacade accountFacade, CategoryFacade categoryFacade) {
        this.operationsProvider = operationsProvider;
        this.accountFacade = accountFacade;
        this.categoryFacade = categoryFacade;
    }

    public List<Operation> getAllOperations() {
        return new ArrayList<>(operationsProvider.getAll());
    }

    public Operation getOperation(int id) {
        var operation = operationsProvider.get(id);
        if (operation.isEmpty()) {
            throw new IllegalArgumentException("Operation not found.");
        }
        return operation.get();
    }

    public Operation addOperation(Integer id, int accountId, int categoryId, OperationType type, double amount, Date date, String description) {
        BankAccount account = accountFacade.getAccount(accountId);
        Category category = categoryFacade.getCategory(categoryId);
        Operation operation = FinanceFactory.createOperation(id, account, category, type, amount, date, description);
        account.updateBalance(operation.getSignedAmount());
        accountFacade.saveAccount(account);
        return operationsProvider.add(operation);
    }

    public Operation addOperation(int accountId, int categoryId, OperationType type, double amount, Date date, String description) {
        return addOperation(defaultIDCounter++, accountId, categoryId, type, amount, date, description);
    }

    public void saveOperation(Operation operation) {
        operationsProvider.save(operation);
    }

    public void deleteOperation(Operation operation) {
        operationsProvider.delete(operation.getId());
    }

}