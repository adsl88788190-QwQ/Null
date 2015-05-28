import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
class server {
	public static void main(String[] args){
		ServerSocket ss;
		String msg="";
		String ip="172.16.94.16";
		int port=7223;
		try{
			ss= new ServerSocket(port);
			System.out.println("waitting...");
			Socket s=ss.accept();
			System.out.println("client connected");
			//DataInputStream in = new DataInputStream(s.getInputStream());
			//msg=in.readUTF();
			//DataOutputStream out = new DataOutputStream(s.getOutputStream());
		//	out.writeUTF("Get!!");
			//System.out.println(msg);
			ss.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}