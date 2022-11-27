package javaPack.waveUSB;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
//File copied from JUIT Libraray project. it is an incomplete library and is very buggy, so please do not use it for production. Still curious? visit https://github.com/RishonDev/JUIT
@SuppressWarnings("ALL")
public class QuestionNotification {


    static private short x = 600;
    static private short y = 175;
    static final public int IMAGE_SIZEY =  92;
    static final public int IMAGE_SIZEX =  84;
    static private int response = Integer.parseInt(null);
    static JLabel IconImage = new JLabel("");
    static JFrame frame = new JFrame("Question!");
    static JButton yes = new JButton("yes");
    static JButton no = new JButton();
    static String temp = yes.getText();
    static JLabel QuestionText;
    public QuestionNotification(String Title, int sizeX,int sizeY){
        yes.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                response = 1;
            }
        });
        no.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               response = 0;
           }
        });
        frame.setSize(new Dimension(sizeX,sizeY));
        frame.setTitle(Title);
        frame.setVisible(true);
    }
    public static void setQuestionText(String displayText)
    {
        var QuestionText = new JLabel(displayText);
        QuestionText.setBounds(102, 17, 492, 113);
        frame.getContentPane().add(QuestionText);
    }
    public JFrame getFrame(){
        return frame;
    }
    public static void setIcon(String filePathWithName) {
        IconImage.setIcon(new ImageIcon(Objects.requireNonNull(QuestionNotification.class.getResource(filePathWithName))));
    }
    @SuppressWarnings("SuspiciousNameCombination")
    public static void setSizeOfText(short x, short y) {
        QuestionText.setSize(y, x);
    }
    public static void setTitle(String Title)
    {
        QuestionText.setName(Title);
    }
    public static void setDefaults()
    {
        frame.setBounds(100, 100, 600, 175);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String pathOfFile = "juit/img/warning.png";
        Image icon = Toolkit.getDefaultToolkit().getImage(pathOfFile);
        frame.setIconImage(icon);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        IconImage.setIcon(new ImageIcon(Objects.requireNonNull(QuestionNotification.class.getResource(pathOfFile))));
        IconImage.setBounds(6, 23, 84, 92);
        frame.getContentPane().add(IconImage);
        yes.setBounds(0,0,580,165);
        frame.getContentPane().add(yes);
    }
    public static void setBackgroundColor(Color color)
    {
        frame.setBackground(color);
    }
    public static void setFont(String FontName,String FontType,short size)
    {
        if(FontType.equals("Plain"))QuestionText.setFont(new Font(FontName, Font.PLAIN, size));
        if(FontType.equals("Bold"))QuestionText.setFont(new Font(FontName, Font.BOLD, size));
        if(FontType.equals("Italic"))QuestionText.setFont(new Font(FontName, Font.ITALIC, size));

    }
    public static void setTitleIcon(String pathOfFile)
    {
        Image icon = Toolkit.getDefaultToolkit().getImage(pathOfFile);
        frame.setIconImage(icon);
    }
    public static void setButtonMessage(String buttonMessage)
    {
        yes.setText(buttonMessage);
    }
    public static void setButtonSize(Dimension dim)
    {
        yes.setPreferredSize(dim);
    }
    public static void setPositionOfYesButton(short x,short y)
    {
        yes.setLocation(x,y);
    }
    public static void setPositionOfNoButton(short x,short y)
    {
        no.setLocation(x,y);
    }
    public void setSizeOfWindow(short x, short y)
    {
        this.x = x;
        this.y = y;
        frame.setSize(this.x,this.y);
    }
    public static short getXOfWindow()
    {
        return x;
    }
    public static short getYOfWindow()
    {
        return y;
    }
    public static short getYOfYesButton()
    {
        return (short) 175;
    }
    public static short getXOfYesButton()
    {
        return (short) 600;
    }
    public static short getYOfNokButton()
    {
        return (short) 175;
    }
    public static short getXOfNoButton()
    {
        return (short) 600;
    }
    public static void addNoButton()
    {
        no.setBounds(100, 100, 590, 175);
        frame.add(no);

    }
    public static void setButtonYesFont(String FontName,String FontType,short size)
    {
        if(FontType.equals("Plain"))yes.setFont(new Font(FontName, Font.PLAIN, size));
        if(FontType.equals("Bold"))yes.setFont(new Font(FontName, Font.BOLD, size));
        if(FontType.equals("Italic"))yes.setFont(new Font(FontName, Font.ITALIC, size));
    }
    public static void setButtonNoFont(String FontName,String FontType,short size)
    {
        if(FontType.equals("Plain"))no.setFont(new Font(FontName, Font.PLAIN, size));
        if(FontType.equals("Bold"))no.setFont(new Font(FontName, Font.BOLD, size));
        if(FontType.equals("Italic"))no.setFont(new Font(FontName, Font.ITALIC, size));
    }
    public static void setVisibility(boolean isVisible){frame.setVisible(isVisible);}
    public static void trimTextOfNoButton()
    {
        no.setText(no.getText().trim());
    }
    public static void trimTextOfYesButton()
    {
        yes.setText(yes.getText().trim());
    }
    public int getResponse(){
        return this.response;
    }



}
