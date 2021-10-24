package blackjack;

import game.GameObject;

import java.awt.image.BufferedImage;

public class Card extends GameObject {

    public static final int CARD_SIZE_PX = 128;

    private CardSuit suit;
    private CardValue value;
    private BufferedImage image;
    private boolean flipped;

    public Card(CardSuit suit, CardValue value, BufferedImage image) {
        this.suit = suit;
        this.value = value;
        this.image = image;
        flipped = false;
    }

    @Override
    public void update() {

    }

    @Override
    public BufferedImage getImage() {
        if (flipped) {
            if (suit == CardSuit.CLUB || suit == CardSuit.HEART) {
                return Deck.getFlippedBlack();
            } else {
                return Deck.getFlippedRed();
            }
        } else {
            return image;
        }
    }

    public CardSuit getSuit() {
        return suit;
    }

    public CardValue getValue() {
        return value;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }
}
