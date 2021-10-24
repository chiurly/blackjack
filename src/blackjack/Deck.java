package blackjack;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Deck {

    private static BufferedImage flippedBlack;
    private static BufferedImage flippedRed;
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
    }

    public void generate() {
        try {
            int cardSizePX = Card.CARD_SIZE_PX;
            BufferedImage flippedSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/flipped.png")));
            BufferedImage cardSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/cards.png")));

            flippedBlack = flippedSheet.getSubimage(0, 0, cardSizePX, cardSizePX);
            flippedRed = flippedSheet.getSubimage(cardSizePX, 0, cardSizePX, cardSizePX);

            int x = 0;
            int y = 0;

            for (CardSuit cardSuit : CardSuit.values()) {
                for (CardValue cardValue : CardValue.values()) {
                    BufferedImage cardImage = cardSheet.getSubimage(x, y, cardSizePX, cardSizePX);
                    cards.add(new Card(cardSuit, cardValue, cardImage));
                    x += cardSizePX;
                }

                x = 0;
                y += cardSizePX;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw(Deck deckToDrawFrom) {
        Card card = deckToDrawFrom.getFirstCard();
        deckToDrawFrom.removeCard(0);
        addCard(card);
        return card;
    }

    public void moveTo(Deck deckToMoveTo) {
        for (Card card : cards) {
            deckToMoveTo.addCard(card);
        }
        cards.clear();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(int i) {
        cards.remove(i);
    }

    public int calculateValue() {
        int totalValue = 0;
        int aceCount = 0;

        for (Card card : cards) {
            if (card.isFlipped()) {
                continue;
            }

            CardValue cardValue = card.getValue();

            if (cardValue == CardValue.TWO) {
                totalValue += 2;
            } else if (cardValue == CardValue.THREE) {
                totalValue += 3;
            } else if (cardValue == CardValue.FOUR) {
                totalValue += 4;
            } else if (cardValue == CardValue.FIVE) {
                totalValue += 5;
            } else if (cardValue == CardValue.SIX) {
                totalValue += 6;
            } else if (cardValue == CardValue.SEVEN) {
                totalValue += 7;
            } else if (cardValue == CardValue.EIGHT) {
                totalValue += 8;
            } else if (cardValue == CardValue.NINE) {
                totalValue += 9;
            } else if (cardValue == CardValue.TEN || cardValue == CardValue.JACK || cardValue == CardValue.QUEEN || cardValue == CardValue.KING) {
                totalValue += 10;
            } else if (cardValue == CardValue.ACE) {
                aceCount++;
            }
        }

        for (int i = 0; i < aceCount; i++) {
            if (totalValue <= 10) {
                totalValue += 11;
            } else {
                totalValue++;
            }
        }

        return totalValue;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card getFirstCard() {
        if (cards.size() == 0) {
            return null;
        }
        return cards.get(0);
    }

    public static BufferedImage getFlippedRed() {
        return flippedRed;
    }

    public static BufferedImage getFlippedBlack() {
        return flippedBlack;
    }

}
