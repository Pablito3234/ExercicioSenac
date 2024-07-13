package connectbdtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static Connection con = null;

    public void ConectBd(String dataBase) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (con == null) {
                con = DriverManager.getConnection(dataBase, "root", "");
                System.out.println("Conectado");
            } else {
                ResultSet rs;
                try (PreparedStatement stat = con.prepareStatement("select * from cadescola")) {
                    rs = stat.executeQuery();
                    while (rs.next()) {
                        String nome = rs.getString("nome");
                        String media = rs.getString("notamedia");
                        System.out.println(nome + " : " + media);
                    }
                }
                rs.close();
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Classe não encontrada");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public void insertRowDatabase(String nome, int notamedia, String nameTable) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = getConnection();
            String query1 = "INSERT INTO " + nameTable + " (nome, notamedia) VALUES (?, ?);";
            try (PreparedStatement pstmt = conn.prepareStatement(query1)) {
                pstmt.setString(1, nome);
                pstmt.setInt(2, notamedia);
                pstmt.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception);
        }
    }
    
    public void removeRowDatabase(int rowIndex, String nameTable){
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = ("DELETE FROM " + nameTable + " WHERE id = ?;");
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, rowIndex);
            pstmt.executeUpdate();
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (pstmt != null) { pstmt.close(); }
                if (rs != null) { rs.close(); }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public Object[][] getDataBaseTable(String dataBaseTableName) {
        ArrayList data = new ArrayList();
        Connection conn = getConnection();
        try {
            String query = ("SELECT * FROM " + dataBaseTableName);
            ResultSet rs;
            try (Statement stmt = conn.createStatement()) {
                rs = stmt.executeQuery(query);
                int columnCount = rs.getMetaData().getColumnCount();
                while (rs.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = rs.getObject(i);
                    }
                    data.add(row);
                }
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return (Object[][]) data.toArray(new Object[0][0]);
    }

    public String[] getColumnNames(String dataBaseTableName) {
        String[] columnNames = null;
        Connection conn = getConnection();
        try {
            String query = ("SELECT * FROM " + dataBaseTableName);
            ResultSet rs;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                rs = pstmt.executeQuery();
                int columnCount = rs.getMetaData().getColumnCount();
                columnNames = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    columnNames[i - 1] = rs.getMetaData().getColumnName(i);
                }
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return columnNames;
    }

    public static Connection getConnection() {
        return con;
    }
}
