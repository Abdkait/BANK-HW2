package bank.domains;

import lombok.ToString;

import java.util.List;
import java.util.Map;

public record AnalyticsResult(double balanceDifference, Map<Category, List<Operation>> groupedOperations) {
}