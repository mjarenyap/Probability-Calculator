import java.util.*;
import java.time.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.event.*;

public class SuccessivePage
{
	public SuccessivePage(String[] names)
	{
		meatNames = names;
		meatButtons = new MeatButtons(names);
        activities = new ArrayList<Boolean>();
		
		activeGrey = new Color(30, 32, 35);
		inactiveGrey = new Color(44, 48, 53);
		formulaGrey = new Color(22, 26, 28);
		lightGrey = new Color(89, 95, 99);

		red = new Color(246, 61, 67);
		white = new Color(229, 229, 229);

		border = new LineBorder(lightGrey, 1);
		probFont = new Font("Source Sans Pro", Font.PLAIN, 13);
		formulaFont = new Font("Source Sans Pro", Font.PLAIN, 16);
		percentFont = new Font("Bebas Neue", Font.PLAIN, 100);
        conditionLabelFont = new Font("Source Sans Pro", Font.PLAIN, 10);


		setMainPanel();
		setMiniPanel();
        setFields();
        setMeatLabels(names);
        setLabels();
		setDisplay();

		combineAll();
	}

	public void combineAll()
	{

		miniPanel.add(formula);
		miniPanel.add(equation);

        for(int i = 0; i < removeFields.size(); i++)
            mainPanel.add(removeFields.get(i));
        
        mainPanel.add(removeLabel);
		mainPanel.add(miniPanel);
		mainPanel.add(label);
		mainPanel.add(meat);
		mainPanel.add(percentage);

		for(int i = 0; i < meatButtons.getButtons().size(); i++)
        {
            mainPanel.add(meatLabels.get(i));
			mainPanel.add(meatButtons.getButtons().get(i));
            activities.add(false);
        }
	}

	public void setMainPanel()
	{
		mainPanel = new JPanel(null);
		mainPanel.setBounds(320, 47, 550, 513);
		mainPanel.setBackground(activeGrey);
	}

	public void setDisplay()
	{
		label = new JLabel("Probability percentage of getting");
		label.setBounds(220, 70, 205, 15);
		label.setFont(probFont);
		label.setForeground(lightGrey);
		label.setBackground(activeGrey);
		label.setHorizontalAlignment(SwingConstants.CENTER);

		//change meat and percentage :)

		meat = new JLabel("no meat selected.");
		meat.setBounds(125, 90, 400, 15);
		meat.setFont(probFont);
		meat.setForeground(red);
		meat.setBackground(activeGrey);
		meat.setHorizontalAlignment(SwingConstants.CENTER);

		percentage = new JLabel("0.00" + "%");
		percentage.setBounds(150, 110, 350, 100);
		percentage.setFont(percentFont);
		percentage.setForeground(red);
		percentage.setBackground(activeGrey);
		percentage.setHorizontalAlignment(SwingConstants.CENTER);

	}
    
    public void setLabels()
    {
        removeLabel = new JLabel("Amount to take.");
        
        removeLabel.setBounds(110, 8, 70, 20);
		removeLabel.setBackground(activeGrey);
		removeLabel.setForeground(red);
		removeLabel.setFont(conditionLabelFont);
		removeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    public void setFields()
    {
        removeFields = new ArrayList<JTextField>();
        int y = 8;
        
        for(int i = 0; i < 10; i++)
		{
			y += 20;

			removeFields.add(new JTextField("0"));
			removeFields.get(i).setBounds(120, y, 50, 20);
			removeFields.get(i).setBorder(border);
			removeFields.get(i).setHorizontalAlignment(SwingConstants.CENTER);
			removeFields.get(i).setFont(conditionLabelFont);
			removeFields.get(i).setForeground(lightGrey);
			removeFields.get(i).setBackground(activeGrey);
			removeFields.get(i).setVisible(true);
			removeFields.get(i).setOpaque(true);
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
    
    public void setToggle(int index)
    {
        activities.set(index, !activities.get(index));
        
        if(activities.get(index) == true)
            meatButtons.getButtons().get(index).setIcon(new ImageIcon("images/" + meatNames[index] + "-active.png"));
        
        else meatButtons.getButtons().get(index).setIcon(new ImageIcon("images/" + meatNames[index] + "-inactive.png"));
    }
    
    public ArrayList<JButton> getMeatButtons()
    {
        return meatButtons.getButtons();
    }
    
    public ArrayList<Boolean> getActivities()
    {
        return activities;
    }
    
    public ArrayList<Integer> getTakes()
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
    
    public int getActiveCount()
    {
        int total = 0;
        for(int i = 0; i < activities.size(); i++)
            if(activities.get(i) == true)
                total++;
        
        return total;
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
    
    public void updateDetails(double percent, ArrayList<String> involved, ArrayList<String> formulas)
    {
        percentage.setText(String.valueOf(percent) + "%");
        
        String stream = "";
        String formulaStream = "";
        for(int i = 0; i < involved.size(); i++)
        {
            formulaStream += formulas.get(i);
            stream += involved.get(i);
            if(i != involved.size() - 1)
                stream += ", ";
        }
        
        if(involved.size() == 0)
        {
            meat.setText("no meat selected.");
            percentage.setText("0.00%");
        }
        
        else meat.setText(stream + ".");
        equation.setText(formulaStream);
    }

	public JPanel mainPanel;
	private JPanel miniPanel;
    
    public MeatButtons meatButtons;

	private JLabel formula;
	private JLabel equation;

	private JLabel label;
	private JLabel meat;
	private JLabel percentage;

	private String example = "";
	private String[] meatNames;
    private ArrayList<Boolean> activities;
    
    private ArrayList<JTextField> removeFields;
    private ArrayList<JLabel> meatLabels;
    private JLabel removeLabel;
    
	private LineBorder border;
	private Font probFont;
	private Font formulaFont;
	private Font percentFont;
    private Font conditionLabelFont;

	private Color activeGrey;
	private Color inactiveGrey;
	private Color formulaGrey;
	private Color lightGrey;

	private Color red;
	private Color white;
}