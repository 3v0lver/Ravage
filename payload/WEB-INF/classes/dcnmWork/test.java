package dcnmWork;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.jboss.util.Base64;

public class test
{

    public test(String s)
    {
        String s1 = new String(pwd);
	if (s!=null){
		if ((s.toLowerCase()).contains("password")){
		Result = "";
		return ;
		}
	} else {
        Result = "<td>[Empty or Decryption failure]</td>";
	}	
        try
        {
            Cipher.getInstance("PBEWithMD5AndDES");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        try
        {
            SecretKeyFactory secretkeyfactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            javax.crypto.SecretKey secretkey = secretkeyfactory.generateSecret(new PBEKeySpec(s1.toCharArray()));
            byte abyte0[] = new byte[0];
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
            cipher.init(2, secretkey, PBE_SPEC);
            abyte0 = cipher.doFinal(Base64.decode(s));
            Result = "<td>" + new String(abyte0) +"</td>" ;
        }
        catch(Exception exception1)
        {
            exception1.printStackTrace();
        }
    }
	//Below is the static Password for the PBE
    byte pwd[] = {
        87, 94, 61, 100, 124, 63, 63, 46, 84, 62, 
        38, 70, 88, 106, 50, 64, 93, 33, 73, 38, 
        42, 83, 122, 53
    };
	//Below is the static seed value
    static PBEParameterSpec PBE_SPEC = new PBEParameterSpec(new byte[] {
        26, 43, 60, 77, 77, 60, 43, 26
    }, 20);
    public String Result;

}
