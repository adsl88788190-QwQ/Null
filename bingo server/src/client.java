import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
class client {
	public static void main(String[] args){
		Socket s;
		String msg="";
		String ip="172.16.94.16";
		int port=7223;
		try{//192.192.122.202
			s=new Socket(ip,port);
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			out.writeUTF("³¢®a»¨");
			DataInputStream in = new DataInputStream(s.getInputStream());
			msg=in.readUTF();
			System.out.println(msg);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}