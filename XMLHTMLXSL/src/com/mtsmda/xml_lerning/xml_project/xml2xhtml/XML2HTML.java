package com.mtsmda.xml_lerning.xml_project.xml2xhtml;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class XML2HTML {
	
	private static final String XSL_ = "xsl";
	private static final String XML_ = "xml";
	private JTextField textFieldXML;
	private JTextField textFieldXSL;
	
	public XML2HTML() {
		JFrame frame = new JFrame("XML2HTML");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(700, 150));
		
		JPanel panel = new JPanel(new GridLayout(3,2));
		
		JLabel labelXML = new JLabel("Please write path to XML file(file separator / for WINDOWS)");
		textFieldXML = new JTextField(25);
		
		JLabel labelXSL = new JLabel("Please write path to XSL file(file separator / for WINDOWS)");
		textFieldXSL = new JTextField(25);
		
		JButton buttonReset = new JButton("Reset");
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldXML.setText("");
				textFieldXSL.setText("");
			}
		});
		JButton buttonCreate = new JButton("Create");
		buttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String xml = textFieldXML.getText();
				String xsl = textFieldXSL.getText();
				
				if(xml != null && !xml.isEmpty() && xsl != null && !xsl.isEmpty()){
					if(checkFileExtension(xml, XML_) && checkFileExtension(xsl, XSL_)){
						File fileXML = new File(xml);
						File fileXSL = new File(xsl);
						if(fileXML != null && fileXML.exists() && fileXSL != null && fileXSL.exists()){
							TransformXML2XHTML transformXML2XHTML = new TransformXML2XHTML();
							ReturnResult returnResult = transformXML2XHTML.transformXML2HTML(fileXML, fileXSL);
							if(returnResult.getSuccess() == 1 && returnResult.getFilename() != null){
								JOptionPane.showMessageDialog(null, "HTML file success created!\n Path - " + returnResult.getFilename(), "Success!", JOptionPane.INFORMATION_MESSAGE);
							}else{
								JOptionPane.showMessageDialog(null, "HTML file not created!", "Error!", JOptionPane.ERROR_MESSAGE);
							}
						}else{
							JOptionPane.showMessageDialog(null, "File(s) not exists on the path(s)!", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "File Extensions error!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "Write xml and xsl file right!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		panel.add(labelXML);
		panel.add(textFieldXML);
		panel.add(labelXSL);
		panel.add(textFieldXSL);
		panel.add(buttonReset);
		panel.add(buttonCreate);
		
		frame.add(panel);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private boolean checkFileExtension(String fileName, String extension){
		if(fileName.endsWith(extension)){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				new XML2HTML();
			}
		});
	}
}