package network;

import java.io.IOException;
import java.net.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ningchen Wang on 6/11/2017.
 */
public class PeerDetectorServer extends Thread {
    private Inet4Address localAddress;
    private Set<Inet4Address> peerAddresses;
    private DatagramSocket serverSocket;
    private DatagramSocket clientSocket;
    private int networkPrefixLength;

    boolean runningFlag = true;

    public PeerDetectorServer(Inet4Address localAddress, short networkPrefixLength) throws SocketException {
        this.localAddress = localAddress;
        peerAddresses = new HashSet<Inet4Address>();
        serverSocket = new DatagramSocket(9876, localAddress);
        clientSocket = new DatagramSocket(9875, localAddress);
        this.networkPrefixLength = networkPrefixLength;
    }

    public void run() {
        try {
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            DatagramPacket broadcastPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), 9876);
            serverSocket.send(broadcastPacket);
            while (runningFlag) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                InetAddress IPAddress = receivePacket.getAddress();
                System.out.println("RECEIVED: " + IPAddress);
                int port = receivePacket.getPort();
                if ((port == 9875 || port == 9876) && IPAddress instanceof Inet4Address && sameNetwork((Inet4Address)IPAddress)) {
                	if (!IPAddress.toString().equals(localAddress.toString())) {
                	    if (port == 9876){
                            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
                            clientSocket.send(sendPacket);
                        }
                		if (!peerAddresses.contains((Inet4Address)IPAddress)){
                            peerAddresses.add((Inet4Address) IPAddress);
                        }
                	}
                }
            }
        }
        catch (SocketException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void kill() {
    	serverSocket.close();
        runningFlag = false;
    }

    public Inet4Address GetLocalAddress() {
        return this.localAddress;
    }
    
    public Inet4Address[] GetPeerAddresses() {
        return this.peerAddresses.toArray(new Inet4Address[peerAddresses.size()]);
    }
    
    private boolean sameNetwork(Inet4Address IPAddress) {
    	byte[] ipaddr = IPAddress.getAddress();
    	byte[] localaddr = localAddress.getAddress();
    	final int bits = networkPrefixLength & 7;
    	final int bytes = networkPrefixLength >>> 3;
    	for (int i = 0; i < bytes; i++) {
    		if (ipaddr[i] != localaddr[i]) {
    			return false;
    		}
    	}
    	final int shift = 8 - bits;
    	if (bits != 0 && ipaddr[bytes] >>> shift != localaddr[bytes] >>> shift) {
    		return false;
    	}
    	return true;
    }
}
