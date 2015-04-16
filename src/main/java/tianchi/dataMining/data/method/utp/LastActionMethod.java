package tianchi.dataMining.data.method.utp;

import tianchi.dataMining.data.entity.Action;
import tianchi.dataMining.data.entity.TmpInfo;
import tianchi.dataMining.data.entity.UTPair;
import tianchi.dataMining.data.method.UTPairMethod;


/**
 * 最后一次的操作数的distance值
 * 初始化值为-1，即没有操作，值就为-1
 * @author v11
 */
public class LastActionMethod implements UTPairMethod{

	@Override
	public void setAttribute(TmpInfo tmpInfo, UTPair utp) {
		int cnt=0;
		boolean mk[] = new boolean[utp.lastAction.length];
		for(int i=0;i<utp.lastAction.length;i++) utp.lastAction[i] = -1;
		for(Action r:tmpInfo.ls){
			if(!mk[r.op]){
				cnt++;
				mk[r.op] = true;
				utp.lastAction[r.op] = r.dis;
			}
			if(cnt == 4) break;
		}
	}

}
