package ru.ggainullin.recommendation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ggainullin.repository.ProductRepository;
import ru.ggainullin.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SavingRecommendation implements RecommendationProvider {

    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;

    private final String name = "Top Saving";
    private final String savingRecommendation = """
            Создайте свою «Копилку» с нашим банком! «Копилка» поможет вам удобно накапливать на важные цели. Больше никаких забытых чеков — всё под контролем!            
            Преимущества «Копилки»:            
            Накопление на конкретные цели. Установите лимит и срок накопления.
            Прозрачность и контроль. Отслеживайте доходы и расходы, корректируйте стратегию.
            Безопасность и надежность. Ваши средства под защитой банка.
            Начните использовать «Копилку» уже сегодня!
            """;

    @Override
    public Optional<Recommendation> provideRecommendationForUser(UUID userId) {
        boolean account = transactionRepository.ifUserHasRequiredAccount(userId, "DEBIT");
        Integer totalDeposit = transactionRepository.totalAmountOfDepositToDebitAccount(userId);
        Integer totalExpenses = transactionRepository.totalAmountOfExpensesFromDebitAccount(userId);
        int quantityOfDeposits = transactionRepository.quantityOfDepositToSavingOrDebitAccount(userId);

        if (account) {
            if (totalDeposit > totalExpenses && quantityOfDeposits > 5){
                List<UUID> uuids = productRepository.listOfProducts("SAVING");
                return Optional.of(new Recommendation(name, uuids.get(0), savingRecommendation));
            }
        }
        return Optional.empty();
    }
}
