package org.nerdpower.tabula;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nerdpower.tabula.extractors.BasicExtractionAlgorithm;
import org.nerdpower.tabula.writers.CSVWriter;
import org.nerdpower.tabula.writers.JSONWriter;
import org.nerdpower.tabula.writers.TSVWriter;

public class TestWriters {
    
    private static final String EXPECTED_CSV_WRITER_OUTPUT = "\"ABDALA de MATARAZZO, Norma Amanda \",\"Frente Cívico por Santiago \",\"Santiago del Estero \",AFIRMATIVO";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    //static final String 
    
    private Table getTable() throws IOException {
        Page page = Util.getAreaFromFirstPage("src/test/resources/org/nerdpower/tabula/argentina_diputados_voting_record.pdf", 269.875f, 12.75f, 790.5f, 561f);
        BasicExtractionAlgorithm bea = new BasicExtractionAlgorithm();
        Table table = bea.extract(page).get(0);
        return table;
    }

    @Test
    public void testCSVWriter() throws IOException {
        Table table = this.getTable();
        StringBuilder sb = new StringBuilder();
        (new CSVWriter()).write(sb, table);
        String s = sb.toString();
        String[] lines = s.split("\\r?\\n");
        assertEquals(lines[0], EXPECTED_CSV_WRITER_OUTPUT);
    }
    
    @Test
    public void testTSVWriter() throws IOException {
        Table table = this.getTable();
        StringBuilder sb = new StringBuilder();
        (new TSVWriter()).write(sb, table);
        String s = sb.toString();
        System.out.println(s);
        //String[] lines = s.split("\\r?\\n");
        //assertEquals(lines[0], EXPECTED_CSV_WRITER_OUTPUT);
    }
    
    @Test
    public void testJSONWriter() throws IOException {
        // TODO add assertions
        Table table = this.getTable();
        StringBuilder sb = new StringBuilder();
        (new JSONWriter()).write(sb, table);
        String s = sb.toString();
        System.out.println(s);
    }

}
