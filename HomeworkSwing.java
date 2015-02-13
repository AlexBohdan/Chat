package week4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by Alex on 09.02.2015.
 */
public class HomeworkSwing {

    private JFrame frame;
    JTextField text;
    JPanel textareaa;
    JButton butsend;
    String[] strArray = {"vasya","albert"};
    ImageIcon[] iconArray = {new ImageIcon("8181_full.jpg")};


    public static void main(String[] args) {
        HomeworkSwing start = new HomeworkSwing();
        String[] k = {"vasya","albert"};
        start.go("Server","9090","127.0.0.1",k);
    }

    public void go(String Servername, String ServerPort,String ServerId,String[] clients){

        frame = new JFrame("Chat");
        //frame.setLayout(null);
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textareaa = new JPanel();
        textareaa.setLayout(new BoxLayout(textareaa,BoxLayout.Y_AXIS));

       //JTextArea textarea = new JTextArea(100,100);
       // textarea.setLineWrap(true);

        JScrollPane scroller = new JScrollPane(textareaa);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //scroller.setSize(800,600);
        //scroller.setLocation(200,0);


        JPanel panelRight = new JPanel();
        panelRight.setLayout(new BorderLayout());
        Font bigFont = new Font("serif",Font.BOLD,22);
        Font smallFont = new Font("serif",Font.BOLD,18);

        JLabel labe = new JLabel("Server:   "+ Servername + "     port:" + ServerPort + "    id:" + ServerId);
        labe.setFont(bigFont);
        JLabel labe1 = new JLabel("Client list:");
        labe1.setFont(smallFont);


        JPanel panelRightNorth = new JPanel();
        panelRightNorth.setLayout(new BoxLayout(panelRightNorth,BoxLayout.Y_AXIS));
        panelRightNorth.add(labe);
        panelRightNorth.add(labe1);

        JList list = new JList(clients);
        JScrollPane scrol = new JScrollPane(list);
        scrol.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        list.setFont(smallFont);

        JButton buttonStart = new JButton("Start Chatting");
        buttonStart.setFont(smallFont);

        panelRight.add(BorderLayout.SOUTH,buttonStart);
        panelRight.add(BorderLayout.NORTH,panelRightNorth);
        panelRight.add(BorderLayout.CENTER,scrol);

        text = new JTextField(30);
        text.setToolTipText("Поле для Ввода");
        text.addKeyListener(new KeyListenerEnter());
        text.setFont(smallFont);


        butsend = new JButton("Send");
        butsend.addActionListener(new ListenerButtonSend());


        butsend.setFont(smallFont);

        JPanel panelcentrsouth = new JPanel();
        panelcentrsouth.setLayout(new BorderLayout());
        panelcentrsouth.add(BorderLayout.CENTER,text);
        panelcentrsouth.add(BorderLayout.EAST,butsend);

        JPanel centrpanel = new JPanel();
        centrpanel.setLayout(new BorderLayout());

        centrpanel.add(BorderLayout.CENTER,scroller);
        centrpanel.add(BorderLayout.SOUTH,panelcentrsouth);


        frame.getContentPane().add(BorderLayout.CENTER, centrpanel);
        frame.getContentPane().add(BorderLayout.EAST,panelRight);

        frame.setVisible(true);
    }

    class ListenerButtonSend implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(text.getText() != ""){
                textareaa.add(new JLabel(text.getText()));
                text.setText("");
                text.requestFocus();
                textareaa.revalidate();
            }
        }
    }

    class KeyListenerEnter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {

            if (e.getKeyChar() == KeyEvent.VK_ENTER){
                if(text.getText() != ""){
                    textareaa.add(new JLabel(text.getText()));
                    text.setText("");
                    textareaa.revalidate();
                }
            }
        }
    }

    class ListBoxRenderer extends JLabel implements ListCellRenderer
    {
        /**
         * author: http://www.sbp-program.ru;
         * ListCellRenderer implementation;
         */
        private static final long serialVersionUID = 1L;
        ImageIcon icon = null;
        String str = null;

        public ListBoxRenderer()
        {
            setOpaque(true);
            setHorizontalAlignment(LEFT);
            setVerticalAlignment(CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean hasFocus)
        {
            int selectedIndex = ((Integer)value).intValue();

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            icon = iconArray[selectedIndex];
            str = strArray[selectedIndex];
            setIcon(icon);
            setText(str);
            return this;
        }
    }
}
