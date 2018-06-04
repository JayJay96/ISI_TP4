package view;


// package logo;

import java.awt.*;

import javax.swing.*;

import controller.Controller;
import controller.RandomTurtlesThread;
import model.Carre;
import model.Form;
import model.Poly;
import model.Spiral;
import model.Tortue;

import java.awt.event.*;
import java.util.*;
import java.io.*;


/*************************************************************************

	Un petit Logo minimal qui devra etre ameliore par la suite

				J. Ferber - 1999-2001

				Cours de DESS TNI - Montpellier II

	@version 2.0
	@date 25/09/


**************************************************************************/


public class SimpleLogo extends JFrame implements ActionListener {
	public static final Dimension VGAP = new Dimension(1,5);
	public static final Dimension HGAP = new Dimension(5,1);

	private FeuilleDessin feuille;
	private Controller controller = Controller.getInstance();
	private JTextField inputValue;
	private JComboBox colorList;


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		   SwingUtilities.invokeLater(new Runnable(){
				public void run(){

					ChooserFrame fenetre = new ChooserFrame();
					fenetre.setVisible(true);
				}
			});
			
		}
	
	private void quitter() {
		System.exit(0);
	}

	public SimpleLogo(int i) {
		super("un logo tout simple");
		logoInit();
		
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent arg0) {
		        super.windowClosing(arg0);
		        System.exit(0);
		    }
		});
		
		if(i == 1){
			RandomTurtlesThread thread = new RandomTurtlesThread(feuille);
			thread.start();
		}
	}

	public void logoInit() {
		getContentPane().setLayout(new BorderLayout(10,10));

		// Boutons
		JToolBar toolBar = new JToolBar();
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(toolBar);

		getContentPane().add(buttonPanel,"North");

		addButton(toolBar,"Effacer","Nouveau dessin","/icons/index.png");
		
		toolBar.add(Box.createRigidArea(HGAP));
		inputValue=new JTextField("45",5);
		toolBar.add(inputValue);
		addButton(toolBar, "Avancer", "Avancer 50", null);
		addButton(toolBar, "Droite", "Droite 45", null);
		addButton(toolBar, "Gauche", "Gauche 45", null);
		addButton(toolBar, "Lever", "Lever Crayon", null);
		addButton(toolBar, "Baisser", "Baisser Crayon", null);
		addButton(toolBar, "Ajouter", "Ajouter une tortue", null);

		String[] colorStrings = {"noir", "bleu", "cyan","gris fonce","rouge",
								 "vert", "gris clair", "magenta", "orange",
								 "gris", "rose", "jaune"};

		// Create the combo box
		toolBar.add(Box.createRigidArea(HGAP));
		JLabel colorLabel = new JLabel("   Couleur: ");
		toolBar.add(colorLabel);
		colorList = new JComboBox(colorStrings);
		toolBar.add(colorList);

		colorList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				int n = cb.getSelectedIndex();
				controller.getCourante().setColor(n);
			}
		});


		// Menus
		JMenuBar menubar=new JMenuBar();
		setJMenuBar(menubar);	// on installe le menu bar
		JMenu menuFile=new JMenu("File"); // on installe le premier menu
		menubar.add(menuFile);

		addMenuItem(menuFile, "Effacer", "Effacer", KeyEvent.VK_N);
		addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

		JMenu menuCommandes=new JMenu("Commandes"); // on installe le premier menu
		menubar.add(menuCommandes);
		addMenuItem(menuCommandes, "Avancer", "Avancer", -1);
		addMenuItem(menuCommandes, "Droite", "Droite", -1);
		addMenuItem(menuCommandes, "Gauche", "Gauche", -1);
		addMenuItem(menuCommandes, "Lever Crayon", "Lever", -1);
		addMenuItem(menuCommandes, "Baisser Crayon", "Baisser", -1);

		JMenu menuHelp=new JMenu("Aide"); // on installe le premier menu
		menubar.add(menuHelp);
		addMenuItem(menuHelp, "Aide", "Help", -1);
		addMenuItem(menuHelp, "A propos", "About", -1);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// les boutons du bas
		JPanel p2 = new JPanel(new GridLayout());
		JButton b20 = new JButton("Proc1");
		p2.add(b20);
		b20.addActionListener(this);
		JButton b21 = new JButton("Proc2");
		p2.add(b21);
		b21.addActionListener(this);
		JButton b22 = new JButton("Proc3");
		p2.add(b22);
		b22.addActionListener(this);

		getContentPane().add(p2,"South");

		feuille = new FeuilleDessin(this); //500, 400);
		feuille.setBackground(Color.white);
		feuille.setSize(new Dimension(600,400));
		feuille.setPreferredSize(new Dimension(600,400));
			
		getContentPane().add(feuille,"Center");
		
		// Creation de la tortue
		Dimension size = feuille.getSize();
		Tortue tortue = controller.createTortue(colorList.getSelectedIndex(), size.width/2, size.height/2);
		controller.setCourante(tortue);
		
		controller.setxMax(feuille.getWidth());
		controller.setyMax(feuille.getHeight());

		pack();
		setVisible(true);
	}

	public String getInputValue(){
		String s = inputValue.getText();
		return(s);
	}

	/** la gestion des actions des boutons */
	public void actionPerformed(ActionEvent e)
	{
		String c = e.getActionCommand();

		// actions des boutons du haut
		if (c.equals("Avancer")) {
			System.out.println("command avancer");
			try {
				int v = Integer.parseInt(inputValue.getText());
				controller.createSegment(controller.getCourante(), v);
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + inputValue.getText());
			}
			
		}
		else if (c.equals("Droite")) {
			try {
				int v = Integer.parseInt(inputValue.getText());
				controller.getCourante().droite(v);
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + inputValue.getText());
			}
		}
		else if (c.equals("Gauche")) {
			try {
				int v = Integer.parseInt(inputValue.getText());
				controller.getCourante().gauche(v);
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + inputValue.getText());
			}
		}
		else if (c.equals("Lever")) 
			controller.getCourante().leverCrayon();
		else if (c.equals("Baisser"))
			controller.getCourante().baisserCrayon();
		else if(c.equals("Ajouter")){
			Dimension size = feuille.getSize();
			Tortue t = controller.createTortue(colorList.getSelectedIndex(), size.width/2, size.height/2);
			t.setPosition(500/2, 400/2);
		}
		// actions des boutons du bas
		else if (c.equals("Proc1"))
			proc1();
		else if (c.equals("Proc2"))
			proc2();
		else if (c.equals("Proc3"))
			proc3();
		else if (c.equals("Effacer"))
			effacer();
		else if (c.equals("Quitter"))
			quitter();

		feuille.repaint();
	}

  	/** les procedures Logo qui combine plusieurs commandes..*/
	public void proc1() {
		Form carre = new Carre();
		controller.createForm(controller.getCourante(), carre, 100);
	}

	public void proc2() {
		Form poly = new Poly();
		controller.createForm(controller.getCourante(), poly, 60, 8);
	}

	public void proc3() {
		Form spiral = new Spiral();
		controller.createForm(controller.getCourante(), spiral, 50, 40, 6);
		
	}

	// efface tout et reinitialise la feuille
	public void effacer() {
		feuille.reset();
		feuille.repaint();

		// Replace la tortue au centre
		Dimension size = feuille.getSize();
		controller.getCourante().setPosition(size.width/2, size.height/2);
		colorList.setSelectedIndex(0);
	}

	//utilitaires pour installer des boutons et des menus
	public void addButton(JComponent p, String name, String tooltiptext, String imageName) {
		JButton b;
		if ((imageName == null) || (imageName.equals(""))) {
			b = (JButton)p.add(new JButton(name));
		}
		else {
			java.net.URL u = this.getClass().getResource(imageName);
			if (u != null) {
				ImageIcon im = new ImageIcon (u);
				b = (JButton) p.add(new JButton(im));
			}
			else
				b = (JButton) p.add(new JButton(name));
			b.setActionCommand(name);
		}

		b.setToolTipText(tooltiptext);
		b.setBorder(BorderFactory.createRaisedBevelBorder());
		b.setMargin(new Insets(0,0,0,0));
		b.addActionListener(this);
	}

	public void addMenuItem(JMenu m, String label, String command, int key) {
		JMenuItem menuItem;
		menuItem = new JMenuItem(label);
		m.add(menuItem);

		menuItem.setActionCommand(command);
		menuItem.addActionListener(this);
		if (key > 0) {
			if (key != KeyEvent.VK_DELETE)
				menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
			else
				menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
		}
	}

	public JComboBox getColorList() {
		return colorList;
	}
}
