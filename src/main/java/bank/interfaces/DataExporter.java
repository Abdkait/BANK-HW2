package bank.interfaces;

import bank.domains.BankAccount;
import bank.domains.Category;
import bank.domains.Operation;

public interface DataExporter {
    void export(BankAccount account);
    void export(Category category);
    void export(Operation operation);
}
