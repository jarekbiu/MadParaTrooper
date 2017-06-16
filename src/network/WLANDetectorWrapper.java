package network;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

public class WLANDetectorWrapper {
	static {
	    try{
			System.loadLibrary("WLANControl");
		} catch (Exception e){
	    	e.printStackTrace();
		}
	}
	public native static int StartUpDetect(int index);
	public native static int GetLocalAddress(); 
	public native static int StopDetect();
	public static void main (String[] args) throws IOException {
		StartUpDetect(2);
		int ifIndex = GetLocalAddress();
		NetworkInterface intf = NetworkInterface.getByIndex(ifIndex);
		System.out.println(intf.getName() + ':' + intf.getDisplayName() + ' ' + intf.getIndex());
		Enumeration<InetAddress> iNetAddresses = NetworkInterface.getByIndex(ifIndex).getInetAddresses();
		for (InetAddress n : Collections.list(iNetAddresses)) {
			if (n instanceof Inet4Address) {
				System.out.println(n);
			}
		}
		System.in.read();
		StopDetect();
	}
}
