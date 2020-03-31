package ru.job4j.jdbc;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
public class Entries {
    private List<Entry> entries;

    public Entries() {
    }

    public Entries(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Entry> getEntry() {
        return entries;
    }

    public void setEntry(List<Entry> entries) {
        this.entries = entries;
    }
}
