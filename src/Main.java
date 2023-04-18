import java.sql.*;
import java.util.ArrayList;

class Main{
    public static void main(String args[]){
        try{
            ArrayList<Player> players = new ArrayList<Player>();

            Class.forName("com.mysql.jdbc.Driver");

            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agario","root","");
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from highscores order by score desc");

            while(rs.next()) {
                players.add(new Player(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
            con.close();

            int longestNickLength = 0;

            for (Player player : players) {
                if(player.nickname.length() > longestNickLength) longestNickLength = player.nickname.length();
            }

            for (Player player : players) {
                int nicknameLength = player.nickname.length();
                for (int i = 0; i < longestNickLength - nicknameLength; i++) player.nickname += " ";
            }

            System.out.println("ID\t|\tNICK\t\t|\tSCORE\n-------------------------------");

            for (Player player : players) {
                System.out.println(player.id + "\t|\t" + player.nickname + "\t|\t" + player.score);
            }

        }catch(Exception e){ System.out.println(e);}
    }
}