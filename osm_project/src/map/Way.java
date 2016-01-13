package map;
import java.util.ArrayList;

public class Way {
	private long id;
	private ArrayList<String> tagList = new ArrayList<String>();
	private ArrayList<String> valueList = new ArrayList<String>();
	private ArrayList<Long> refList = new ArrayList<Long>();
	private int altitude = 0;

	public Way(long id, ArrayList<String> tagin) {
		this.id = id;
		String tag = "";
		if(contient("building",tagin)|| contient("wall",tagin)){
			this.altitude=1;
		}
	}
	public boolean contient(String str , ArrayList<String> tag){
		for(int i=0;i<tag.size();i++){
			if(tag.get(i)==str){
				return true;
			}
		}
		return false;
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

	public int getAltitude() {
		return this.altitude;
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