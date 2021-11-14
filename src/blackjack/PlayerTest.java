package blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    private static final BigDecimal BIG_DECIMAL_ZERO = new BigDecimal("0.00");
    private static final BigDecimal BIG_DECIMAL_ONE = new BigDecimal("1.00");
    Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
    }

    @Test
    void testAddBetAffordability() {
        BigDecimal betToAdd = player.getMoney().add(BIG_DECIMAL_ONE);
        player.addBet(betToAdd);
        assertTrue(player.getBet().compareTo(player.getMoney()) <= 0, "Adding bet should not result in a higher bet than available money");
    }

    @Test
    void testAddBetLimit() {
        BigDecimal betToAdd = new BigDecimal(player.MAX_BET).add(BIG_DECIMAL_ONE);
        player.addMoney(betToAdd);
        player.addBet(betToAdd);
        assertTrue(player.getBet().compareTo(new BigDecimal(player.MAX_BET)) <= 0, "Adding bet should not result in a higher bet than maximum bet");
    }

    @Test
    void testSubtractBet() {
        BigDecimal betToSubtract = player.getBet().add(BIG_DECIMAL_ONE);
        player.subtractBet(betToSubtract);
        assertTrue(player.getBet().compareTo(BIG_DECIMAL_ZERO) >= 0, "Subtracting bet should not result in negative bet");
    }

    @Test
    void testAddMoneyNull() {
        try {
            player.addMoney(null);
        } catch(Exception exception) {
            exception.printStackTrace();
            System.out.println("Adding money with null should not result in a error");
        }
    }

    @Test
    void testSubtractMoneyNull() {
        try {
            player.subtractMoney(null);
        } catch(Exception exception) {
            exception.printStackTrace();
            System.out.println("Subtracting money with null should not result in a error");
        }
    }

    @Test
    void testAddBetNull() {
        try {
            player.addBet(null);
        } catch(Exception exception) {
            exception.printStackTrace();
            System.out.println("Adding bet with null should not result in a error");
        }
    }

    @Test
    void testSubtractBetNull() {
        try {
            player.subtractBet(null);
        } catch(Exception exception) {
            exception.printStackTrace();
            System.out.println("Subtracting bet with null should not result in a error");
        }
    }

}
