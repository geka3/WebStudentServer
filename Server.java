package net.ukr.geka3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket ss = null;
	int port;
	public Server(int port){
		this.port = port;
	}
		
	public void start(){
		
		Init init = new Init();
		init.init();//start server initialization 
		
		try {
			
			
			
			ss = new ServerSocket(port);
			Socket soc = null;
			for(;;){
				soc = ss.accept();
				
				Thread t = new Thread(new HttpThread(soc,init));
				t.start();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
