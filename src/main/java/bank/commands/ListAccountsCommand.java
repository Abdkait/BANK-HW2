package bank.commands;

import bank.domains.BankAccount;
import bank.domains.Category;
import bank.domains.Operation;
import bank.facades.AccountFacade;
import bank.facades.CategoryFacade;
import bank.facades.OperationFacade;
import bank.interfaces.Command;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListAccountsCommand implements Command<List<BankAccount>> {
    private final AccountFacade accounts;

    @Override
    public List<BankAccount> execute() {
        return accounts.getAllAccounts();
    }
}