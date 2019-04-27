// Juan Diego Solorzano 18151
import static org.junit.Assert.*;

public class SplayTreeTest {

    SplayTree splayTree = new SplayTree();
    @org.junit.Test
    public void insert() {
        splayTree.insert(new Entry("Hola", "Hello"));

    }

    @org.junit.Test
    public void find() {
        splayTree.insert(new Entry("Hola", "Hello"));
        String a = splayTree.find("Hola").toString();
        assertEquals("Hello", a);
    }
}