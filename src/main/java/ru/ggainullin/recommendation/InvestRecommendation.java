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
public class InvestRecommendation implements RecommendationProvider {

    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    private final String name = "INVEST500";
    private final String investRecommendation =
            """
                    Начните с индивидуальным инвестиционным счетом (ИИС)!
                    Воспользуйтесь налоговыми льготами и начните инвестировать.
                    Пополните счет до конца года и получите налоговый вычет.
                    Не упустите возможность разнообразить портфель и следить за рыночными тенденциями.
                    Откройте ИИС сегодня!
                        """;

    @Override
    public Optional<Recommendation> provideRecommendationForUser(UUID userId) {
        boolean account1 = transactionRepository.ifUserHasRequiredAccount(userId, "INVEST");
        boolean account2 = transactionRepository.ifUserHasRequiredAccount(userId, "DEBIT");
        boolean deposit = transactionRepository.isDepositToSavingAccountLessThanOneThousand(userId);
        if (!account1 && account2 && deposit) {
            List<UUID> uuids = productRepository.listOfProducts("INVEST");
            return Optional.of(new Recommendation(name, uuids.get(0), investRecommendation));
        }
        return Optional.empty();
    }
}
