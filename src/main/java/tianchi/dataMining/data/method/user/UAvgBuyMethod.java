package tianchi.dataMining.data.method.user;

import tianchi.dataMining.data.entity.Action;
import tianchi.dataMining.data.entity.TmpInfo;
import tianchi.dataMining.data.entity.User;
import tianchi.dataMining.data.method.UserMethod;


public class UAvgBuyMethod implements UserMethod{

	@Override
	public void setAttribute(TmpInfo tmpInfo, User u) {
		// TODO Auto-generated method stub
		int pre = -1;
		int cnt = 0;
		for(Action r:tmpInfo.ls){
			if(r.op == 3){
				if(pre != -1){
					u.avgBuy += (r.dis-pre);
					cnt ++;
				}
				pre = r.dis;
			}
		}
		if(cnt!=0) u.avgBuy /=cnt;
		else u.avgBuy = 0;
	}

}
