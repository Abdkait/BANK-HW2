package bank.factories;

import bank.commands.*;
import bank.domains.OperationType;
import bank.facades.AccountFacade;
import bank.facades.AnalyticsFacade;
import bank.facades.CategoryFacade;
import bank.facades.OperationFacade;
import bank.interfaces.Command;
import bank.persistence.BankAccountYAMLSerializationService;
import bank.persistence.CategoryYAMLSerializationService;
import bank.persistence.OperationYAMLSerializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandFactory {
    private final AnalyticsFacade analytics;
    private final AccountFacade accounts;
    private final CategoryFacade categories;
    private final OperationFacade operations;
    private final BankAccountYAMLSerializationService bankAccountYAMLSerializationService;
    private final CategoryYAMLSerializationService categoryYAMLSerializationService;
    private final OperationYAMLSerializationService operationYAMLSerializationService;

    public AnalyticsCommand createAnalyticsCommand(Date startDate, Date endDate) {
        return new AnalyticsCommand(analytics, startDate, endDate);
    }

    public FixBalanceCommand createFixBalanceCommand() {
        return new FixBalanceCommand(accounts);
    }

    public SetBalanceCommand createSetBalanceCommand(int id, double balance) {
        return new SetBalanceCommand(accounts, id, balance);
    }

    public CreateAccountCommand createCreateAccountCommand(String name) {
        return new CreateAccountCommand(accounts, name);
    }

    public CreateCategoryCommand createCreatCategoryCommand(String name, OperationType type) {
        return new CreateCategoryCommand(categories, name, type);
    }

    public CreateOperationCommand createCreateOperationCommand(int accountId, int categoryId, OperationType type, double amount, String description) {
        return new CreateOperationCommand(operations, accountId, categoryId, type, amount, description);
    }

    public ExportCommand createExportCommand() {
        return new ExportCommand(accounts, categories, operations, bankAccountYAMLSerializationService, categoryYAMLSerializationService, operationYAMLSerializationService);
    }

    public ImportCommand createImportCommand() {
        return new ImportCommand(bankAccountYAMLSerializationService, categoryYAMLSerializationService, operationYAMLSerializationService);
    }

    public <T> TimedCommandDecorator<T> makeTimed(Command<T> command) {
        return new TimedCommandDecorator<>(command);
    }

    public ListOperationsCommand createListOperationsCommand() {
        return new ListOperationsCommand(operations);
    }

    public ListCategoriesCommand createListCategoriesCommand() {
        return new ListCategoriesCommand(categories);
    }

    public ListAccountsCommand createListAccountsCommand() {
        return new ListAccountsCommand(accounts);
    }
}