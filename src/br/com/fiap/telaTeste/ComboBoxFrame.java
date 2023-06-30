package br.com.fiap.telaTeste;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ComboBoxFrame extends JFrame {
	
	private JComboBox imagesJComboBox;
	private JLabel label;
	private String names[] = {"bug1.gif", "bug2.gif", "travelbug.gif", "buganim.gif"};
	private Icon icons[] = {new ImageIcon(getClass().getResource(names[0])),
			new ImageIcon(getClass().getResource(names[1])), new ImageIcon(getClass().getResource(names[2])),
			new ImageIcon(getClass().getResource(names[3])) };

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComboBoxFrame frame = new ComboBoxFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ComboBoxFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLayout(new FlowLayout());

		imagesJComboBox = new JComboBox(names);
		imagesJComboBox.setMaximumRowCount(5); // exibe tr�s linhas
		imagesJComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED)
					label.setIcon(icons[imagesJComboBox.getSelectedIndex()]);
			}
		});

		add(imagesJComboBox);
		label = new JLabel(icons[0]);
		add(label);
	}
}
