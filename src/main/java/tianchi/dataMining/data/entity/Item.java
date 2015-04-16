package tianchi.dataMining.data.entity;

/**
 * item 信息
 * 
 * @author v11
 */
public class Item {
	public Long tid;
	public Long ity;
	public String geo;
	public static Item generate(String str){
		Item item = new Item();
		String v[] = str.split(",");
		if(v.length != 3){
			System.err.println(str);
			return null;
		}
		item.tid = Long.parseLong(v[0]);
		item.ity = Long.parseLong(v[2]);
		item.geo = v[1];
		return item;
	}
	@Override
	public String toString() {
		return "Item [tid=" + tid + ", ity=" + ity + ", geo=" + geo + "]";
	}
	
}
