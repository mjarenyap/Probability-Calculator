import java.util.*;
import java.time.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.event.*;

public class ConditionalPage
{
	public ConditionalPage(String[] names)
	{
		meatNames = names;
		meatButtons = new MeatButtons(names);
        activities = new ArrayList<Boolean>();

		grey = new Color(89, 95, 99);
		lightGrey = new Color(89, 95, 99);
		red = new Color(246, 61, 67);
		white = new Color(229, 229, 229);
		activeGrey = new Color(30, 32, 35);
		formulaGrey = new Color(22, 26, 28);

		border = new LineBorder(lightGrey, 1);

		formulaFont = new Font("Source Sans Pro", Font.PLAIN, 16);
		probFont = new Font("Source Sans Pro", Font.PLAIN, 13);
		conditionLabelFont = new Font("Source Sans Pro", Font.PLAIN, 10);
		meatFont = new Font("Source Sans Pro", Font.BOLD, 10);
		percentFont = new Font("Bebas Neue", Font.PLAIN, 81);

		setMainPanel();
		setDisplay();
		setMiniPanel();
		setFields();
		setMeatLabels(names);
		setLabels();

		combineAll();
	}

	public void combineAll()
	{
		miniPanel.add(formula);
		miniPanel.add(equation);

		mainPanel.add(miniPanel);
		mainPanel.add(label);
		mainPanel.add(meat);
		mainPanel.add(percentage);
		mainPanel.add(addLabel);
		mainPanel.add(removeLabel);

		for(int i = 0; i < meatButtons.getButtons().size(); i++)
		{	mainPanel.add(meatButtons.getButtons().get(i));
			mainPanel.add(addFields.get(i));
			mainPanel.add(removeFields.get(i));
			mainPanel.add(meatLabels.get(i));
            activities.add(false);
		}

	}

	public void setMainPanel()
	{
		mainPanel = new JPanel(null);
		mainPanel.setBounds(320, 47, 550, 513);
		mainPanel.setBackground(activeGrey);
	}
	
	public void setLabels()
	{
		addLabel = new JLabel("Added");
		removeLabel = new JLabel("Removed");

		addLabel.setBounds(120, 8, 50, 20);
		addLabel.setBackground(activeGrey);
		addLabel.setForeground(red);
		addLabel.setFont(conditionLabelFont);
		addLabel.setHorizontalAlignment(SwingConstants.CENTER);

		removeLabel.setBounds(170, 8, 50, 20);
		removeLabel.setBackground(activeGrey);
		removeLabel.setForeground(red);
		removeLabel.setFont(conditionLabelFont);
		removeLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void setMeatLabels(String[] names)
	{
		meatLabels = new ArrayList<JLabel>();
		int y = 8;

		for(int i = 0; i < names.length; i++)
		{
			y += 20;

			meatLabels.add(new JLabel(names[i]));
			meatLabels.get(i).setBounds(60, y, 60, 20);
			meatLabels.get(i).setFont(conditionLabelFont);
			meatLabels.get(i).setForeground(lightGrey);
			meatLabels.get(i).setBackground(activeGrey);
			meatLabels.get(i).setVisible(true);
			meatLabels.get(i).setOpaque(true);
		}
	}

	public void setMiniPanel()
	{
		miniPanel = new JPanel(null);
		miniPanel.setBounds(0, 240, 550, 60);
		miniPanel.setBackground(formulaGrey);

		formula = new JLabel("Formula:");
		formula.setFont(formulaFont);
		formula.setBounds(50, 20, 100, 20);
		formula.setForeground(lightGrey);
		formula.setBackground(formulaGrey);

		equation = new JLabel(example, SwingConstants.CENTER);
		equation.setFont(formulaFont);
		equation.setBounds(150, 20, 250, 20);
		equation.setForeground(red);
		equation.setBackground(formulaGrey);
	}

	public void setDisplay()
	{
		label = new JLabel("Probability percentage of getting");
		label.setBounds(270, 70, 205, 15);
		label.setFont(probFont);
		label.setForeground(lightGrey);
		label.setBackground(activeGrey);
		label.setHorizontalAlignment(SwingConstants.CENTER);

		//change meat and percentage :)

		meat = new JLabel("no meat selected.");
		meat.setBounds(170, 90, 400, 15);
		meat.setFont(probFont);
		meat.setForeground(red);
		meat.setBackground(activeGrey);
		meat.setHorizontalAlignment(SwingConstants.CENTER);

		percentage = new JLabel("0.00" + "%");
		percentage.setBounds(200, 110, 350, 100);
		percentage.setFont(percentFont);
		percentage.setForeground(red);
		percentage.setBackground(activeGrey);
		percentage.setHorizontalAlignment(SwingConstants.CENTER);

	}



	public void setFields()
	{
		addFields = new ArrayList<JTextField>();
		removeFields = new ArrayList<JTextField>();
		int y = 8;

		for(int i = 0; i < 10; i++)
		{
			y += 20;

			addFields.add(new JTextField("0"));
			addFields.get(i).setBounds(120, y, 50, 20);
			addFields.get(i).setBorder(border);
			addFields.get(i).setHorizontalAlignment(SwingConstants.CENTER);
			addFields.get(i).setFont(conditionLabelFont);
			addFields.get(i).setForeground(lightGrey);
			addFields.get(i).setBackground(activeGrey);
			addFields.get(i).setVisible(true);
			addFields.get(i).setOpaque(true);
		}

		y = 8;

		for(int i = 0; i < 10; i++)
		{
			y += 20;

			removeFields.add(new JTextField("0"));
			removeFields.get(i).setBounds(170, y, 50, 20);
			removeFields.get(i).setBorder(border);
			removeFields.get(i).setHorizontalAlignment(SwingConstants.CENTER);
			removeFields.get(i).setFont(conditionLabelFont);
			removeFields.get(i).setForeground(lightGrey);
			removeFields.get(i).setBackground(activeGrey);
			removeFields.get(i).setVisible(true);
			removeFields.get(i).setOpaque(true);
		}
	}
    
    public void setToggle(int index)
    {
        activities.set(index, !activities.get(index));
        
        if(activities.get(index) == true)
            meatButtons.getButtons().get(index).setIcon(new ImageIcon("images/" + meatNames[index] + "-active.png"));
        
        else meatButtons.getButtons().get(index).setIcon(new ImageIcon("images/" + meatNames[index] + "-inactive.png"));
    }
    
    public ArrayList<Boolean> getActivities()
    {
        return activities;
    }
    
    public ArrayList<Integer> getAddedList()
    {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        
        for(int i = 0; i < addFields.size(); i++)
            temp.add(Integer.parseInt(addFields.get(i).getText().toString()));
        
        return temp;
    }
    
    public ArrayList<Integer> getRemovedList()
    {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        
        for(int i = 0; i < removeFields.size(); i++)
            temp.add(Integer.parseInt(removeFields.get(i).getText().toString()));
        
        return temp;
    }

	public JPanel getMainPanel()
	{
		return mainPanel;
	}
    
    public ArrayList<JButton> getMeatButtons()
    {
        return meatButtons.getButtons();
    }
    
    public int getActiveCount()
    {
        int total = 0;
        for(int i = 0; i < activities.size(); i++)
            if(activities.get(i) == true)
                total++;
        
        return total;
    }
    
    public void updateDetails(double percent, ArrayList<String> involved, ArrayList<String> formulas)
    {
        percentage.setText(Double.toString(percent) + "%");
        
        String stream = "";
        String formulaStream = "";
        for(int i = 0; i < involved.size(); i++)
        {
            stream += involved.get(i).toLowerCase();
            formulaStream += formulas.get(i);
            if(i != involved.size() - 1)
            {
                stream += ", ";
                formulaStream += "+";
            }
        }
        
        if(involved.size() == 0)
            meat.setText("no meat selected.");
        
        else meat.setText(stream + ".");
        equation.setText(formulaStream);
    }

	public JPanel mainPanel;
	private JPanel miniPanel;

    private MeatButtons meatButtons;

	private JLabel percentage;
	private JLabel meat;
	private JLabel label;
	private JLabel formula;
	private JLabel equation;

	private JLabel addLabel;
	private JLabel removeLabel;

	private LineBorder border;
	private String[] meatNames;

	private String example = "";

	private ArrayList<JLabel> meatLabels;
	private ArrayList<JTextField> addFields;
	private ArrayList<JTextField> removeFields;
    private ArrayList<Boolean> activities;

	private Font formulaFont;
	private Font probFont;
	private Font percentFont;
	private Font conditionLabelFont;
	private Font meatFont;

	private Color grey;
	private Color lightGrey;
	private Color red;
	private Color white;
	private Color activeGrey;
	private Color formulaGrey;
}