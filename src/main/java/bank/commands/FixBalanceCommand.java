package bank.commands;

import bank.facades.AccountFacade;
import bank.interfaces.Command;
import lombok.RequiredArgsConstructor;

/**
 * Command to fix account balances by operations
 */
@RequiredArgsConstructor
public class FixBalanceCommand implements Command<Void> {
    private final AccountFacade accounts;

    @Override
    public Void execute() {
        accounts.fixBalances();
        return null;
    }
}