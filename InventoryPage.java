import java.util.*;
import java.time.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.event.*;


public class InventoryPage
{
	public InventoryPage(String[] names)
	{
		darkGrey = new Color(72, 73, 76);
		lightGrey = new Color(229, 229, 229);
		grey = new Color(206, 206, 206);
		red = new Color(246, 61, 67);
		white = new Color(229, 229, 229);

		nameFont = new Font("Montserrat", Font.BOLD, 11);
		stockFont = new Font("Montserrat", Font.PLAIN, 11);
		border = new LineBorder(grey, 2);

		setMainPanel();
		setName(names);
        setUpdateLabel();
		setStockFields();
		setIconLabels(names);

		combineAll();
	}

	public void combineAll()
	{
        mainPanel.add(update);
		for(int i = 0; i < nameLabel.size(); i++)
		{	
			mainPanel.add(iconLabels.get(i));
			mainPanel.add(nameLabel.get(i));
			mainPanel.add(stockFields.get(i));
		}

	}

	public void setMainPanel()
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 47, 320, 500);
		mainPanel.setBackground(lightGrey);
	}

	public void setName(String[] names)
	{
		nameLabel = new ArrayList<JLabel>();

		int y = 30;

		for(int i = 0; i < names.length; i++)
		{	
			y += 35;
			nameLabel.add(new JLabel("                 " + names[i]));
			nameLabel.get(i).setFont(nameFont);
			nameLabel.get(i).setBackground(grey);
			nameLabel.get(i).setForeground(darkGrey);
			nameLabel.get(i).setOpaque(true);
			nameLabel.get(i).setBounds(45, y, 120, 30);
		}
	}

	public void setStockFields()
	{
		stockFields = new ArrayList<JTextField>();

		int y = 30;

		for(int i = 0; i < 10; i++)
		{
			y += 35;
			stockFields.add(new JTextField("0"));
			stockFields.get(i).setBounds(165, y, 110, 30);
			stockFields.get(i).setBorder(border);
			stockFields.get(i).setHorizontalAlignment(SwingConstants.CENTER);
			stockFields.get(i).setFont(nameFont);
			stockFields.get(i).setForeground(darkGrey);
			stockFields.get(i).setBackground(lightGrey);
			stockFields.get(i).setVisible(true);
			stockFields.get(i).setOpaque(true);
		}
	}
    
    public void setUpdateLabel()
    {
        update = new JLabel("Update your inventory");
        update.setBounds(0, 5, 320, 70);
        update.setFont(new Font("Montserrat", Font.BOLD, 13));
        update.setBorder(null);
        update.setForeground(red);
        update.setHorizontalAlignment(SwingConstants.CENTER);
    }

	public void setIconLabels(String[] names)
	{
		iconLabels = new ArrayList<JLabel>();

		int y = 30;

		for(int i = 0; i < 10; i++)
		{
			y += 35;

			iconLabels.add(new JLabel());
			iconLabels.get(i).setBounds(50, y, 50, 30);
			iconLabels.get(i).setIcon(new ImageIcon("images/" + names[i] + "-colored.png"));
			iconLabels.get(i).setVisible(true);
		}
	}

	public JPanel getMainPanel()
	{
		return mainPanel;
	}
    
    public ArrayList<JTextField> getStockFields()
    {
        return stockFields;
    }

	public JPanel mainPanel;

	private ArrayList<JTextField> stockFields;
	private ArrayList<JLabel> nameLabel;
	private ArrayList<JLabel> iconLabels;

	private Font nameFont;
	private Font stockFont;
	private LineBorder border;
	private JLabel update;
	
	private Color darkGrey;
	private Color grey;
	private Color lightGrey;
	private Color red;
	private Color white;
}