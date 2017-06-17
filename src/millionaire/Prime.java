package millionaire;

import java.util.Random;

public class Prime {

	private static final int ORDER = 10000;// �������������
	private static final int MIN = 1000;// ѡ������������Сֵ


	public static void main(String[] args) {
		int x = getPrime();
		boolean flag = true;
		for (int i = 0; i < 10; i++) {
			if (!isPrime(x)) {
				flag = false;
				break;
			}
		}
		if (flag) {
			System.out.println(x + ":��������ͨ������");
		} else {
			System.out.println(x + ":��������");
		}

	}

	/**
	 * ���ѡ��һ������
	 * 
	 * @return
	 */
	static int getRandom() {
		int x = 3;
		Random rd = new Random();
		do {
			x = rd.nextInt(ORDER);
		} while (x < MIN || x % 2 == 0);
		return x;
	}

	/**
	 * ��ȡһ����������Ҽ���Ƿ�Ϊ����
	 * 
	 * @return
	 */
	static int getPrime() {
		int x = 0;
		while (x % 2 == 0 || !isPrime(x)) {
			x = getRandom();
		}
		return x;
	}

	/**
	 * ��֤һ�����Ƿ�Ϊ��������n-1��дΪ2^k * m����ʽ������m����������{2,...,n-1}�����ѡȡһ������a;
	 * 
	 * @param n
	 * @return
	 */
	static boolean isPrime(int n) {
		// n-1 ��2���ݱ�ʾ
		int[] arr = intTOIndex(n - 1);
		int k = arr[0];
		int m = arr[1];

		// ��{2,...,n-1}���ѡ��һ������a
		Random r = new Random();
		int a = 0;
		do {
			a = r.nextInt(n - 1);
		} while (a < 2);

		// b=a^m%n
		int b = Square_and_Mutiply(a, m, n);
		if (b == 1) {
			return true;
		}
		for (int i = 0; i < k; i++) {
			if (b == (n - 1)) {
				return true;
			} else {
				b = (b * b) % n;
			}
		}
		return false;
	}

	/**
	 * ��һ������Ϊ2^k * m����ʽ������m������
	 * 
	 * @param n
	 * @return
	 */
	static int[] intTOIndex(int n) {
		int[] arr = new int[2];
		int k = 0;

		int x;
		// ��nΪ������ֹͣѭ��
		do {
			k++;
			// n����1λ
			n >>= 1;
			x = n & 1;
		} while (x == 0);
		arr[0] = k;
		arr[1] = n;
		return arr;
	}

	/**
	 * ƽ��-�˷�����ָ��ģ���� a^m % n
	 * 
	 * @param a
	 * @param m
	 * @param n
	 * @return
	 */
	static int Square_and_Mutiply(int a, int m, int n) {
		int d = 1;

		// ��mת��Ϊ��������
		byte[] bm = getByte(m);
		for (int i = 0; i < bm.length; i++) {
			d = (d * d) % n;
			// ������1����asciI���49
			if (bm[i] == 49) {
				d = (d * a) % n;
			}
		}
		return d;
	}

	/**
	 * ����תΪ������
	 * 
	 * @param m
	 * @return
	 */
	static byte[] getByte(int m) {
		String sb = "";
		while (m > 0) {
			sb = (m % 2) + sb;
			m = m / 2;
		}
		return sb.getBytes();
	}

}
