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
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class CreateOperationCommand implements Command<Void> {
    private final OperationFacade operations;
    private final int accountId;
    private final int categoryId;
    private final OperationType type;
    private final double amount;
    private final String description;

    @Override
    public Void execute() {
        operations.addOperation(accountId, categoryId, type, amount, new Date(), description);
        return null;
    }
}
