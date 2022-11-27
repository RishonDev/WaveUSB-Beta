package javaPack.waveUSB;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

//File copied from JUIT Libraray project. it is an incomplete library and is very buggy, so please do not use it for production. Still curious? visit https://github.com/RishonDev/JUIT
@SuppressWarnings("ALL")
public class ErrorNotification {

	private short x = 600;
	private short y = 175;
	final private int IMAGE_SIZEY =  92;
	final private int IMAGE_SIZEX =  84;

	private static JLabel IconImage = new JLabel("");
	private static JFrame frame;
	private static JButton ok = new JButton("Ok");
	private String temp = ok.getText();
	private static JButton exit = new JButton();
	private String temp2 = exit.getText();
	private int response = 0;

	private static JLabel ErrorText;
	public JFrame getFrame() {
		return frame;
	}
	public ErrorNotification(String Title,int x,int y)
	{
		frame = new JFrame(Title);
		frame.setSize(x,y);
	}
	public void SetErrorText(String displayText)
	{
		var ErrorText = new JLabel(displayText);
		ErrorText.setBounds(102, 17, 492, 113);
		frame.getContentPane().add(ErrorText);
	}
	public static void SetIcon(String filePathWithName) {
		IconImage.setIcon(new ImageIcon(ErrorNotification.class.getResource(filePathWithName)));
	}
	public static void setSizeOfText(short x,short y) {
		ErrorText.setSize(y, x);
	}
	public static void setTitle(String Title)
	{
		ErrorText.setName(Title);
	}
	public static void setDefaults()
	{
		String pathOfFile = "juit/img/error.png";
		frame.setBounds(100, 100, 600, 175);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image icon = Toolkit.getDefaultToolkit().getImage(pathOfFile);
		frame.setIconImage(icon);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		IconImage.setIcon(new ImageIcon(Objects.requireNonNull(ErrorNotification.class.getResource(pathOfFile))));
		IconImage.setBounds(6, 23, 84, 92);
		frame.getContentPane().add(IconImage);
		ok.setBounds(0,0,580,165);
		frame.getContentPane().add(ok);
	}
	public static void setBackgroundColor(Color color)
	{
		frame.setBackground(color);
	}
	public static void setFont(String FontName,String FontType,short size)
	{
		if(FontType.equals("Plain"))ErrorText.setFont(new Font(FontName, Font.PLAIN, size));
		if(FontType.equals("Bold"))ErrorText.setFont(new Font(FontName, Font.BOLD, size));
		if(FontType.equals("Italic"))ErrorText.setFont(new Font(FontName, Font.ITALIC, size));

	}
	public static void setTitleIcon(String pathOfFile)
	{
		Image icon = Toolkit.getDefaultToolkit().getImage(pathOfFile);
		frame.setIconImage(icon);
	}
	public static void setButtonMessage(String buttonMessage)
	{
		ok.setText(buttonMessage);
	}
	public static void setButtonSize(Dimension dim)
	{
		ok.setPreferredSize(dim);
	}
	public static void setPositionOfOkButton(short x,short y)
	{
		ok.setLocation(x,y);
	}
	public static void setPositionOfCancelButton(short x,short y)
	{
		exit.setLocation(x,y);
	}
	public void setSizeOfWindow(short x, short y)
	{
		this.x = x;
		this.y = y;
		frame.setSize(this.x,this.y);
	}
	public short getXOfWindow()
	{
		return x;
	}
	public short getYOfWindow()
	{
		return y;
	}
	public static short getYOfOkButton()
	{
		short buttony = 175;
		return buttony;
	}
	public static short getXOfOkButton()
	{
		short buttonx = 600;
		return buttonx;
	}
	public static short getYOfCancelkButton()
	{
		short cancelButtony = 175;
		return cancelButtony;
	}
	public static short getXOfCancelButton()
	{
		short cancelButtonx = 600;
		return cancelButtonx;
	}
	public static void AddCancelButton()
	{
		exit.setBounds(100, 100, 590, 175);
		frame.add(exit);

	}
	public static void setButtonOkFont(String FontName,String FontType,short size)
	{
		if(FontType.equals("Plain"))ok.setFont(new Font(FontName, Font.PLAIN, size));
		if(FontType.equals("Bold"))ok.setFont(new Font(FontName, Font.BOLD, size));
		if(FontType.equals("Italic"))ok.setFont(new Font(FontName, Font.ITALIC, size));
	}
	public static void setButtonCancelFont(String FontName,String FontType,short size)
	{
		if(FontType.equals("Plain"))exit.setFont(new Font(FontName, Font.PLAIN, size));
		if(FontType.equals("Bold"))exit.setFont(new Font(FontName, Font.BOLD, size));
		if(FontType.equals("Italic"))exit.setFont(new Font(FontName, Font.ITALIC, size));
	}

	public static void trimTextOfCancelButton()
	{
		exit.setText(exit.getText().trim());
	}
	public static void trimTextOfOkButton()
	{
		ok.setText(ok.getText().trim());
	}
	public int getResponse() {
		return this.response;
	}
	public static void setVisibility(boolean visible){

	}
}
