import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private JPanel pane;
    private JLabel label;
    private JButton button;
    public static final int CHAR_WIDTH= 8;

    public MainFrame(){
        super("Drag and drop button");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(200, 200, 550, 550);
        label = new JLabel();
        this.setLayout(new BorderLayout());
        this.add(label, BorderLayout.SOUTH);
        pane = new JPanel();
        pane.setLayout(null);
        this.add(pane, BorderLayout.CENTER);
        button = new JButton("Button");
        pane.add(button);
        button.setBounds(20, 20, 80, 20);
        pane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                button.setLocation(e.getPoint());
                label.setText("X: " + e.getX() + "; Y: " + e.getY());
            }
        });
        pane.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                label.setText("X: " + e.getX() + "; Y: " + e.getY());
            }
            public void mouseDragged(MouseEvent e){
                label.setText("X: " + e.getX() + "; Y: " + e.getY());
            }
        });
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                label.setText("X: " + pane.getMousePosition().x + "; Y: " + pane.getMousePosition().y);
            }
        });
        button.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                try {
                    super.mouseDragged(e);
                    if (e.isControlDown()) {
                        button.setLocation(pane.getMousePosition());
                    }
                    label.setText("X: " + pane.getMousePosition().x + "; Y: " + pane.getMousePosition().y);
                } catch (Exception exception){};
            }
            public void mouseMoved(MouseEvent e){
                super.mouseMoved(e);
                label.setText("X: " + pane.getMousePosition().x + "; Y: " + pane.getMousePosition().y);
            }
        });
        button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE){
                    if(!button.getText().isEmpty()) {
                        button.setText(button.getText().substring(0, button.getText().length() - 1));
                        if(button.getSize().width > 70) {
                            button.setSize(button.getWidth() - MainFrame.CHAR_WIDTH, button.getHeight());
                            button.setBounds(button.getBounds().x + MainFrame.CHAR_WIDTH/2, button.getBounds().y, button.getBounds().width, button.getBounds().height);
                        }
                    }
                } else {
                    button.setSize(button.getWidth() + MainFrame.CHAR_WIDTH, button.getHeight());
                    button.setBounds(button.getBounds().x - MainFrame.CHAR_WIDTH/2, button.getBounds().y, button.getBounds().width, button.getBounds().height);
                    button.setText(button.getText() + e.getKeyChar());
                }
            }
        });
    }

    public static void main(String[] args){
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}
