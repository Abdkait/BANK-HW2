package bank.commands;

import bank.domains.BankAccount;
import bank.domains.Category;
import bank.domains.Operation;
import bank.domains.OperationType;
import bank.facades.AccountFacade;
import bank.facades.CategoryFacade;
import bank.facades.OperationFacade;
import bank.interfaces.Command;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CreateCategoryCommand implements Command<Void> {
    private final CategoryFacade categories;
    private final String name;
    private final OperationType type;

    @Override
    public Void execute() {
        categories.addCategory(name, type);
        return null;
    }
}
