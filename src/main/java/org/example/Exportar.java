package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
public class Exportar {
	private File fichjson;
	
	public Exportar() {
		this.fichjson= new File("susursalesExportado.json");
	}
	public static void transformarJSonaXMl(File f) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		JsonParser parser = new JsonParser();
		Sucursales sucursales = new Gson().fromJson(parser.parse(new FileReader(f)), Sucursales.class);
	
		try {
			   JAXBContext jaxbContext = JAXBContext.newInstance(Sucursales.class);
			   Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			   // Indentar el XML resultante
			   jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			   // Escribir el XML resultante a un archivo
			   jaxbMarshaller.marshal(sucursales, new File("C:\\Users\\Guillermo\\Desktop\\AccesoTrabajo2bis\\exportar\\sucursales.xml"));
			} catch (JAXBException e) {
					System.out.println("Fallo en la transformación, revisar el formato del documento");
			   e.printStackTrace();
			}
		System.out.println("Transformacion realizada con exito");
		
	}
	public  void exportaJson() throws IOException {
		
		MongoClient cliente = new MongoClient();
		MongoDatabase db = cliente.getDatabase("2AMT");
		MongoCollection<Document> coleccion = db.getCollection("sucursales");

		File fiche = new File("C:\\Users\\Guillermo\\Desktop\\AccesoTrabajo2bis\\exportar\\sucursales.json");
		FileWriter fic;
		try {
			fic = new FileWriter(fiche);
			BufferedWriter fichero = new BufferedWriter(fic);
			// Recorrer la colección :
			System.out.println(" - ----------------------------------------");
			List<Document> consulta = coleccion.find().into(new ArrayList<Document>());
			for (int i = 0; i < consulta.size(); i++) {
				System.out.println(" Grabar elemento  " + i + ", " + consulta.get(i).toString());
				fichero.write(consulta.get(i).toJson());
				fichero.newLine();
			}
			fichero.close();
		} catch (IOException e) {
			System.out.println("Fallo al exportar, revisar el fichero");
			e.printStackTrace();
		
		
		}
		
		}
}
	



