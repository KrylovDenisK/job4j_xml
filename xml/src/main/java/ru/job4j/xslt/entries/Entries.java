package ru.job4j.xslt.entries;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Entries {
   @XmlElement
    private List<Entry> entry;
    public void setEntries(List<Entry> entries) {
        this.entry = entries;
    }
}
