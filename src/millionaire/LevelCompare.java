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
		User user1 = new User(1, 1, "", "");// Ӧ��ͨ��IP��ȡ��user����
		for (int i = 0; i < soldierNum; i++) {
			//����һ��������������һ�����ֵ����ӵõ�һ���������
			int maxbound=999999999,minbound=10000000;
			Random rd=new Random();
			int bound=rd.nextInt(maxbound)%(maxbound-minbound+1)+minbound;//����һ���ϴ������
			//BigInteger _bound = new BigInteger(9, rd);//���ڿ���A��B���ɵĴ������������A��������Դ���B
			int temp=rd.nextInt(100);
			//BigInteger _temp=new BigInteger(temp+"");
			//BigInteger _Arandom=_bound.add(_temp);
			int _Arandom=bound+temp;
			User user2 = new User(2, 2, "", "");// Ӧ��ͨ��IP��ȡ��user����
			int MessageAtoB = Millionnaire.step1(user1.getLevel(), user2.getDescription(), _Arandom);
			//trans MessageAtoB to B
			//get return list
			int temp2=rd.nextInt(100);
			int _Brandom=bound-temp2;
			int[] results = Millionnaire.step2(MessageAtoB, user2.getLevel(), user2.getDescription(),_Brandom);
			if(Millionnaire.step3(_Arandom, user1.getLevel(), results)){
				//A�ľ���С�ڻ����B
				compareResults[i]=0;
			}else{
				//A�ľ��δ���B
				compareResults[i]=1;
			}
		}
		//�����һ����0��˵��A�ľ��β������ģ����ȫ��1˵��A�ľ���������
		for(int i=0;i<compareResults.length;i++){
			if(compareResults[i]==0){
				return false;
			}
		}
		return true;
	}

}
