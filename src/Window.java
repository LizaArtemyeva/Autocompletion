import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame{
    public final static int FRAME_WIDTH = 700;
    public final static int FRAME_HEIGHT = 550;

    private JPanel options;
    private JLabel instruction;
    private JTextField userText;
    private JButton addButton, deleteButton;
    private Trie trie;
    public static void main(String[] args) {
        new Window();
    }

    //При введении символов в текстовое поле предлагать список возможных завершений слова на основе данных из структуры
    public Window() {
        super("Автодополнение");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(false);

        options = new JPanel();
        options.setLayout(new GridLayout(10,1));
        options.setBounds(100, 160, 300, 250);
        trie = new Trie();
        trie.init();

        userText = new JTextField(15);
        userText.setBounds(100, 100, 300, 50);
        userText.getDocument().addDocumentListener(new TextListener());

        instruction = new JLabel("Enter your text in the field: ");
        instruction.setBounds(100, 60, 300, 50);

        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        addButton.setBounds(410, 95, 100, 60);
        deleteButton.setBounds(515, 95, 100, 60);
        addButton.addActionListener(e -> trie.add(userText.getText()));
        deleteButton.addActionListener(e -> trie.delete(userText.getText()));

        this.add(options);
        this.add(instruction);
        this.add(addButton);
        this.add(deleteButton);
        this.add(userText);
        this.setVisible(true);
    }
    private void update(){
        options.removeAll();
        if(userText.getText().isEmpty()) {
            revalidate();
            repaint();
            return;
        }
        for(String s: trie.getWords(userText.getText().trim())){
            JLabel word = new JLabel(s);
            word.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            word.setBorder(BorderFactory.createLineBorder(Color.black));
            word.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    userText.setText(s);
                }
            });
            options.add(word);
        }
        revalidate();
        repaint();
    }

    private class TextListener implements DocumentListener{
        @Override
        public void insertUpdate(DocumentEvent e) {
            update();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            update();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            update();
        }
    }
}
