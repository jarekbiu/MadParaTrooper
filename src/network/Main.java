package network;

import java.net.Inet4Address;
import java.net.SocketException;


public class Main {

    public static void main(String[] args) throws SocketException, InterruptedException {
	// write your code here
    	PeerDetector peerDetector = new PeerDetector();
    	peerDetector.Initialize(2);
    	Thread.sleep(100000);
    	System.out.println(peerDetector.GetLocalAddress());
    	for (Inet4Address nAddress : peerDetector.GetPeerAddresses()) {
    		System.out.println(nAddress);
    	}
    	peerDetector.Stop();
    }
}
