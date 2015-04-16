package tianchi.dataMining.data.method.utp;

import tianchi.dataMining.data.entity.Action;
import tianchi.dataMining.data.entity.TmpInfo;
import tianchi.dataMining.data.entity.UTPair;
import tianchi.dataMining.data.method.UTPairMethod;

public class IntervalMethod implements UTPairMethod{

	@Override
	public void setAttribute(TmpInfo tmpInfo, UTPair utp) {
		// TODO Auto-generated method stub
		int pre[] = new int [UTPair.Intervals.length];
		for(int i=0;i<pre.length;i++) pre[i] = -1;
		for(Action r : tmpInfo.ls){
			for (int i = 0; i < UTPair.Intervals.length; i++) {
				if(pre[i] == -1 || (r.dis - pre[i]>= UTPair.Intervals[i])){
					pre[i] = r.dis;
					utp.intervals[i]++;
				}
			}
		}
	}
}
