package bank.persistence;

import bank.domains.Category;
import bank.domains.OperationType;
import bank.facades.CategoryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryCSVSerializationService extends CsvSerializerService<Category> {
    private final CategoryFacade categoryFacade;

    @Override
    protected String serializeItem(Category category) {
        return category.getId() + delimiter + category.getName() + delimiter + category.getType().toString();
    }

    @Override
    protected Category deserializeItem(String data) {
        String[] parts = data.split(delimiter);
        try {
            return categoryFacade.addCategory(Integer.parseInt(parts[0]), parts[1], OperationType.valueOf(parts[2]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid arguments for deserialization: " + e.getMessage());
        }
    }
}