package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Importar {
	public void importar() throws IOException {
		
	try {
		//Leemos el fichero Json
		FileReader fr = new FileReader("sucursales.json");
		BufferedReader bf = new BufferedReader(fr);

		//Creamos el objeto clliente para leer la coleccion de MongoDb
		MongoClient cliente = new MongoClient();
		//Leemos la base de datos de MongoDb
		MongoDatabase db = cliente.getDatabase("2AMT");
		//Leemos la colección de sucursales
		MongoCollection<Document> coleccion = db.getCollection("sucursales");

		String cadenajson;
		//Recorremos el fichero y lo parseamos para tenerlo en un objeto
		while ((cadenajson = bf.readLine()) != null) {
			System.out.println(cadenajson);
			Document docu = new Document(org.bson.Document.parse(cadenajson));
			coleccion.insertOne(docu);
		}

	} catch (FileNotFoundException e) {
		System.out.println("Archivo no encontrado");
		e.printStackTrace();
	}
}
}

