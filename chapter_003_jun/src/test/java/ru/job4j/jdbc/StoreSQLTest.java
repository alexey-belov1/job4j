package ru.job4j.jdbc;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StoreSQLTest {
    private final File scheme = new File("scheme.xsl");
    private final String path = System.getProperty("java.io.tmpdir");

    @Rule
    public TemporaryFolder folder = new TemporaryFolder(new File(path));

    @Test
    public void checkClassesFromJDBC() throws Exception {
        Config config = new Config();
        config.init();
        StoreSQL store = new StoreSQL(config);
        store.init();
        store.createTableEntry();
        store.generate(100);
        List<Entry> entries = store.load();

        File source = new File(folder.getRoot() + "/" + "out.xml");
        File dest = new File(folder.getRoot() + "/" + "outTrans.xml");
        new StoreXML(source).save(entries);
        new ConvertXSQT().convert(source, dest, scheme);
        SAXPars sax = new SAXPars();
        sax.parse(dest);
        assertThat(sax.getSum(), is(5050L));
    }
}
