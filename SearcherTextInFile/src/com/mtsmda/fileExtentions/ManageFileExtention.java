package com.mtsmda.fileExtentions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.mtsmda.searcher.SearchEverywhereText;

public class ManageFileExtention {

	private JFrame jFrame;
	private JTextField jTextFieldNewExtention;
	private JButton jButtonAdd;
	private JTextArea text;
	private JCheckBox jCheckBoxSwitcher;
	private JComboBox<String> jComboBoxExtenshions;

	private DefaultComboBoxModel model;

	private static final String ADD = "Add";
	private static final String REMOVE = "Remove";

	private static final String ADD_BUTTON = "Add new Extention";
	private static final String DELETE_BUTTON = "Delete existing Extention";

	private List<String> extentions = new ArrayList<String>();

	public ManageFileExtention() {
		this.jFrame = new JFrame("Manager Available File Extention");
		this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.jFrame.setLayout(new FlowLayout());
		this.jFrame.setPreferredSize(new Dimension(500, 250));

		this.jFrame.add(panelMain());
		this.jFrame.add(panelAddNewExtention());

		this.jFrame.pack();
		this.jFrame.setLocationRelativeTo(null);
		this.jFrame.setVisible(true);
	}

	private JPanel panelMain() {
		JPanel jPanel = new JPanel(new FlowLayout());
		jPanel.setPreferredSize(new Dimension(500, 100));

		StringBuilder availableFileExtentions = SearchEverywhereText
				.getAvailableFileExtentions(extentions);

		text = new JTextArea(availableFileExtentions.toString(), 15, 10);
		text.setPreferredSize(new Dimension(400, 50));
		text.setWrapStyleWord(true);
		text.setLineWrap(true);
		Font font = new Font("Serif", Font.BOLD, 20);
		text.setFont(font);
		text.setForeground(Color.GREEN);

		text.setEnabled(false);

		jPanel.add(text);
		return jPanel;
	}

	private JPanel panelAddNewExtention() {
		JPanel jPanel = new JPanel(new FlowLayout());
		jPanel.setPreferredSize(new Dimension(500, 100));

		jTextFieldNewExtention = new JTextField(10);

		jComboBoxExtenshions = new JComboBox<String>(fillModel(extentions));
		jComboBoxExtenshions.setVisible(false);

		jButtonAdd = new JButton(ADD_BUTTON);
		jButtonAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jTextFieldNewExtention.getText() != null
						&& !jTextFieldNewExtention.getText().isEmpty()
						&& jCheckBoxSwitcher.isSelected()) {
					String newExtension = jTextFieldNewExtention.getText();
					String have = text.getText();
					if (have.contains(newExtension)) {
						JOptionPane.showMessageDialog(jFrame,
								"This is extension already exists!",
								"Information", JOptionPane.INFORMATION_MESSAGE);

					} else {
						have += ", " + newExtension;
						if (writeNewExtension(newExtension, true)) {
							JOptionPane.showMessageDialog(jFrame,
									"Added success!", "Information",
									JOptionPane.INFORMATION_MESSAGE);
							text.setText(have);
							if(!extentions.isEmpty()){
								extentions.clear();
							}
							SearchEverywhereText
							.getAvailableFileExtentions(extentions);
							jComboBoxExtenshions.setModel(fillModel(extentions));
							jTextFieldNewExtention.setText("");
						}
					}
				} else if (jComboBoxExtenshions.isVisible()
						&& !jCheckBoxSwitcher.isSelected()) {
					int selectedIndex = jComboBoxExtenshions.getSelectedIndex();
					extentions.remove(selectedIndex);
					jComboBoxExtenshions.setModel(fillModel(extentions));
					String currentText = extentions.toString().substring(1,
							extentions.toString().length() - 1);
					text.setText(currentText);
					if (writeNewExtension(currentText, false)) {
						JOptionPane.showMessageDialog(jFrame,
								"Deleted success!", "Information",
								JOptionPane.INFORMATION_MESSAGE);
					}

				} else if ((jTextFieldNewExtention.getText() != null
						&& !jTextFieldNewExtention.getText().isEmpty() && jCheckBoxSwitcher
							.isSelected())
						|| (jTextFieldNewExtention.getText() != null
								&& jTextFieldNewExtention.getText().isEmpty() && jCheckBoxSwitcher
									.isSelected())) {
					JOptionPane
							.showMessageDialog(
									jFrame,
									"Please write new file extention, which you want added!",
									"Information",
									JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		jCheckBoxSwitcher = new JCheckBox(ADD);
		jCheckBoxSwitcher.setSelected(true);
		jCheckBoxSwitcher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jCheckBoxSwitcher.isSelected()) {
					jCheckBoxSwitcher.setText(ADD);
					jButtonAdd.setText(ADD_BUTTON);
					jTextFieldNewExtention.setVisible(true);
					jComboBoxExtenshions.setVisible(false);
				} else {
					jCheckBoxSwitcher.setText(REMOVE);
					jButtonAdd.setText(DELETE_BUTTON);
					jTextFieldNewExtention.setVisible(false);
					jComboBoxExtenshions.setVisible(true);
				}
			}
		});

		jPanel.add(jTextFieldNewExtention);
		jPanel.add(jComboBoxExtenshions);
		jPanel.add(jButtonAdd);
		jPanel.add(jCheckBoxSwitcher);
		return jPanel;
	}

	private boolean writeNewExtension(String newExtention, boolean append) {
		boolean result = false;
		FileWriter fileWriter = null;
		try {
			File file = new File(SearchEverywhereText.AVAILABLE_FILE_EXTENTIONS);
			fileWriter = new FileWriter(file, append);
			if(append){
				fileWriter.write(", " + newExtention);
			}else{
				fileWriter.write(newExtention);
			}
			
			result = true;
		} catch (Exception e) {
			result = false;
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	private String[] listToArray(List<String> strings) {
		String[] s = new String[strings.size()];
		if (strings != null && !strings.isEmpty()) {
			s = strings.toArray(s);
		}
		return s;
	}

	private DefaultComboBoxModel<String> fillModel(List<String> strings) {
		model = new DefaultComboBoxModel<String>();
		for (String current : strings) {
			model.addElement(current);
		}
		return model;
	}

	public static void main(String[] args) throws IOException {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new ManageFileExtention();
			}
		});

		// writeNewExtension("xml");
	}

}