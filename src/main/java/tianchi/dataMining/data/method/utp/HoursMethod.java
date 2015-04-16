package tianchi.dataMining.data.method.utp;

import tianchi.dataMining.data.entity.Action;
import tianchi.dataMining.data.entity.TmpInfo;
import tianchi.dataMining.data.entity.UTPair;
import tianchi.dataMining.data.method.UTPairMethod;


public class HoursMethod implements UTPairMethod {

	@Override
	public void setAttribute(TmpInfo tmpInfo, UTPair utp) {
		// TODO Auto-generated method stub
		for(Action r : tmpInfo.ls){
			for (int i = 0; i < UTPair.Hours.length; i++) {
				if (r.dis <= UTPair.Hours[i]) {
					utp.hours[i][r.op]++;
				}
			}
		}
	}
}
