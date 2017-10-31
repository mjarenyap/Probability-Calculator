import java.util.*;
import java.time.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.event.*;

public class BottomPage
{
	public BottomPage()
	{
		activeGrey = new Color(30, 32, 35);
		inactiveGrey = new Color(44, 48, 53);
		formulaGrey = new Color(22, 26, 28);
		lightGrey = new Color(89, 95, 99);
		red = new Color(246, 61, 67);
		white = new Color(229, 229, 229);
		grey = new Color(206, 206, 206);

		border = new LineBorder(lightGrey, 1);
		saveFont = new Font("Source Sans Pro", Font.PLAIN, 16);

		setMainPanel();
		setSaveButton();
		setSettingButton();
		setInventoryButton();

		combineAll();
	}

	public void combineAll()
	{
		mainPanel.add(save);
		mainPanel.add(setting);
		mainPanel.add(inventory);
	}

	public void setMainPanel()
	{
		mainPanel = new JPanel(null);
		mainPanel.setBounds(0, 499, 320, 40);
	}

	public void setInventoryButton()
	{
		inventory = new JButton(new ImageIcon("images/box.png"));

		inventory.setBounds(0, 0, 65, 40);
		inventory.setBackground(white);
        inventory.setOpaque(true);
		inventory.setBorderPainted(false);

	}

	public void setSettingButton()
	{
		setting = new JButton(new ImageIcon("images/gear2.png"));

		setting.setBounds(65, 0, 65, 40);
		setting.setBackground(grey);
        setting.setOpaque(true);
		setting.setBorderPainted(false);
	}

	public void setSaveButton()
	{
		save = new JButton("Save & Compute");

		save.setBounds(130, 0, 190, 40);
		save.setFont(saveFont);
		save.setForeground(white);
		save.setBackground(red);
		save.setBorder(border);
        save.setOpaque(true);
		save.setBorderPainted(false);
		save.setVisible(true);
	}
    
    public void setToggle(int command)
    {
        if(command == 0)
        {
            inventory.setIcon(new ImageIcon("images/box.png"));
            inventory.setBackground(white);
            
            setting.setIcon(new ImageIcon("images/gear2.png"));
            setting.setBackground(grey);   
        }
        
        else
        {
            inventory.setIcon(new ImageIcon("images/box2.png"));
            inventory.setBackground(grey);
            
            setting.setIcon(new ImageIcon("images/gear.png"));
            setting.setBackground(white);
        }
    }

	public JButton getInventoryButton()
	{
		return inventory;
	}

	public JButton getSettingButton()
	{
		return setting;
	}

	public JButton getSaveButton()
	{
		return save;
	}

	public JPanel getMainPanel()
	{
		return mainPanel;
	}

	public JPanel mainPanel;

	private LineBorder border;
	private Font saveFont;

	public JButton save;
	public JButton inventory;
	public JButton setting;

	private Color activeGrey;
	private Color inactiveGrey;
	private Color formulaGrey;
	private Color lightGrey;
	private Color grey;

	private Color red;
	private Color white;
}