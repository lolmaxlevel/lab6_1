package modules.manages;


import util.ListHolder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Class to save\load files(collection)
 */

public class FileManager {

    public FileManager() {}

    /**
     * saves collection to file
     *

     * @throws JAXBException         exception that risen if something wrong with collection
     * @throws FileNotFoundException exception that risen if something wrong with file(no such file\no permissions...)
     */
    public static void saveToFile() throws JAXBException, FileNotFoundException {
        ListHolder holder = new ListHolder();
        holder.setList(new CollectionManager().getCollection());
        String listXML = marshalIt(holder);
        final OutputStream os = new FileOutputStream("collection.xml");
        final PrintStream printStream = new PrintStream(os);
        printStream.print(listXML);
        printStream.close();
    }
    /**
     * loads collection from file
     * @throws JAXBException exception that risen if something wrong with collection
     * @return collection from file
     */
    public static void loadFromFile() throws JAXBException {
        defaultPath = "collection.xml";
        StringBuilder listXML = new StringBuilder();
        File file = new File(defaultPath);

        try (InputStreamReader in = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))
        {
            int content;
            while ((content = in.read()) != -1) {
                listXML.append((char) content);
            }
        } catch (IOException e) {
            System.out.println("Не удалось открыть файл(проверьте верный ли путь и права доступа к файлу)");
        }
        ListHolder newHolder = (ListHolder) unmarshalIt(ListHolder.class, listXML.toString());
        new CollectionManager().addAll(newHolder.getList());
    }

    public static String marshalIt(Object objectName) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(objectName.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(objectName, writer);

        return writer.toString();

    }

    public static Object unmarshalIt(Class<?> className, String xml) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(className);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(xml);

        return unmarshaller.unmarshal(reader);

    }

    static String defaultPath = "";
}
