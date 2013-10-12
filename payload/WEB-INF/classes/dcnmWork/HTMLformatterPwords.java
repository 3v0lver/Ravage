package dcnmWork;

import java.util.ArrayList;

public class HTMLformatterPwords
{

    public HTMLformatterPwords(ArrayList arraylist)
    {
        Result = "";
        boolean flag = false;
        boolean flag1 = false;
        String s = "";
        String s1 = "";
	int colcount = 0;
        try
        {
            s1 = (new StringBuilder()).append(s1).append("<table border=\"0\">").toString();
            for(int i = 0; i < arraylist.size(); i++)
            {
                String s2 = "";
                for(int j = 0; j < ((ArrayList)arraylist.get(i)).size(); j++)
                    if(j == 1)
                    {
                        test test1 = new test((String)((ArrayList)arraylist.get(i)).get(j));
                        s2 = (new StringBuilder()).append(s2).append(test1.Result).toString();
                    } else
                    {
			if ((((ArrayList)arraylist.get(i)).get(j))!=null){
			if ((((ArrayList)arraylist.get(i)).get(j)).equals("USERNAME") || (((ArrayList)arraylist.get(i)).get(j)).equals("userid")){
                                        colcount++;
                                                if (colcount==1){
                        		s2 = (new StringBuilder()).append(s2).append("<td colspan=\"2\"><b>DCNM Credentials</b></td>").toString();
                                                }else if(colcount==2){
                        		s2 = (new StringBuilder()).append(s2).append("<td colspan=\"2\"><b>Device Credentials</b></td>").toString();
                                                }else if(colcount==3){
                        		s2 = (new StringBuilder()).append(s2).append("<td colspan=\"2\"><b>Enable Credentials</b></td>").toString();
					}
                                        }else{
                                        s2 = (new StringBuilder()).append(s2).append("<td>").append((String)((ArrayList)arraylist.get(i)).get(j)).append("</td>").toString();
	
					}
					}else{
                        		s2 = (new StringBuilder()).append(s2).append("<td>").append((String)((ArrayList)arraylist.get(i)).get(j)).append("</td>").toString();
                                        }
                    }

                s1 = (new StringBuilder()).append(s1).append("<tr>").append(s2).append("</tr>\r\n").toString();
            }

            s1 = (new StringBuilder()).append(s1).append("</table>").toString();
            Result = s1;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public String Result;
}
