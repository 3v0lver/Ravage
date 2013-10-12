package dcnmWork;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.net.*;
import java.util.ArrayList;

public class dbquery{

class DriverShim implements Driver {
        private Driver driver;
        DriverShim(Driver d) {
                this.driver = d;
        }
        public boolean acceptsURL(String u) throws SQLException {
                return this.driver.acceptsURL(u);
        }
        public Connection connect(String u, Properties p) throws SQLException {
                return this.driver.connect(u, p);
        }
        public int getMajorVersion() {
                return this.driver.getMajorVersion();
        }
        public int getMinorVersion() {
                return this.driver.getMinorVersion();
        }
        public DriverPropertyInfo[] getPropertyInfo(String u, Properties p) throws SQLException {
                return this.driver.getPropertyInfo(u, p);
        }
        public boolean jdbcCompliant() {
                return this.driver.jdbcCompliant();
        }
}

	
public ArrayList<ArrayList> matrix = new ArrayList<ArrayList>();

public dbquery(String dbpath, String pgLogin, String pgPassword, String query){ 
URL[] u = new URL[0];

try {
	u[0] = new URL("jar:file:c:/jboss-4.2.2.GA/server/dcnm/lib/postgresql-8.1-404.jdbc3.jar!/");
	}	catch (Exception e) {
                        e.printStackTrace();
                }
		Statement st = null;
                ResultSet rs = null;
                Connection Conn = null;

                URLClassLoader ucl;

                String classname = "org.postgresql.Driver";
                ucl = new URLClassLoader(u);
                try {
                        Driver d = (Driver)Class.forName(classname, true, ucl).newInstance();
                        DriverManager.registerDriver(new DriverShim(d));

                Conn = DriverManager.getConnection(dbpath,pgLogin, pgPassword);
                st = Conn.createStatement();

                rs = st.executeQuery(query);
                ResultSetMetaData rsmd = rs.getMetaData();
                int NumOfCol=rsmd.getColumnCount();
	        ArrayList columnHeader = new ArrayList();

                for (int j=1; j<NumOfCol+1; j++){
                       columnHeader.add(rsmd.getColumnName(j));
                }
		matrix.add(columnHeader);


                int rowCount = 0;
	         while (rs.next()) {
                        String rowCat = "";
                        ArrayList col = new ArrayList();
                        for (int i=1; i<NumOfCol+1; i++){
                                col.add(rs.getString(i));
                                rowCount ++;
                        }
                        matrix.add(col);

                }


                } catch (Exception e) {
                        e.printStackTrace();
                }finally {
                            try {
                                if (rs != null) {
                                    rs.close();
                                }
                                if (st != null) {
                                    st.close();
                                }
                                if (Conn != null) {
                                    Conn.close();
                                }
                                } catch (Exception e) {
                                        e.printStackTrace();

				}

}


}

}
