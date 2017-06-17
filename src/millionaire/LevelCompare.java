package millionaire;

import java.util.Random;

import main.model.User;

/**
 * 
 * @author Jarek-Lab 
 * Compare Level A compare to others
 *
 */
public class LevelCompare {
	private static int soldierNum = 3;
	public String[] ipList = new String[soldierNum];
	public int[] compareResults = new int[soldierNum-1];

	public boolean compareToOthers() {
		User user1 = new User(1, 1, "", "");// 应该通过IP获取到user对象
		for (int i = 0; i < soldierNum; i++) {
			//生成一个随机大的整数和一个随机值，相加得到一个大随机数
			int maxbound=999999999,minbound=10000000;
			Random rd=new Random();
			int bound=rd.nextInt(maxbound)%(maxbound-minbound+1)+minbound;//生成一个较大的整数
			//BigInteger _bound = new BigInteger(9, rd);//用于控制A、B生成的大随机数相差不大，且A的随机数略大于B
			int temp=rd.nextInt(100);
			//BigInteger _temp=new BigInteger(temp+"");
			//BigInteger _Arandom=_bound.add(_temp);
			int _Arandom=bound+temp;
			User user2 = new User(2, 2, "", "");// 应该通过IP获取到user对象
			int MessageAtoB = Millionnaire.step1(user1.getLevel(), user2.getDescription(), _Arandom);
			//trans MessageAtoB to B
			//get return list
			int temp2=rd.nextInt(100);
			int _Brandom=bound-temp2;
			int[] results = Millionnaire.step2(MessageAtoB, user2.getLevel(), user2.getDescription(),_Brandom);
			if(Millionnaire.step3(_Arandom, user1.getLevel(), results)){
				//A的军衔小于或等于B
				compareResults[i]=0;
			}else{
				//A的军衔大于B
				compareResults[i]=1;
			}
		}
		//如果有一个是0，说明A的军衔不是最大的，如果全是1说明A的军衔是最大的
		for(int i=0;i<compareResults.length;i++){
			if(compareResults[i]==0){
				return false;
			}
		}
		return true;
	}

}
