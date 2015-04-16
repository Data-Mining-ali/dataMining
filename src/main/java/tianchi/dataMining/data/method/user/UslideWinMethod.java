package tianchi.dataMining.data.method.user;

import java.util.HashSet;
import java.util.Set;

import tianchi.dataMining.data.entity.Action;
import tianchi.dataMining.data.entity.TmpInfo;
import tianchi.dataMining.data.entity.User;
import tianchi.dataMining.data.method.UserMethod;


public class UslideWinMethod implements UserMethod{

	@Override
	public void setAttribute(TmpInfo tmpInfo, User u) {
		// TODO Auto-generated method stub
		Set<Long> st = new HashSet<Long>();
		int k=0;
		for(int t:User.SlideWins){
			int c = 0;
			double sum = 0;
			for(int i=0;i<30;i++){
				int cnt = 0;
				st.clear();
				for(Action r:tmpInfo.ls){
					if(r.dis >=i*24 && r.dis<(i+1)*24 && r.op == 3){
						st.add(r.tid);
					}
					if(r.dis >=(i+1)*24 && r.dis<(i+t+1)*24 && st.contains(r.tid)){
						cnt ++;
					}
				}
				if(cnt!=0){
					sum += (1.0*st.size()/cnt);
					c++;
				}
			}
			if(c!=0){
				u.slideWin[k++] = sum/c;
				System.out.println(sum/c);
			}
		}
		
	}

}
