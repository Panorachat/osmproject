import java.util.ArrayList;

public class Way {
	private long id;
    private ArrayList<String> tagList = new ArrayList<String>();
    private ArrayList<String> valueList = new ArrayList<String>();
    private ArrayList<Long> refList = new ArrayList<Long>();

    public Way(long id) {
        this.id = id;
    }

    public void addRef(long ref) {
        this.refList.add(ref);
    }

    public void addTag(String e) {
        this.tagList.add(e);
    }

    public void addValue(String e) {
        this.valueList.add(e);
    }

    public ArrayList<Long> getRef() {
        return refList;
    }

    public ArrayList<String> getTag() {
        return tagList;
    }

    public ArrayList<String> getValue() {
        return valueList;
    }

    public long getId() {
        return this.id;
    }

    public long getRef(int i) {
        return refList.get(i);
    }

    public String getTag(int i) {
        return tagList.get(i);
    }

    public String getValue(int i) {
        return valueList.get(i);
    }

    public int getRefSize() {
        return this.refList.size();
    }

    public int getTagSize() {
        return this.tagList.size();
    }
    
    public int getValueSize() {
        return this.valueList.size();
    }
}