package tianchi.dataMining.data.method.utp;

import tianchi.dataMining.data.entity.Action;
import tianchi.dataMining.data.entity.TmpInfo;
import tianchi.dataMining.data.entity.UTPair;
import tianchi.dataMining.data.method.UTPairMethod;

public class AvgBuyMethod implements UTPairMethod{

	@Override
	public void setAttribute(TmpInfo tmpInfo, UTPair utp) {
		// TODO Auto-generated method stub
		int pre = -1;
		int cnt = 0;
		for(Action r:tmpInfo.ls){
			if(r.op == 3){
				if(pre != -1){
					utp.avgBuy += (r.dis-pre);
					cnt ++;
				}
				pre = r.dis;
			}
		}
		if(cnt!=0) utp.avgBuy /=cnt;
		else utp.avgBuy = 0;
	}

}
