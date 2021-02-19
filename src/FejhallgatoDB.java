import java.sql.*;
import java.util.ArrayList;

public class FejhallgatoDB {
    Connection conn;
    public  FejhallgatoDB() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/fejhallgatokdb";
        String user = "root";
        String pass = "";
        conn = DriverManager.getConnection(url,user, pass);

    }
    public ArrayList<Fejhallgato> getFejhallgatok() throws SQLException{
        ArrayList<Fejhallgato> fejhallgatok = new ArrayList<>();
        Statement st  = conn.createStatement();
        ResultSet result = st.executeQuery("Select * from fejhallgatotbl");
        while (result.next()){
            int id = result.getInt("id");
            String nev = result.getString("nev");
            String fajta = result.getString("fajta");
            int ar = result.getInt("ar");
            String leiras = result.getString("leiras");
            Fejhallgato f = new Fejhallgato(id,nev,fajta,ar,leiras);
            fejhallgatok.add(f);
        }
        return  fejhallgatok;
    }

    public boolean deleteFejhallgato(int id) throws SQLException {
        PreparedStatement st = conn.prepareStatement("DELETE FROM fejhallgatotbl WHERE id = ?");
        st.setInt(1,id);
        return st.execute();


    }


    public int insertFejhallgato(String nev, String fajta, int ar, String leiras)throws SQLException {
        PreparedStatement st = conn.prepareStatement("INSERT INTO fejhallgatotbl(nev,fajta,ar,leiras) VALUES(?,?,?,?)");
        st.setString(1,nev);
        st.setString(2,fajta);
        st.setInt(3,ar);
        st.setString(4,leiras);

        return st.executeUpdate();
    }

    public int updateFejhallgato(int id, String nev, String fajta, int ar, String leiras) throws SQLException {
        PreparedStatement st = conn.prepareStatement("UPDATE fejhallgatotbl SET nev = ?, fajta = ?, ar = ?, leiras = ? WHERE id = ?");
        st.setString(1,nev);
        st.setString(2,fajta);
        st.setInt(3,ar);
        st.setString(4,leiras);
        return st.executeUpdate();
    }
}

