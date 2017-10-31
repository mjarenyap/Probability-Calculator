import java.util.*;
import java.time.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.text.*;

public class CountProgram
{
	public JFrame mainFrame;
	public Container mainPane;

	public MarginalPage marginalPanel;
	public SuccessivePage successivePanel;
	public ConditionalPage conditionalPanel;

	public SettingsPage settingsPanel;
	public InventoryPage inventoryPanel;
	public MenuPage menuPanel;
	public BottomPage bottomPanel;

	public Inventory store;

	public CountProgram()
	{
		mainFrame = new JFrame("Choice Cuts Corporation");
		mainPane = mainFrame.getContentPane();
		mainFrame.setSize(860, 560);

		mainPane.setLayout(null);

		/* Initialize the models */
		store = new Inventory();

		/* Initialize the views */
		marginalPanel = new MarginalPage(store.getEveryName());
		successivePanel = new SuccessivePage(store.getEveryName());
		conditionalPanel = new ConditionalPage(store.getEveryName());
		settingsPanel = new SettingsPage();
		//store.getTotalCount()
		inventoryPanel = new InventoryPage(store.getEveryName());
		bottomPanel = new BottomPage();
		menuPanel = new MenuPage();

		mainPane.add(bottomPanel.getMainPanel());
		mainPane.add(inventoryPanel.getMainPanel());
        mainPane.add(menuPanel.getMainPanel());
		mainPane.add(settingsPanel.getMainPanel());
		mainPane.add(marginalPanel.getMainPanel());
		mainPane.add(successivePanel.getMainPanel());
		mainPane.add(conditionalPanel.getMainPanel());
        
        firstImpression();
        setActionListeners();
	

		/* Add views to the pane */

		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.setResizable(true);
	}
    
    public void firstImpression()
    {
        marginalPanel.mainPanel.setVisible(true);
        successivePanel.mainPanel.setVisible(false);
        conditionalPanel.mainPanel.setVisible(false);
        
        inventoryPanel.mainPanel.setVisible(true);
        settingsPanel.mainPanel.setVisible(false);
    }
    
    public void setActionListeners()
    {
        menuPanel.marginal.addActionListener(new menupage_tab_action());
        menuPanel.successive.addActionListener(new menupage_tab_action());
        menuPanel.conditional.addActionListener(new menupage_tab_action());
        
        bottomPanel.inventory.addActionListener(new bottompage_tab_action());
        bottomPanel.setting.addActionListener(new bottompage_tab_action());
        bottomPanel.save.addActionListener(new compute_action());
        
        for(int i = 0; i < 10; i++)
        {
            marginalPanel.getMeatButtons().get(i).addActionListener(new meatButton_action());
            successivePanel.getMeatButtons().get(i).addActionListener(new meatButton_action());
            conditionalPanel.getMeatButtons().get(i).addActionListener(new meatButton_action());
        }
    }
    
    class menupage_tab_action implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource().equals(menuPanel.marginal))
            {
                marginalPanel.mainPanel.setVisible(true);
                successivePanel.mainPanel.setVisible(false);
                conditionalPanel.mainPanel.setVisible(false);
                
                menuPanel.setTabActive('m');
            }
            
            else if(e.getSource().equals(menuPanel.successive))
            {
                marginalPanel.mainPanel.setVisible(false);
                successivePanel.mainPanel.setVisible(true);
                conditionalPanel.mainPanel.setVisible(false);
                
                menuPanel.setTabActive('s');
            }
            
            else if(e.getSource().equals(menuPanel.conditional))
            {
                marginalPanel.mainPanel.setVisible(false);
                successivePanel.mainPanel.setVisible(false);
                conditionalPanel.mainPanel.setVisible(true);
                
                menuPanel.setTabActive('c');
            }
        }
    }
    
    class meatButton_action implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            for(int i = 0; i < 10; i++)
            {
                if(e.getSource().equals(marginalPanel.getMeatButtons().get(i)))
                    marginalPanel.setToggle(i);
                
                else if(e.getSource().equals(successivePanel.getMeatButtons().get(i)))
                    successivePanel.setToggle(i);
                
                else if(e.getSource().equals(conditionalPanel.getMeatButtons().get(i)))
                    conditionalPanel.setToggle(i);
            }
            
            menuPanel.changeTitle(marginalPanel.getActiveCount());
            
            /* For the marginal probability */
            double marginalPercentage = store.computeMarginalPercentage(marginalPanel.getActivities());
            ArrayList<String> marginalInvolved = store.getMarginalMeatNames(marginalPanel.getActivities());
            ArrayList<String> marginalFormulas = store.getMarginalFormula(marginalPanel.getActivities());
            marginalPanel.updateDetails(marginalPercentage, marginalInvolved, marginalFormulas);
            
            /* For the successive probability */
            double successivePercentage = store.computeSuccessivePercentage(successivePanel.getActivities(), successivePanel.getTakes());
            ArrayList<String> successiveInvolved = store.getSuccessiveMeatNames(successivePanel.getActivities());
            ArrayList<String> successiveFormulas = store.getSuccessiveFormula(successivePanel.getActivities(), successivePanel.getTakes());
            successivePanel.updateDetails(successivePercentage, successiveInvolved, successiveFormulas);
            
            /* For the conditional probability */
            double conditionalPercentage = store.computeConditionalPercentage(conditionalPanel.getActivities(), conditionalPanel.getAddedList(),
                                                                             conditionalPanel.getRemovedList());
            ArrayList<String> conditionalInvolved = store.getConditionalMeatNames(conditionalPanel.getActivities());
            ArrayList<String> conditionalFormulas = store.getConditionalFormula(conditionalPanel.getActivities(), conditionalPanel.getAddedList(),
                                                                             conditionalPanel.getRemovedList());
            conditionalPanel.updateDetails(conditionalPercentage, conditionalInvolved, conditionalFormulas);
        }
    }
    
    class bottompage_tab_action implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource().equals(bottomPanel.inventory))
            {
                bottomPanel.setToggle(0);
                inventoryPanel.mainPanel.setVisible(true);
                settingsPanel.mainPanel.setVisible(false);
            }
            
            else if(e.getSource().equals(bottomPanel.setting))
            {
                bottomPanel.setToggle(1);
                inventoryPanel.mainPanel.setVisible(false);
                settingsPanel.mainPanel.setVisible(true);
            }
        }
    }
    
    class compute_action implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            for(int i = 0; i < inventoryPanel.getStockFields().size(); i++)
                store.setMeatCount(Integer.parseInt(inventoryPanel.getStockFields().get(i).getText().toString()), i);
            
            store.countTotal();
            settingsPanel.setTotalCount(store.getTotalCount());
            
            menuPanel.changeTitle(marginalPanel.getActiveCount());
            
            /* For the marginal probability */
            double marginalPercentage = store.computeMarginalPercentage(marginalPanel.getActivities());
            ArrayList<String> marginalInvolved = store.getMarginalMeatNames(marginalPanel.getActivities());
            ArrayList<String> marginalFormulas = store.getMarginalFormula(marginalPanel.getActivities());
            marginalPanel.updateDetails(marginalPercentage, marginalInvolved, marginalFormulas);
            
            /* For the successive probability */
            double successivePercentage = store.computeSuccessivePercentage(successivePanel.getActivities(), successivePanel.getTakes());
            ArrayList<String> successiveInvolved = store.getSuccessiveMeatNames(successivePanel.getActivities());
            ArrayList<String> successiveFormulas = store.getSuccessiveFormula(successivePanel.getActivities(), successivePanel.getTakes());
            successivePanel.updateDetails(successivePercentage, successiveInvolved, successiveFormulas);
            
            /* For the conditional probability */
            double conditionalPercentage = store.computeConditionalPercentage(conditionalPanel.getActivities(), conditionalPanel.getAddedList(),
                                                                             conditionalPanel.getRemovedList());
            ArrayList<String> conditionalInvolved = store.getConditionalMeatNames(conditionalPanel.getActivities());
            ArrayList<String> conditionalFormulas = store.getConditionalFormula(conditionalPanel.getActivities(), conditionalPanel.getAddedList(),
                                                                             conditionalPanel.getRemovedList());
            conditionalPanel.updateDetails(conditionalPercentage, conditionalInvolved, conditionalFormulas);
        }
    }
}