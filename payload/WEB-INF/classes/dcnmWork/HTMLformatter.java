package dcnmWork;
import java.io.*;
import java.util.ArrayList;

public class  HTMLformatter{

public String Result = "";

public  HTMLformatter(ArrayList<ArrayList> matrix){
int x = 0;
int y = 0;
String matrixout = "";

try {
	matrixout += "<table border=\"1\">";
		for(x = 0; x < matrix.size();x++){
		String rowdata = "";
                        for(y = 0; y < matrix.get(x).size(); y++){
                                rowdata += "<td>" + (String)((ArrayList)matrix.get(x)).get(y) + "</td>";

                        }
                        matrixout += "<tr>" + rowdata + "</tr>\r\n";
                }
        matrixout += "</table>";
	Result=matrixout;	


}catch (Exception e){
e.printStackTrace();

}
}
}
