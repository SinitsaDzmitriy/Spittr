package edu.sam.aveng.base.converter;

import edu.sam.aveng.base.contract.converter.IShortConverter;
import edu.sam.aveng.base.model.domain.Card;
import edu.sam.aveng.base.model.domain.UserCard;
import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.model.transfer.dto.ShortCardDto;
import edu.sam.aveng.base.model.transfer.dto.UserCardDto;
import edu.sam.aveng.base.model.transfer.dto.UserCardTableItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Stream;

@Component
public class UserCardConverter implements IShortConverter<UserCard, UserCardDto, UserCardTableItem> {

    private IShortConverter<Card, CardDto, ShortCardDto> cardConverter;

    @Autowired
    @Qualifier("cardConverter")
    public void setCardConverter(IShortConverter<Card, CardDto, ShortCardDto> cardConverter) {
        this.cardConverter = cardConverter;
    }

    @Override
    public Stream<UserCardDto> convertToDto(Collection<UserCard> userCards) {
        return userCards.stream()
                .map(this::convertToDto);
    }

    @Override
    public Stream<UserCard> convertToEntity(Collection<UserCardDto> dtos) {
        return null;
    }

    @Override
    public UserCardDto convertToDto(UserCard userCard) {

        UserCardDto userCardDto = new UserCardDto();

        userCardDto.setId(userCard.getId());

        userCardDto.setFavorite(userCard.isFavorite());
        userCardDto.setStatus(userCard.getStatus());
        userCardDto.setUserSample(userCard.getUserSample());

        userCardDto.setCard(cardConverter.convertToDto(userCard.getCard()));

        return userCardDto;
    }

    @Override
    public UserCard convertToEntity(UserCardDto dto) {
        return null;
    }

    @Override
    public Stream<UserCardTableItem> convertToShortDto(Collection<UserCard> userCards) {
        return userCards.stream()
                .map(userCard -> {
                    UserCardTableItem item = new UserCardTableItem();

                    item.setUserCardId(userCard.getId());
                    item.setStatus(userCard.getStatus());
                    item.setLang(userCard.getCard().getLang());
                    item.setContent(userCard.getCard().getContent());
                    item.setType(userCard.getCard().getType());
                    item.setDefinition(userCard.getCard().getDefinition());


                    return item;
                });
    }
}
