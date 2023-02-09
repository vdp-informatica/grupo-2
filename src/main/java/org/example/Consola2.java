package org.example;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTabbedPane;

import javax.swing.JLabel;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Consola2 extends JFrame {

	private JPanel contentPane;
	private File sfile;
	Exportar ex = new Exportar();
	private JTabbedPane ventanaPrincipal;
	private JPanel option1, option2, option3;
	private JLabel lbInformacion;
	Importar im = new Importar();
	Convertir cv = new Convertir();
	public Consola2() {
		
		setTitle("Conversor");
	    setSize(470, 420);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    getContentPane().setLayout(null);

		ventanaPrincipal = new JTabbedPane();
		ventanaPrincipal.setBounds(0, 0, 453, 368);
	    getContentPane().add(ventanaPrincipal);
	    
	    option1 = new JPanel();
	    option1.setLayout(null);

		lbInformacion = new JLabel("CONVERSOR JSON-XML");
		lbInformacion.setBounds(140, 44, 363, 13);
	    option1.add(lbInformacion);
		ventanaPrincipal.addTab("Option 1", option1);

		JLabel lbXML_JSON = new JLabel("Pulsa para convertir el fichero XML a un fichero JSON.");
		lbXML_JSON.setBounds(60, 70, 320, 40);
		option1.add(lbXML_JSON);

	    JButton btnXML_JSON = new JButton("XML-JSON");
		btnXML_JSON.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Convertir cv= new Convertir();
	    	}
	    });
		btnXML_JSON.setBounds(160, 110, 100, 21);
	    option1.add(btnXML_JSON);
	    
	    JLabel lbJSON_XML = new JLabel("Pulsa para convertir el fichero JSON a un fichero XML.");
		lbJSON_XML.setBounds(60, 140, 320, 40);
	    option1.add(lbJSON_XML);

		JTabbedPane ventanaConvertir = new JTabbedPane(JTabbedPane.TOP);
		ventanaConvertir.setEnabled(false);

		JButton btnJSON_XML = new JButton("JSON-XML");
		btnJSON_XML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser jf = new JFileChooser();
				jf.showOpenDialog(ventanaConvertir);

				int result = jf.showOpenDialog(Consola2.this);

				if(result==jf.APPROVE_OPTION) {
					sfile= jf.getSelectedFile();
				}
				try {
					ex.transformarJSonaXMl(sfile);
				} catch (JsonSyntaxException e1) {
					System.out.println("Error en la transformación");
					e1.printStackTrace();
				} catch (JsonIOException e1) {
					System.out.println("Error de Json");
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					System.out.println("Fichero Json no encontrado");
					e1.printStackTrace();
				}
			}
		});
		btnJSON_XML.setBounds(160, 180, 100, 21);
		option1.add(btnJSON_XML);

		option3 = new JPanel();
		option3.setLayout(null);

		JLabel lbImportar = new JLabel("IMPORTAR XML-MONGODB");
		lbImportar.setBounds(140, 44, 260, 14);
		option3.add(lbImportar);
		ventanaPrincipal.addTab("Option 2", option3);

		JLabel lbtxtImportar = new JLabel("Importa un fichero XML a una colección de MongoDB.");
		lbtxtImportar.setBounds(50, 70, 340, 40);
		option3.add(lbtxtImportar);
	    	    
		JButton btnImportar = new JButton("IMPORTAR");
		btnImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					im.importar();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnImportar.setBounds(160, 110, 110, 20);
		option3.add(btnImportar);

	    option2 = new JPanel();
	    option2.setLayout(null);

	    JLabel lbEsportar = new JLabel("EXPORTAR MONGODB-XML");
		lbEsportar.setAlignmentX(CENTER_ALIGNMENT);
		lbEsportar.setAlignmentY(CENTER_ALIGNMENT);
		lbEsportar.setBounds(140, 44, 260, 14);
	    option2.add(lbEsportar);
		ventanaPrincipal.addTab("Option 3", option2);
	    
	    JLabel lbtxtExportar = new JLabel("Exporta un fichero XML desde una colección de MongoDB.");
		lbtxtExportar.setBounds(50, 70, 340, 40);
	    option2.add(lbtxtExportar);

		JButton btnExportar = new JButton("EXPORTAR");
		btnExportar.setBounds(160, 110, 110, 20);
		option2.add(btnExportar);

		btnExportar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				try {
					ex.exportaJson();
				} catch (IOException exc) {
					System.out.println("Error en la exportación");
					throw new RuntimeException(exc);
				}
			}
	    });

		JTabbedPane ventanaMostrar = new JTabbedPane(JTabbedPane.TOP);
		ventanaPrincipal.addTab("Option 4", null, ventanaMostrar, null);

		JTextArea txJson = new JTextArea(10, 20);
		txJson.setLineWrap(true);

		JScrollPane scrollPane1 = new JScrollPane(txJson);
		ventanaMostrar.addTab("Json", null, scrollPane1, null);
		txJson.setText(cv.jsonData);

		JTextArea txXML = new JTextArea(10, 20);
		txXML.setLineWrap(true);
		
		JScrollPane scrollPane2 = new JScrollPane(txXML);
		ventanaMostrar.addTab("Xml", null, scrollPane2, null);
		txXML.setText(String.valueOf(Convertir.stringWriter));

	}
}
