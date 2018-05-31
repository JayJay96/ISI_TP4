package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChooserFrame extends JFrame implements ActionListener {
	
	public ChooserFrame(){
		super("Choix du mode");
		createPanel();
	}

	private void createPanel() {
		getContentPane().setLayout(new BorderLayout(10,10));
		
		JPanel buttonPanel = new JPanel();
		JButton controlledMode = new JButton("Tortues controlées");
		controlledMode.setActionCommand("controlledMode");
		controlledMode.addActionListener(this);
		JButton automaticMode = new JButton("Tortues automatiques");
		automaticMode.setActionCommand("automaticMode");
		automaticMode.addActionListener(this);
		
		buttonPanel.add(controlledMode);
		buttonPanel.add(automaticMode);
		
		getContentPane().add(buttonPanel, "Center");
		
		pack();
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String c = e.getActionCommand();		
		
		SimpleLogo mainFrame = null;
		if(c.equals("controlledMode")){
			mainFrame = new SimpleLogo();
		} else if (c.equals("automaticMode")){
			mainFrame = new SimpleLogo();
		}
		dispose();
	}
	
	

}
