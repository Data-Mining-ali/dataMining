package tianchi.dataMining.data.method.utp;

import tianchi.dataMining.data.entity.Action;
import tianchi.dataMining.data.entity.TmpInfo;
import tianchi.dataMining.data.entity.UTPair;
import tianchi.dataMining.data.method.UTPairMethod;



public class LastDupActionMethod implements UTPairMethod{

	@Override
	public void setAttribute(TmpInfo tmpInfo, UTPair utp) {
		int cnt=0;
		int mk[] = new int[utp.lastDupAction.length];
		for(int i=0;i<utp.lastDupAction.length;i++) utp.lastDupAction[i] = -1;
		for(Action r:tmpInfo.ls){
			if(mk[r.op] == 0){
				mk[r.op] = 1;
			}
			else if(mk[r.op] == 1){
				cnt++;
				mk[r.op] = 2;
				utp.lastDupAction[r.op] = r.dis;
			}
			if(cnt == 4) break;
		}
	}

}
