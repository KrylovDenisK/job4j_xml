package ru.job4j.xslt;

import ru.job4j.xslt.sax.OperationXML;

/**
 * Launch Data Manipulation Application
 */
public class ApplicationLaunch {
    public static void main(String...arqs) {
        Config config = new Config();
        config.init();
        StoreXML storeXML = new StoreXML("./xml/1.xml");
        try (StoreSQL storeSQL = new StoreSQL(config)) {
            storeSQL.generate(20);
            storeXML.save(storeSQL.load());
            ConvertXSQT.convert("./xml/1.xml", "./xml/2.xml", "./xml/scheme.xsl");
            OperationXML operationXML = new OperationXML();
            operationXML.parse("./xml/2.xml");
            System.out.println(operationXML.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
