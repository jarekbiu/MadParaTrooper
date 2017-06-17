package millionaire;

import rsa.RSA;

/**
 * Created by rui on 2017/6/17.
 */
public class Millionnaire {
	/**
	 * 
	 * @param rank
	 *            A�ľ���
	 * @param publicKey
	 *            B�Ĺ�Կ
	 * @param randomnum
	 *            A���ɵĴ������ �ü��ܺ�Ĵ��������ȥA�ľ���
	 * @return ���ؽ��
	 */
	public static int step1(int rank, String publicKey, int randomnum) {
		// ��A���ɵĴ��������B�Ĺ�Կ����
		int c = RSA.encryptByPublicKey(randomnum, publicKey);// ��B�Ĺ�Կ����
		return c - rank;
	}

	/**
	 * 
	 * @param mA
	 *            A����B����Ϣ�������Ǵ���������ʱ��Ϊint
	 * @param rank
	 *            ����
	 * @param privateKey
	 *            ˽Կ
	 * @param bound
	 *            A���ɵĴ�������Χ
	 * @return
	 */
	public static int[] step2(int mA, int rank, String privateKey, int _Bbound) {
		// BigInteger a=bound.nextProbablePrime();//��ط��������⣬Ӧ���Ǳ�bound��Сһ��Ĵ��������
		while (!(Prime.isPrime(_Bbound))) {
			_Bbound--;
		}
		int p = _Bbound;// B���ɵĴ��������
		int[] y = new int[100];// ʹ����Կ���ܺ��100����
		int[] z = new int[100];
		int[] result = new int[101];
		// ��1<=u<=100������mA+uͨ����Կ���ܺ��ֵ
		for (int u = 1; u <= 100; u++) {
			y[u - 1] = RSA.decryptByPrivateKey(mA + u, privateKey);
		}
		boolean pnum = false;// ����P�Ƿ����Ҫ��
		while (!pnum) {// if pnum=false ,choose a p
			// p=Prime.getPrime();//choose a big random number and smaller than
			// x
			for (int u = 1; u <= 100; u++) {
				z[u - 1] = y[u - 1] / p;// y mod p
			}
			// ��֤z
			for (int u = 1; u <= 100; u++) {// validate z
				if (z[u - 1] < 0 || z[u - 1] >= p - 1) {// validate for every u
														// 0<z<p-1
					pnum = false;
					// �����µ�����
					while (!(Prime.isPrime(p--))) {
						p--;
					}
				}
				for (int v = 1; v <= 100; v++) {// validata for each u!=v
												// |z[u-1]-z[v-1]|>=2
					if (u != v) {
						if (Math.abs(z[u - 1] - z[v - 1]) < 2) {
							pnum = false;
							// �����µ�����
							while (!(Prime.isPrime(p--))) {
								p--;
							}
						}
					}
				}
			}
			pnum = true;
		}
		// ���ɽ������
		for (int u = 1; u < 101; u++) {
			if (u <= rank) {
				result[u - 1] = z[u - 1];
			}
			result[u - 1] = z[u - 1] + 1;
		}
		result[100] = p;
		return result;

	}

	/**
	 * �������ֵΪtrue��˵��A�ľ���С�ڻ����B���������ֵΪfalse��˵��A�ľ��δ���B
	 * 
	 * @param x
	 * @param rank
	 * @param z
	 * @return
	 */
	public static boolean step3(int x, int rank, int[] z) {
		if ((z[rank - 1] - x) / z[100] == 0) {
			// RankA<=RankB
			return true;
		} else {
			// RankA>RankB
			return false;
		}
	}

}
