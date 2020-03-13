package ru.job4j.xslt;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;

/**
 *Change input xml file using xslt
 */
public class ConvertXSQT {
    public static void convert(String source, String dest, String scheme) {
        try (FileInputStream inScheme = new FileInputStream(scheme)) {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(inScheme));
            try (FileInputStream inSource = new FileInputStream(source)) {
                transformer.transform(
                        new StreamSource(inSource),
                        new StreamResult(dest)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
