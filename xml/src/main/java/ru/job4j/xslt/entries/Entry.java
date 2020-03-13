package ru.job4j.xslt.entries;

import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

public class Entry {
    @XmlElement
    private int field;
    public Entry setField(int field) {
        this.field = field;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entry entry = (Entry) o;
        return field == entry.field;
    }

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }
}
