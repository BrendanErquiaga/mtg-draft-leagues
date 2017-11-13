package org.rd.draftleague.core.utils;

import org.rd.draftleague.core.model.Card;

public class RequestClasses {

    public static class DraftedCardRequestObject {
        public Long drafterId;
        public Card draftedCard;

        public DraftedCardRequestObject(Long drafterId, Card draftedCard) {
            this.drafterId = drafterId;
            this.draftedCard = draftedCard;
        }

        public DraftedCardRequestObject() {

        }

        public Long getDrafterId() {
            return drafterId;
        }

        public void setDrafterId(Long drafterId) {
            this.drafterId = drafterId;
        }

        public Card getDraftedCard() {
            return draftedCard;
        }

        public void setDraftedCard(Card draftedCard) {
            this.draftedCard = draftedCard;
        }
    }
}
