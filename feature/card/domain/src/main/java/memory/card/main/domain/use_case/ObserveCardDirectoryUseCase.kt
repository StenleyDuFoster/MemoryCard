package memory.card.main.domain.use_case

import memory.card.main.domain.repository.CardRepository
import javax.inject.Inject

class ObserveCardDirectoryUseCase @Inject constructor(
    private val cardRepository: CardRepository,
) {
    operator fun invoke(
        groupId: Long? = null,
    ) = cardRepository.observeCardDirectory(groupId)
}