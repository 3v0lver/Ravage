package dcnmWork;
import java.io.*;

public class clexecute{

public String Result = "";

public clexecute(String command){

try {
	Process p = Runtime.getRuntime().exec("cmd.exe /c " + command );
	OutputStream os = p.getOutputStream();
	InputStream in = p.getInputStream();
	DataInputStream dis = new DataInputStream(in);
	String disr = dis.readLine();

	while ( disr != null ) {
	Result = Result + disr + "\r\n";
	disr = dis.readLine();
	}

}catch (Exception e){
e.printStackTrace();

}
}
}
