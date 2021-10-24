package game;

import blackjack.Card;
import blackjack.Deck;
import blackjack.Player;
import util.Alignment;
import util.Vector2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.math.BigDecimal;
import java.util.ArrayList;

public class GameScene {

    private static final BigDecimal BET_INCREMENT = new BigDecimal("10.00");
    private Color sceneBackgroundColor;

    private ArrayList<GameLabel> labels;
    private GameLabel playerMoneyLabel;
    private GameLabel playerBetLabel;
    private GameLabel dealerValueLabel;
    private GameLabel playerValueLabel;
    private GameLabel gameOverLabel;


    private ArrayList<GameButton> buttons;
    private GameButton playButton;
    private GameButton hitButton;
    private GameButton standButton;
    private GameButton decreaseBetButton;
    private GameButton increaseBetButton;

    private Deck mainDeck;
    private Deck dealerDeck;
    private Player player;

    public GameScene() {
        sceneBackgroundColor = new Color(63, 127, 63);

        labels = new ArrayList<>();
        buttons = new ArrayList<>();

        mainDeck = new Deck();
        dealerDeck = new Deck();
        player = new Player();

        mainDeck.generate();

        initLabels();
        initButtons();
    }

    public void update() {

    }

    public void render() {
        Canvas canvas = Game.getInstance().getCanvas();
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(sceneBackgroundColor);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        renderDealerCards(graphics);
        renderPlayerCards(graphics);
        renderLabels(graphics);
        renderButtons(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }

    public void handleMousePress(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            for (GameButton button : buttons) {
                if (button.isEnabled() && button.getBounds().contains(e.getX(), e.getY())) {
                    button.setPressed(true);
                }
            }
        }
    }

    public void handleMouseRelease(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (playButton.isEnabled() && playButton.isPressed() && playButton.getBounds().contains(e.getX(), e.getY()) && !player.isBroke()) {
                start();
            }
            if (decreaseBetButton.isEnabled() && decreaseBetButton.isPressed() && decreaseBetButton.getBounds().contains(e.getX(), e.getY())) {
                player.subtractBet(BET_INCREMENT);
                playerBetLabel.setText("My bet: " + player.getBet());
            }
            if (increaseBetButton.isEnabled() && increaseBetButton.isPressed() && increaseBetButton.getBounds().contains(e.getX(), e.getY())) {
                player.addBet(BET_INCREMENT);
                playerBetLabel.setText("My bet: " + player.getBet());
            }
            if (hitButton.isEnabled() && hitButton.isPressed() && hitButton.getBounds().contains(e.getX(), e.getY())) {
                hit();
            }
            if (standButton.isEnabled() && standButton.isPressed() && standButton.getBounds().contains(e.getX(), e.getY())) {
                stand();
            }

            for (GameButton button : buttons) {
                button.setPressed(false);
            }
        }
    }

    private void initLabels() {
        playerMoneyLabel = new GameLabel("My money: " + player.getMoney());
        playerMoneyLabel.setAnchorPoint(new Vector2(0, 1));
        playerMoneyLabel.setPosition(new Vector2(16, 464));
        playerMoneyLabel.setSize(new Vector2(128, 32));
        playerMoneyLabel.setTextHorizontalAlignment(Alignment.START);
        labels.add(playerMoneyLabel);

        playerBetLabel = new GameLabel("My bet: " + player.getBet());
        playerBetLabel.setAnchorPoint(new Vector2(0, 1));
        playerBetLabel.setPosition(new Vector2(16, 432));
        playerBetLabel.setSize(new Vector2(128, 32));
        playerBetLabel.setTextHorizontalAlignment(Alignment.START);
        labels.add(playerBetLabel);

        dealerValueLabel = new GameLabel();
        dealerValueLabel.setPosition(new Vector2(16, 16));
        dealerValueLabel.setSize(new Vector2(320, 16));
        dealerValueLabel.setTextHorizontalAlignment(Alignment.START);
        labels.add(dealerValueLabel);

        playerValueLabel = new GameLabel();
        playerValueLabel.setAnchorPoint(new Vector2(0, 1));
        playerValueLabel.setPosition(new Vector2(16, 240));
        playerValueLabel.setSize(new Vector2(320, 16));
        playerValueLabel.setTextHorizontalAlignment(Alignment.START);
        labels.add(playerValueLabel);

        gameOverLabel = new GameLabel();
        gameOverLabel.setAnchorPoint(new Vector2(0.5, 1));
        gameOverLabel.setPosition(new Vector2(320, 240));
        gameOverLabel.setSize(new Vector2(640, 32));
        gameOverLabel.setTextSize(32);
        gameOverLabel.setEnabled(false);
        labels.add(gameOverLabel);
    }

    private void initButtons() {
        playButton = new GameButton("Play");
        playButton.setAnchorPoint(new Vector2(1, 1));
        playButton.setPosition(new Vector2(352, 464));
        playButton.setSize(new Vector2(128, 32));
        buttons.add(playButton);

        decreaseBetButton = new GameButton("Decrease Bet");
        decreaseBetButton.setAnchorPoint(new Vector2(1, 1));
        decreaseBetButton.setPosition(new Vector2(488, 464));
        decreaseBetButton.setSize(new Vector2(128, 32));
        buttons.add(decreaseBetButton);

        increaseBetButton = new GameButton("Increase Bet");
        increaseBetButton.setAnchorPoint(new Vector2(1, 1));
        increaseBetButton.setPosition(new Vector2(624, 464));
        increaseBetButton.setSize(new Vector2(128, 32));
        buttons.add(increaseBetButton);

        hitButton = new GameButton("Hit");
        hitButton.setAnchorPoint(new Vector2(1, 1));
        hitButton.setPosition(new Vector2(312, 464));
        hitButton.setSize(new Vector2(128, 32));
        hitButton.setEnabled(false);
        buttons.add(hitButton);

        standButton = new GameButton("Stand");
        standButton.setAnchorPoint(new Vector2(0, 1));
        standButton.setPosition(new Vector2(328, 464));
        standButton.setSize(new Vector2(128, 32));
        standButton.setEnabled(false);
        buttons.add(standButton);
    }

    private void renderDealerCards(Graphics graphics) {
        int x = 0;
        int y = 48;

        for (Card card : dealerDeck.getCards()) {
            card.setPosition(new Vector2(x, y));
            Vector2 absolutePosition = card.getAbsolutePosition();
            graphics.drawImage(card.getImage(), absolutePosition.intX(), absolutePosition.intY(), null);
            x += Card.CARD_SIZE_PX;
        }
    }

    private void renderPlayerCards(Graphics graphics) {
        int x = 0;
        int y = 256;

        for (Card card : player.getDeck().getCards()) {
            card.setPosition(new Vector2(x, y));
            Vector2 absolutePosition = card.getAbsolutePosition();
            graphics.drawImage(card.getImage(), absolutePosition.intX(), absolutePosition.intY(), null);
            x += Card.CARD_SIZE_PX;
        }
    }

    private void renderLabels(Graphics graphics) {
        for (GameLabel label : labels) {
            if (label.isEnabled()) {
                Vector2 absolutePosition = label.getAbsolutePosition();
                graphics.drawImage(label.getImage(), absolutePosition.intX(), absolutePosition.intY(), null);
            }
        }
    }

    private void renderButtons(Graphics graphics) {
        for (GameButton button : buttons) {
            if (button.isEnabled()) {
                Vector2 absolutePosition = button.getAbsolutePosition();
                graphics.drawImage(button.getImage(), absolutePosition.intX(), absolutePosition.intY(), null);
            }
        }
    }

    private void start() {
        gameOverLabel.setEnabled(false);
        player.subtractMoney(player.getBet());
        playerMoneyLabel.setText("My money: " + player.getMoney());

        playButton.setEnabled(false);
        decreaseBetButton.setEnabled(false);
        increaseBetButton.setEnabled(false);

        hitButton.setEnabled(true);
        standButton.setEnabled(true);

        Deck playerDeck = player.getDeck();
        Card firstDealerCard = dealerDeck.getFirstCard();

        if (firstDealerCard != null && firstDealerCard.isFlipped()) {
            firstDealerCard.setFlipped(false);
        }

        dealerDeck.moveTo(mainDeck);
        playerDeck.moveTo(mainDeck);
        mainDeck.shuffle();

        dealerDeck.draw(mainDeck).setFlipped(true);
        playerDeck.draw(mainDeck);
        dealerDeck.draw(mainDeck);
        playerDeck.draw(mainDeck);

        dealerValueLabel.setText("Dealer: " + dealerDeck.calculateValue());
        playerValueLabel.setText("You: " + playerDeck.calculateValue());
    }

    private void hit() {
        Deck playerDeck = player.getDeck();
        playerDeck.draw(mainDeck);
        playerValueLabel.setText("You: " + playerDeck.calculateValue());

        if (playerDeck.calculateValue() > 21) {
            gameOver(GameResult.LOSE);
        }
    }

    private void stand() {
        Deck playerDeck = player.getDeck();
        Card firstDealerCard = dealerDeck.getFirstCard();

        if (firstDealerCard.isFlipped()) {
            firstDealerCard.setFlipped(false);
        }

        int dealerValue = dealerDeck.calculateValue();
        int playerValue = playerDeck.calculateValue();

        while (dealerValue < 17) {
            dealerDeck.draw(mainDeck);
            dealerValue = dealerDeck.calculateValue();
        }

        dealerValueLabel.setText("Dealer: " + dealerValue);

        if (dealerValue > 21 || playerValue > dealerValue) {
            gameOver(GameResult.WIN);
        } else if (playerValue < dealerValue) {
            gameOver(GameResult.LOSE);
        } else {
            gameOver(GameResult.TIE);
        }
    }

    private void gameOver(GameResult gameResult) {
        if (gameResult == GameResult.WIN) {
            gameOverLabel.setText("You win!");
            player.addMoney(player.getBet());
            player.addMoney(player.getBet());

        } else if (gameResult == GameResult.LOSE) {
            gameOverLabel.setText("Dealer wins!");
            player.updateBet();
            playerBetLabel.setText("My bet: " + player.getBet());

        } else {
            gameOverLabel.setText("Tie!");
            player.addMoney(player.getBet());
        }

        gameOverLabel.setEnabled(true);
        playerMoneyLabel.setText("My money: " + player.getMoney());

        playButton.setEnabled(true);
        increaseBetButton.setEnabled(true);
        decreaseBetButton.setEnabled(true);

        hitButton.setEnabled(false);
        standButton.setEnabled(false);
    }

}
