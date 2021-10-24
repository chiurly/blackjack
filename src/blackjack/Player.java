package blackjack;

import java.math.BigDecimal;

public class Player {

    public static final String STARTING_MONEY = "100.00";
    public static final String DEFAULT_BET = "10.00";

    private BigDecimal money;
    private BigDecimal bet;
    private Deck deck;

    public Player() {
        money = new BigDecimal(STARTING_MONEY);
        bet = new BigDecimal(DEFAULT_BET);
        deck = new Deck();
    }

    public void addMoney(BigDecimal addend) {
        money = money.add(addend);
    }

    public void subtractMoney(BigDecimal subtrahend) {
        money = money.subtract(subtrahend);
    }

    public void addBet(BigDecimal addend) {
        BigDecimal newBet = bet.add(addend);

        if (newBet.compareTo(money) < 0) {
            bet = newBet;
        } else {
            bet = money;
        }
    }

    public void subtractBet(BigDecimal subtrahend) {
        BigDecimal newBet = bet.subtract(subtrahend);

        if (newBet.compareTo(new BigDecimal("0.00")) < 0) {
            bet = new BigDecimal("0.00");
        } else {
            bet = newBet;
        }
    }

    public void updateBet() {
        if (bet.compareTo(money) > 0) {
            bet = money;
        }
    }

    public boolean isBroke() {
        return money.equals(new BigDecimal("0.00"));
    }

    public BigDecimal getMoney() {
        return money;
    }

    public BigDecimal getBet() {
        return bet;
    }

    public Deck getDeck() {
        return deck;
    }

}
