package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public class Convertir {
        JAXBContext jaxbContext;
        static String jsonData;
        static StringWriter stringWriter;
    {
        try {
            jaxbContext = JAXBContext.newInstance(Sucursales.class);

            //Convertimos el fichero XML a Objeto
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Sucursales sucursales = (Sucursales) unmarshaller.unmarshal(new File("C:\\Users\\Guillermo\\Desktop\\grupo-2-main\\src\\sucursales.xml"));

            //Convertimos el Objeto a XML
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            stringWriter = new StringWriter();
            marshaller.marshal(sucursales, stringWriter);
            marshaller.marshal(sucursales, new File("sucursales.xml"));

            ObjectMapper mapper = new ObjectMapper();

            try {
                //Le damos un formato bonito de Json para que sea fácil de leer para el usuario
                jsonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sucursales);

                try {
                    //Creamos el fichero Json mapeado de las clases Sucursales, Sucursal y Cuenta
                    mapper.writeValue(new File("sucursales.json"), sucursales);


                } catch (IOException e) {
                    System.out.println("No se encuentra el fichero");
                    throw new RuntimeException(e);
                }
            } catch (JsonProcessingException e) {
                System.out.println("Error en el mapeo del Json, revisar formato");
                throw new RuntimeException(e);
            }
        } catch (JAXBException ex) {
            System.out.println("Error en la conversión del fichero a Objeto, revisar el mapeo");
            throw new RuntimeException(ex);
        }
    }


}
