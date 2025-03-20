package bank.commands;

import bank.domains.Operation;
import bank.facades.OperationFacade;
import bank.interfaces.Command;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListOperationsCommand implements Command<List<Operation>> {
    private final OperationFacade operations;

    @Override
    public List<Operation> execute() {
        return operations.getAllOperations();
    }
}