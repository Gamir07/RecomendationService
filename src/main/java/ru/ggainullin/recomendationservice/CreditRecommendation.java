package ru.ggainullin.recomendationservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ggainullin.repository.ProductRepository;
import ru.ggainullin.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreditRecommendation implements RecommendationProvider {

    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    private final String name = "Простой кредит";
    private final String creditRecommendation =
            """
                    Получите выгодный кредит с нами! Быстрый процесс, низкие ставки, гибкие условия.
                    Быстрое рассмотрение заявки.
                    Удобное оформление онлайн или через приложение.
                    Разнообразие кредитных продуктов: на недвижимость, авто, образование и т.д.
                    Воспользуйтесь выгодными условиями кредитования!
                         """;

    @Override
    public Optional<Recommendation> provideRecommendationForUser(UUID userId) {
        boolean requiredAccount = transactionRepository.ifUserHasRequiredAccount(userId, "CREDIT");
        boolean requiredExpenses = transactionRepository.ifTotalExpensesFromDebitAccountMoreThanHundredThousand(userId);
        Integer totalDeposit = transactionRepository.totalAmountOfDepositToDebitAccount(userId);
        Integer totalExpenses = transactionRepository.totalAmountOfExpensesFromDebitAccount(userId);

        if (!requiredAccount && requiredExpenses) {
            if (totalDeposit > totalExpenses) {
                List<UUID> uuids = productRepository.listOfProducts("CREDIT");
                return Optional.of(new Recommendation(name, uuids.get(0), creditRecommendation));
            }
        }
        return Optional.empty();
    }
}
