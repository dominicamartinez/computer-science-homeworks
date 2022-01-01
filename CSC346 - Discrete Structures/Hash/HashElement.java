public class HashElement {
	private String key;
	private Object value;

	public HashElement(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	public Object getValue() { return value; }
	public String getKey() { return key; }
}