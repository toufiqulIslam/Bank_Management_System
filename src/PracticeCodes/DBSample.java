package PracticeCodes;

import java.sql.ResultSet;
/**
 *
 * @author Toufiq Rich
 */

/*class DBSample {	
    public static void main(String[] args) {
       // String entereduserPassInfo="erick";
       // String e="4567";
        /*try {
            
            DataAccess da = new DataAccess();
            //String query = "SELECT uid, name, password FROM user";            
            //String query = "SELECT Username FROM logininfo WHERE Username=' "+entereduserPassInfo+" ' ";            
            String query = "SELECT Username, Password FROM logininfo WHERE Username='erick' and Password='4567' ";            
            ResultSet rs = da.getData(query);
            
            while(rs.next()){
                //System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
				//or//
                 // if(entereduserPassInfo.equals(rs.getString("Username")))
	System.out.println(rs.getInt("uid") + "\t" + rs.getString("name") + "\t" + rs.getString("Username"));
           // }*/
            
      //  } catch (Exception ex) {
           // ex.printStackTrace();
         //   System.out.println(ex);
        //} 

   // }




/*class DBSample {      
    public static void main(String[] args) {
        try {
            int id= 8;
            String name = "Kent";
            String password = "01915";
            
            String query = "INSERT INTO user (uid, name, password) VALUES ("+id+", 'Anne', '01815')";            
            System.out.println(query);
			System.out.println(new DataAccess().updateDB(query));
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}*/
