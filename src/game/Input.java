package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements MouseListener {

    public Input() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        GameScene scene = Game.getInstance().getScene();
        scene.handleMousePress(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        GameScene scene = Game.getInstance().getScene();
        scene.handleMouseRelease(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
