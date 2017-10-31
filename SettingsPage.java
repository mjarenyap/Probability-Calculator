import java.util.*;
import java.time.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.event.*;

// fix totalCount

//int totalCount
public class SettingsPage
{
	public SettingsPage()
	{
		grey = new Color(89, 95, 99);
		lightGrey = new Color(229, 229, 229);
		red = new Color(246, 61, 67);
		white = new Color(229, 229, 229);

		totalFont = new Font("Montserrat", Font.BOLD, 12);
		countFont = new Font("Montserrat", Font.BOLD, 57);

		setMainPanel();
		setIconLabel();
		setTotalLabel();
		setCountLabel(); //totalCount

		combineAll();
	}

	public void combineAll()
	{
		mainPanel.add(total1);
		mainPanel.add(total2);
		mainPanel.add(count);
		mainPanel.add(iconLabel);

	}

	public void setMainPanel()
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 47, 320, 520);
		mainPanel.setBackground(lightGrey);
	}

	public void setIconLabel()
	{
		iconLabel = new JLabel(new ImageIcon("images/Dish.png"));
		iconLabel.setBounds(75, 40, 180, 150);
		iconLabel.setBackground(lightGrey);

	}

	public void setTotalLabel()
	{
		total1 = new JLabel("TOTAL FOOD");
		total2 = new JLabel("IN THE INVENTORY");

		total1.setForeground(grey);
		total1.setFont(totalFont);
		total1.setBounds(125, 195, 100, 15);
		total1.setBackground(lightGrey);

		total2.setForeground(grey);
		total2.setFont(totalFont);
		total2.setBounds(105, 210, 150, 15);
		total2.setBackground(lightGrey);
	}

	public void setCountLabel()
	{	//int totalCount

		count = new JTextField("0");
		count.setBounds(0, 230, 320, 70);
		count.setFont(countFont);
		count.setForeground(red);
		count.setBackground(lightGrey);
        count.setBorder(null);
        count.setEditable(false);
        count.setHorizontalAlignment(JTextField.CENTER);
	}
    
    public void setTotalCount(int total)
    {
        count.setText(Integer.toString(total));
    }

	public JPanel getMainPanel()
	{
		return mainPanel;
	}

	public JPanel mainPanel;

	private JLabel iconLabel;
	private JLabel total1;
	private JLabel total2;
	private JTextField count;

	private Font totalFont;
	private Font countFont;

	private Color grey;
	private Color lightGrey;
	private Color red;
	private Color white;
}