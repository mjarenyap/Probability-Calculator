import java.util.*;
import java.text.*;

public class Inventory
{
	public Inventory()
	{
		totalCount = 0;

		meatCounts = new int[10];
		for(int i = 0; i < meatCounts.length; i++)
			meatCounts[i] = 0;

		meatNames = new String[10];
		meatNames[0] = "Burrito";
		meatNames[1] = "Ham";
		meatNames[2] = "Bacon";
		meatNames[3] = "Sausage";
		meatNames[4] = "Fish";
		meatNames[5] = "Kebab";
		meatNames[6] = "Burger";
		meatNames[7] = "Steak";
		meatNames[8] = "Chicken";
		meatNames[9] = "Pepperoni";
	}

	public void setMeatCount(int amount, int index)
	{
		meatCounts[index] = amount;
	}

	public void countTotal()
	{
		totalCount = 0;
		for(int i = 0; i < meatCounts.length; i++)
			totalCount += meatCounts[i];
	}
    
    public double computeMarginalPercentage(ArrayList<Boolean> activities)
    {
        DecimalFormat twoPlaces = new DecimalFormat("#.##");
        int base = 0;
        
        for(int i = 0; i < meatNames.length; i++)
            if(activities.get(i) == true)
                base += meatCounts[i];
        
        return Double.valueOf(twoPlaces.format((float)base / totalCount * 100));
    }
    
    public ArrayList<String> getMarginalMeatNames(ArrayList<Boolean> activities)
    {
        ArrayList<String> names = new ArrayList<String>();
        
        for(int i = 0; i < activities.size(); i++)
            if(activities.get(i) == true)
                names.add(meatNames[i]);
        
        return names;
    }
    
    public ArrayList<String> getMarginalFormula(ArrayList<Boolean> activities)
    {
        ArrayList<String> formulas = new ArrayList<String>();
        
        for(int i = 0; i < activities.size(); i++)
        {
            if(activities.get(i) == true)
            {
                String temp = "(";
                temp += String.valueOf(meatCounts[i]);
                temp += "/";
                temp += String.valueOf(totalCount);
                temp += ")";
                
                formulas.add(temp);
            }
        }
        
        return formulas;
    }
    
    public double computeConditionalPercentage(ArrayList<Boolean> activities, ArrayList<Integer> added, ArrayList<Integer> removed)
    {
        DecimalFormat twoPlaces = new DecimalFormat("#.##");
        int tempTotal = totalCount;
        int base = 0;
        
        for(int i = 0; i < added.size(); i++)
        {
            tempTotal += added.get(i);
            tempTotal -= removed.get(i);
        }
        
        for(int i = 0; i < activities.size(); i++)
        {
            if(activities.get(i) == true)
            {
                base += meatCounts[i];
                base += added.get(i);
                base -= removed.get(i);
            }
        }
        
        return Double.valueOf(twoPlaces.format((float)base / tempTotal * 100));
    }
    
    public ArrayList<String> getConditionalMeatNames(ArrayList<Boolean> activities)
    {
        ArrayList<String> names = new ArrayList<String>();
        
        for(int i = 0; i < activities.size(); i++)
            if(activities.get(i) == true)
                names.add(meatNames[i]);
        
        return names;
    }
    
    public ArrayList<String> getConditionalFormula(ArrayList<Boolean> activities, ArrayList<Integer> added, ArrayList<Integer> removed)
    {
        int tempTotal = totalCount;
        ArrayList<String> formulas = new ArrayList<String>();
        
        for(int i = 0; i < added.size(); i++)
        {
            tempTotal += added.get(i);
            tempTotal -= removed.get(i);
        }
        
        for(int i = 0; i < activities.size(); i++)
        {
            if(activities.get(i) == true)
            {
                String temp = "(";
                temp += String.valueOf(meatCounts[i] + added.get(i) - removed.get(i));
                temp += "/";
                temp += String.valueOf(tempTotal);
                temp += ")";
                
                formulas.add(temp);
            }
        }
        
        return formulas;
    }
    
    public double computeSuccessivePercentage(ArrayList<Boolean> activities, ArrayList<Integer> take)
    {
        DecimalFormat twoPlaces = new DecimalFormat("#.####");
        int tempTotal = totalCount;
        double base = 1;
        
        for(int i = 0; i < activities.size(); i++)
        {
            if(activities.get(i) == true)
            {
                base = base * ((double)meatCounts[i] / (double)tempTotal);
                tempTotal -= take.get(i);
            }
        }
        
        return Double.valueOf(twoPlaces.format(base * 100));
    }
    
    public ArrayList<String> getSuccessiveMeatNames(ArrayList<Boolean> activities)
    {
        ArrayList<String> names = new ArrayList<String>();
        
        for(int i = 0; i < activities.size(); i++)
            if(activities.get(i) == true)
                names.add(meatNames[i]);
        
        return names;
    }
    
    public ArrayList<String> getSuccessiveFormula(ArrayList<Boolean> activities, ArrayList<Integer> take)
    {
        ArrayList<String> formulas = new ArrayList<String>();
        int tempTotal = totalCount;
        
        for(int i = 0; i < activities.size(); i++)
        {
            if(activities.get(i) == true)
            {
                String temp = "(";
                temp += String.valueOf(meatCounts[i]);
                temp += "/";
                temp += String.valueOf(tempTotal);
                temp += ")";
                formulas.add(temp);
                
                tempTotal -= take.get(i);
            }
        }
        
        return formulas;
    }

	public int getMeatCount(int type)
	{
		return meatCounts[type];
	}

	public int[] getEveryMeat()
	{
		return meatCounts;
	}

	public String[] getEveryName()
	{
		return meatNames;
	}

	public int getTotalCount()
	{
		return totalCount;
	}

	private int[] meatCounts;
	private String[] meatNames;
	private int totalCount;
}