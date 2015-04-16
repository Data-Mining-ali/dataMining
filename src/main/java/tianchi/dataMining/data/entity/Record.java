package tianchi.dataMining.data.entity;


/**
 * 每次操作的一条记录
 * 
 * @author v11
 */
public class Record implements Comparable<Record>{
	public Long uid;
	public Long tid;
	/** 操作类型,包括浏览、收藏、加购物车、购买，对应取值分别是0、1、2、3。*/
	public int op;
	/**　用前缀来判断地理位置 */
	public String geo;
	/** 商品类型*/
	public Long ity;
	/** 距离最后一天多少小时 */
	public int dis;
	public Record(){}
	public static Record generate(String str){
		Record r = new Record();
		String v[] = str.split(",");
		if(v.length != 6){
			System.err.println(str);
			return null;
		}
		for(int i=0;i<v.length;i++) v[i] = v[i].trim();
		r.uid = Long.parseLong(v[0]);
		r.tid = Long.parseLong(v[1]);
		r.op = Integer.parseInt(v[2]);
		r.geo = v[3];
		r.ity = Long.parseLong(v[4]);
		r.dis = Integer.parseInt(v[5]);
		return r;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(uid).append(',').append(tid).append(',').append(op)
		.append(',').append(geo).append(',').append(ity).append(',').append(dis);
		return str.toString();
	}
	@Override
	public int compareTo(Record o) {
		// TODO Auto-generated method stub
		if(this.uid>o.uid) return 1;
		else if(this.uid<o.uid) return -1;
		else{
			if(this.tid>o.tid) return 1;
			else if(this.tid<o.tid) return -1;
			return 0;
		}
	}
	
}
