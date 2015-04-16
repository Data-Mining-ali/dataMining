package tianchi.dataMining.data.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Action implements Comparable<Action>{
	public int dis;
	public int op;
	public long tid;
	public Action(int dis,int op){this.dis = dis;this.op = op;}
	public Action(int dis,int op,long tid){this.dis = dis;this.op = op;this.tid=tid;}
	@Override
	public String toString() {
		return "Action [dis=" + dis + ", op=" + op + "]";
	}

	@Override
	public int compareTo(Action o) {
		// TODO Auto-generated method stub
		return this.dis-o.dis;
	}
	public static void main(String[] args) {
		List<Action> ls = new ArrayList<Action>();
		ls.add(new Action(0, 0));
		ls.add(new Action(1, 0));
		Collections.sort(ls);
		System.out.println(ls);
	}
}
