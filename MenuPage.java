import java.util.*;
import java.time.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.event.*;

public class MenuPage
{
	public MenuPage()
	{
		activeGrey = new Color(30, 32, 35);
		inactiveGrey = new Color(44, 48, 53);
		formulaGrey = new Color(22, 26, 28);
		lightGrey = new Color(89, 95, 99);
		darkGrey = new Color(72, 73, 76);

		red = new Color(246, 61, 67);
		white = new Color(229, 229, 229);

		border = new LineBorder(lightGrey, 1);
		tabFont = new Font("Source Sans Pro", Font.PLAIN, 12);
		nameFont = new Font("Montserrat", Font.BOLD, 11);
		stockFont = new Font("Montserrat", Font.PLAIN, 11);

		setMainPanel();
		setMarginalButton();
		setSuccessiveButton();
		setConditionalButton();
		setMiniPanel();
		setCompanyTitle();

		combineAll();
	}

	public void combineAll()
	{
		miniPanel.add(name);
		miniPanel.add(tagline);
		miniPanel.add(shopLabel);

		mainPanel.add(miniPanel);
		mainPanel.add(marginal);
		mainPanel.add(successive);
		mainPanel.add(conditional);
	}

	public void setMainPanel()
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 860, 47);
		mainPanel.setBackground(activeGrey);
	}

	public void setMiniPanel()
	{
		miniPanel = new JPanel();
		miniPanel.setLayout(null);
		miniPanel.setBounds(0, 0, 320, 47);
		miniPanel.setBackground(new Color(206, 206, 206));
	}

	public void setCompanyTitle()
	{
		name = new JLabel("CHOICE CUTS");
		name.setBounds(100, 12, 150, 15);
		name.setFont(nameFont);
		name.setForeground(darkGrey);

		tagline = new JLabel("ONE SHOP, EVERY MEAT");
		tagline.setBounds(100, 25, 150, 15);
		tagline.setFont(stockFont);
		tagline.setForeground(red);

		shopLabel = new JLabel(new ImageIcon("images/shopLogo.png"));
		shopLabel.setBounds(50, 0, 50, 50);
	}

	public void setMarginalButton()
	{	
		marginal = new JButton("MARGINAL");
		marginal.setBounds(320, 0, 180, 47);
		marginal.setFont(tabFont);
		marginal.setBorder(border);
        marginal.setOpaque(true);
		marginal.setBorderPainted(false);
		marginal.setVisible(true);
		marginal.setForeground(red);
		marginal.setBackground(activeGrey);
		marginal.setVisible(true);
	}

	public void setSuccessiveButton()
	{
		successive = new JButton("SUCCESSIVE");
		successive.setBounds(500, 0, 180, 47);
		successive.setFont(tabFont);
		successive.setBorder(border);
        successive.setOpaque(true);
		successive.setBorderPainted(false);
		successive.setVisible(true);

		successive.setForeground(lightGrey);
		successive.setBackground(inactiveGrey);
	}

	public void setConditionalButton()
	{
		conditional = new JButton("CONDITIONAL");
		conditional.setBounds(680, 0, 180, 47);
		conditional.setFont(tabFont);
		conditional.setBorder(border);
        conditional.setOpaque(true);
		conditional.setBorderPainted(false);
		conditional.setVisible(true);
		conditional.setForeground(lightGrey);
		conditional.setBackground(inactiveGrey);
	}
    
    public void setTabActive(char command)
    {
        if(command == 'm')
        {
            marginal.setBackground(activeGrey);
            marginal.setForeground(red);
            successive.setBackground(inactiveGrey);
            successive.setForeground(lightGrey);
            conditional.setBackground(inactiveGrey);
            conditional.setForeground(lightGrey);
        }
        
        else if(command == 's')
        {
            marginal.setBackground(inactiveGrey);
            marginal.setForeground(lightGrey);
            successive.setBackground(activeGrey);
            successive.setForeground(red);
            conditional.setBackground(inactiveGrey);
            conditional.setForeground(lightGrey);
        }
        
        else if(command == 'c')
        {
            marginal.setBackground(inactiveGrey);
            marginal.setForeground(lightGrey);
            successive.setBackground(inactiveGrey);
            successive.setForeground(lightGrey);
            conditional.setBackground(activeGrey);
            conditional.setForeground(red);
        }
    }
    
    public void changeTitle(int count)
    {
        if(count > 1)
            marginal.setText("MUTUALLY EXCLUSIVE");
        
        else marginal.setText("MARGINAL");
    }

	public JPanel getMainPanel()
	{
		return mainPanel;
	}

	public JButton getMarginalButton()
	{
		return marginal;
	}

	public JButton getSuccessiveButton()
	{
		return successive;
	}

	public JButton getConditionalButton()
	{
		return conditional;
	}

	public JPanel mainPanel;
	private JPanel miniPanel;

	public JButton marginal;
	public JButton successive;
	public JButton conditional;

	private JLabel name;
	private JLabel tagline;
	private JLabel shopLabel;

	private Border border;

	private Font tabFont;
	private Font nameFont;
	private Font stockFont;

	private Color activeGrey;
	private Color inactiveGrey;
	private Color formulaGrey;
	private Color lightGrey;
	private Color darkGrey;

	private Color red;
	private Color white;
}