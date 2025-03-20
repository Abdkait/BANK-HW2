package bank.commands;

import bank.domains.Category;
import bank.facades.CategoryFacade;
import bank.interfaces.Command;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListCategoriesCommand implements Command<List<Category>> {
    private final CategoryFacade categories;

    @Override
    public List<Category> execute() {
        return categories.getAllCategories();
    }
}