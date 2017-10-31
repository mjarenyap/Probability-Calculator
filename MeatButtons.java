import java.util.*;
import java.time.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.event.*;

public class MeatButtons
{
	public MeatButtons(String[] names)
	{
		int yOffset = 0;
		int xOffset = -1;

		meats = new ArrayList<JButton>();
		for(int i = 0; i < names.length; i++)
		{
			if(i == 5)
            {
				yOffset += 96;
                xOffset = -1;
            }

			JButton temp = new JButton("");
			temp.setBounds(xOffset, 300 + yOffset, 110, 97);
            temp.setBorder(new LineBorder(new Color(72, 73, 76), 1));
            
            ImageIcon btnIcon= new ImageIcon("images/" + names[i] + "-inactive.png");
            btnIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
			temp.setIcon(btnIcon);

			meats.add(temp);
			xOffset += 108;
		}
	}

	public ArrayList<JButton> getButtons()
	{
		return meats;
	}

	private ArrayList<JButton> meats;
}