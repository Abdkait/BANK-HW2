package bank.commands;

import bank.domains.BankAccount;
import bank.domains.Category;
import bank.domains.Operation;
import bank.interfaces.Command;
import bank.persistence.AbstractSerializationService;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class ImportCommand implements Command<Void> {
    private final AbstractSerializationService<BankAccount> bankAccountYAMLSerializationService;
    private final AbstractSerializationService<Category> categoryYAMLSerializationService;
    private final AbstractSerializationService<Operation> operationYAMLSerializationService;

    @Override
    public Void execute() {
        try {
            bankAccountYAMLSerializationService.deserialize("accounts.yml");
            categoryYAMLSerializationService.deserialize("categories.yml");
            operationYAMLSerializationService.deserialize("operations.yml");
        } catch (IOException e) {
            throw new IllegalArgumentException("Deserialization failure: " + e.getMessage());
        }
        return null;
    }
}