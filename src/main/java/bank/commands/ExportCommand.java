package bank.commands;

import bank.domains.BankAccount;
import bank.domains.Category;
import bank.domains.Operation;
import bank.facades.AccountFacade;
import bank.facades.CategoryFacade;
import bank.facades.OperationFacade;
import bank.interfaces.Command;
import bank.persistence.AbstractSerializationService;
import bank.persistence.BankAccountYAMLSerializationService;
import bank.persistence.CategoryYAMLSerializationService;
import bank.persistence.OperationYAMLSerializationService;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class ExportCommand implements Command<Void> {
    private final AccountFacade accountFacade;
    private final CategoryFacade categoryFacade;
    private final OperationFacade operationFacade;
    private final AbstractSerializationService<BankAccount> bankAccountYAMLSerializationService;
    private final AbstractSerializationService<Category> categoryYAMLSerializationService;
    private final AbstractSerializationService<Operation> operationYAMLSerializationService;

    @Override
    public Void execute() {
        try {
            bankAccountYAMLSerializationService.serialize(accountFacade.getAllAccounts(), "accounts.yml");
            categoryYAMLSerializationService.serialize(categoryFacade.getAllCategories(), "categories.yml");
            operationYAMLSerializationService.serialize(operationFacade.getAllOperations(), "operations.yml");
        } catch (IOException e) {
            throw new IllegalArgumentException("Serialization failure: " + e.getMessage());
        }
        return null;
    }
}