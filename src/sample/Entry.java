// Juan Diego Solorzano 18151
// Extraido de https://www.sanfoundry.com/java-program-implement-splay-tree/

public class Entry implements Comparable<Entry> {
    private String key;
    private String value;

    @Override
    public String toString() {
        return "Entry{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }

    public Entry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }


    @Override
    public int compareTo(Entry that) {
        if (this.key.compareTo(that.key) < 0) {
            return 1;
        } else if (this.key.compareTo(that.key) > 0) {
            return -1;
        } else {
            return 0;
        }
    }


}