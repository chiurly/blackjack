package game;

import util.Alignment;
import util.Vector2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GameLabel extends GameObject {

    private String text;
    private int textSize;
    private Alignment textHorizontalAlignment;
    private Alignment textVerticalAlignment;
    private Color textColor;
    private Color backgroundColor;
    private boolean enabled;

    public GameLabel() {
        this("");
    }

    public GameLabel(String text) {
        this.text = text;
        textSize = 16;
        textHorizontalAlignment = Alignment.CENTER;
        textVerticalAlignment = Alignment.CENTER;
        textColor = Color.WHITE;
        backgroundColor = new Color(0, 0, 0, 0);
        enabled = true;
    }

    @Override
    public void update() {

    }

    @Override
    public BufferedImage getImage() {
        BufferedImage image = new BufferedImage(getSize().intX(), getSize().intY(), BufferedImage.TYPE_INT_ARGB);

        Graphics graphics = image.createGraphics();
        graphics.setFont(new Font("Trebuchet MS", Font.PLAIN, textSize));

        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, getSize().intX(), getSize().intY());

        int textWidth = graphics.getFontMetrics().stringWidth(text);
        Vector2 alignedTextPosition = getAlignedTextPosition(textWidth);

        graphics.setColor(textColor);
        graphics.drawString(text, alignedTextPosition.intX(), alignedTextPosition.intY());

        graphics.dispose();

        return image;
    }

    public String getText() {
        return text;
    }

    public int getTextSize() {
        return textSize;
    }

    public Alignment getTextHorizontalAlignment() {
        return textHorizontalAlignment;
    }

    public Alignment getTextVerticalAlignment() {
        return textVerticalAlignment;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setTextHorizontalAlignment(Alignment textHorizontalAlignment) {
        this.textHorizontalAlignment = textHorizontalAlignment;
    }

    public void setTextVerticalAlignment(Alignment textVerticalAlignment) {
        this.textVerticalAlignment = textVerticalAlignment;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private Vector2 getAlignedTextPosition(int textWidth) {
        int x = 0;
        int y = textSize;

        if (textHorizontalAlignment == Alignment.CENTER) {
            x = getSize().intX() / 2 - textWidth / 2;
        } else if (textHorizontalAlignment == Alignment.END) {
            x = getSize().intX() - textWidth;
        }

        if (textVerticalAlignment == Alignment.CENTER) {
            y = getSize().intY() / 2 + textSize / 2;
        } else if (textVerticalAlignment == Alignment.END) {
            y = getSize().intY();
        }

        return new Vector2(x, y);
    }

}
