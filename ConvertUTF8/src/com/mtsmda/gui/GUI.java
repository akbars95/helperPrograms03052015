package com.mtsmda.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class GUI {
	private JFrame frame;
	private JRadioButton jRadioButtonText, jRadioButtonFile;
	private ButtonGroup buttonGroup;
	private JTextArea jTextAreaTextBeforeConvert, jTextAreaTextAfterConvert;

	public GUI() {
		frame = new JFrame("Convert text to UTF-8");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 250));
		frame.setLayout(new FlowLayout());

		frame.add(createRadioButtons());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private Component createRadioButtons() {
		JPanel jPanel = new JPanel(new FlowLayout());
		jRadioButtonFile = new JRadioButton("Convert file");
		jRadioButtonText = new JRadioButton("Convert text");
		jRadioButtonText.setSelected(true);
		buttonGroup = new ButtonGroup();
		buttonGroup.add(jRadioButtonText);
		buttonGroup.add(jRadioButtonFile);
		jPanel.add(jRadioButtonText);
		jPanel.add(jRadioButtonFile);
		jRadioButtonText.addActionListener(new ActionListenerImpl());

		return jPanel;
	}

	class ActionListenerImpl implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (jRadioButtonText.isSelected()) {
				frame.add(createComponentForText());
			}
		}
	}

	private Component createComponentForText() {
		JPanel jPanel = new JPanel(new FlowLayout());
		jTextAreaTextBeforeConvert = new JTextArea(5, 25);
		jTextAreaTextAfterConvert = new JTextArea(5, 25);

		jPanel.add(jTextAreaTextBeforeConvert);
		jPanel.add(jTextAreaTextAfterConvert);

		return jPanel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GUI();
			}
		});

	}

}