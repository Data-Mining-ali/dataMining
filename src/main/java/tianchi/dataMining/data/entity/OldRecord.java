package tianchi.dataMining.data.entity;

import java.util.Date;



import tianchi.dataMining.utility.Contants;
import tianchi.dataMining.utility.TimeTool;

/**
 * 原始的record类
 * 
 * @author v11
 */
public class OldRecord implements Comparable<OldRecord>{
	public Long uid;
	public Long tid;
	/** 操作类型,包括浏览、收藏、加购物车、购买，对应取值分别是1、2、3、4。*/
	public int op;
	/**　用前缀来判断地理位置 */
	public String geo;
	/** 商品类型*/
	public Long ity;
	public Date time;
	/** 距离最后一天多少小时 */
	public int dis;
	
	public OldRecord(){}
	public static OldRecord generate(String str){
		OldRecord r = new OldRecord();
		String v[] = str.split(",");
		if(v.length != 6){
			System.err.println(str);
			return null;
		}
		for(int i=0;i<v.length;i++) v[i] = v[i].trim();
		r.uid = Long.parseLong(v[0]);
		r.tid = Long.parseLong(v[1]);
		r.op = Integer.parseInt(v[2]) - 1;
		r.geo = v[3];
		r.ity = Long.parseLong(v[4]);
		r.time = TimeTool.getTime(v[5]);
		r.dis = (int) ((TimeTool.getTime(Contants.time18).getTime() - r.time.getTime())/1000/3600);
		return r;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(uid).append(',').append(tid).append(',').append(op)
		.append(',').append(geo).append(',').append(ity).append(',').append(dis);
		return str.toString();
	}

	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Long getTid() {
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}
	public int getOp() {
		return op;
	}
	public void setOp(int op) {
		this.op = op;
	}
	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		this.geo = geo;
	}
	public Long getIty() {
		return ity;
	}
	public void setIty(Long ity) {
		this.ity = ity;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public int compareTo(OldRecord o) {
		// TODO Auto-generated method stub
		if(this.uid>o.uid) return 1;
		else if(this.uid<o.uid) return -1;
		return 0;
	}
}
