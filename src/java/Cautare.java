
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/search"})
public class Cautare extends HttpServlet {

    private static Connection link;
    private static Statement statement;
    private static ResultSet result;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter output = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            link = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "");
        } catch (ClassNotFoundException cnfEx) {
            System.out.println("* Nu am putut incarca driverul! *");
            System.exit(1);
        } catch (SQLException sqlEx) {
            System.out.println("* Eroare SQL! *");
            System.exit(1);
        }
        output.println("<!DOCTYPE html>\n"
                + "<html>\n"
                + "	<head>\n"
                + "		<title>Cautare contact</title>\n"
                + "	</head>\n"
                + "	<body>\n"
                + "		<h3>Cautare contact</h3>\n"
                + "		<form method=\"POST\" action=\"\">\n"
                + "			<table>\n"
                + "				<tr><td>Nume</td><td><input type=\"text\" name=\"nume\" value=\"" + (request.getParameter("nume") == null ? "" : request.getParameter("nume")) + "\"></td></tr>\n"
                + "				<tr><td>Prenume</td><td><input type=\"text\" name=\"prenume\" value=\"" + (request.getParameter("prenume") == null ? "" : request.getParameter("prenume")) + "\"></td></tr>\n"
                + "				<tr><td>Telefon mobil</td><td><input type=\"text\" name=\"mobil\" value=\"" + (request.getParameter("mobil") == null ? "" : request.getParameter("mobil")) + "\"></td></tr>\n"
                + "				<tr><td>Telefon fix</td><td><input type=\"text\" name=\"fix\" value=\"" + (request.getParameter("fix") == null ? "" : request.getParameter("fix")) + "\"></td></tr>\n"
                + "				<tr><td>Email</td><td><input type=\"email\" name=\"email\" value=\"" + (request.getParameter("email") == null ? "" : request.getParameter("email")) + "\"></td></tr>\n"
                + "				<tr><td>Adresa</td><td><input type=\"text\" name=\"adresa\" value=\"" + (request.getParameter("adresa") == null ? "" : request.getParameter("adresa")) + "\"></td></tr>\n"
                + "				<tr><td>Oras</td><td><input type=\"text\" name=\"oras\" value=\"" + (request.getParameter("oras") == null ? "" : request.getParameter("oras")) + "\"></td></tr>\n"
                + "				<tr><td>Judet</td><td><input type=\"text\" name=\"judet\" value=\"" + (request.getParameter("judet") == null ? "" : request.getParameter("judet")) + "\"></td></tr>\n"
                + "				<tr><td>Cod postal</td><td><input type=\"number\" name=\"cod\" value=\"" + (request.getParameter("cod") == null ? "" : request.getParameter("cod")) + "\"></td></tr>\n"
                + "			</table>\n"
                + "			<br>\n"
                + "			<input type=\"submit\" value=\"Cauta\">\n"
                + "		</form>\n"
                + "		</br>\n"
                + "		<form method=\"GET\" action=\"/\">\n"
                + "			<input type=\"submit\" value=\"Inapoi\">\n"
                + "		</form>\n"
                + "		</br>");

        try {
            statement = link.createStatement();
            String sql = "select * from agenda where ";
            if (request.getParameter("nume") != null && !request.getParameter("nume").equals("")) {
                System.out.println(sql.length());
                if (sql.length() < 28) {
                    sql = sql + "nume='" + request.getParameter("nume") + "'";
                }
            }
            if (request.getParameter("prenume") != null && !request.getParameter("prenume").equals("")) {
                if (sql.length() < 28) {
                    sql = sql + "prenume='" + request.getParameter("prenume") + "'";
                } else {
                    sql = sql + " or prenume='" + request.getParameter("prenume") + "'";
                }
            }
            if (request.getParameter("mobil") != null && !request.getParameter("mobil").equals("")) {
                if (sql.length() < 28) {
                    sql = sql + "tmobil='" + request.getParameter("mobil") + "'";
                } else {
                    sql = sql + " or tmobil='" + request.getParameter("mobil") + "'";
                }
            }
            if (request.getParameter("fix") != null && !request.getParameter("fix").equals("")) {
                if (sql.length() < 28) {
                    sql = sql + "tfix='" + request.getParameter("fix") + "'";
                } else {
                    sql = sql + " or tfix='" + request.getParameter("fix") + "'";
                }
            }
            if (request.getParameter("email") != null && !request.getParameter("email").equals("")) {
                if (sql.length() < 28) {
                    sql = sql + "email='" + request.getParameter("email") + "'";
                } else {
                    sql = sql + " or email='" + request.getParameter("email") + "'";
                }
            }
            if (request.getParameter("adresa") != null && !request.getParameter("adresa").equals("")) {
                if (sql.length() < 28) {
                    sql = sql + "adresa='" + request.getParameter("adresa") + "'";
                } else {
                    sql = sql + " or adresa='" + request.getParameter("adresa") + "'";
                }
            }
            if (request.getParameter("oras") != null && !request.getParameter("oras").equals("")) {
                if (sql.length() < 28) {
                    sql = sql + "oras='" + request.getParameter("oras") + "'";
                } else {
                    sql = sql + " or oras='" + request.getParameter("oras") + "'";
                }
            }
            if (request.getParameter("judet") != null && !request.getParameter("judet").equals("")) {
                if (sql.length() < 28) {
                    sql = sql + "judet='" + request.getParameter("judet") + "'";
                } else {
                    sql = sql + " or judet='" + request.getParameter("judet") + "'";
                }
            }
            if (request.getParameter("cod") != null && !request.getParameter("cod").equals("")) {
                if (sql.length() < 28) {
                    sql = sql + "cpostal='" + request.getParameter("cod") + "'";
                } else {
                    sql = sql + " or cpostal='" + request.getParameter("cod") + "'";
                }
            }
            System.out.println(sql);
            if (sql.length() > 27) {
                result = statement.executeQuery(sql);
                StringBuilder resp = new StringBuilder();

                resp.append("		<table>\n"
                        + "			<tr>\n"
                        + "				<td>Nume</td>\n"
                        + "				<td>Prenume</td>\n"
                        + "				<td>Telefon mobil</td>\n"
                        + "				<td>Telefon fix</td>\n"
                        + "				<td>Email</td>\n"
                        + "				<td>Adresa</td>\n"
                        + "				<td>Oras</td>\n"
                        + "				<td>Judet</td>\n"
                        + "				<td>Cod postal</td>\n"
                        + "			</tr>");
                int index = 0;

                while (result.next()) {
                    resp.append("			<tr>\n"
                            + "				<td>" + result.getString(2) + "</td>\n"
                            + "				<td>" + result.getString(3) + "</td>\n"
                            + "				<td>" + result.getString(4) + "</td>\n"
                            + "				<td>" + (result.getString(5) == null ? "" : result.getString(5)) + "</td>\n"
                            + "				<td>" + result.getString(6) + "</td>\n"
                            + "				<td>" + (result.getString(7) == null ? "" : result.getString(7)) + "</td>\n"
                            + "				<td>" + (result.getString(8) == null ? "" : result.getString(8)) + "</td>\n"
                            + "				<td>" + (result.getString(9) == null ? "" : result.getString(9)) + "</td>\n"
                            + "				<td>" + (result.getString(10) == null ? "" : result.getString(10)) + "</td>\n"
                            + "			</tr>\n");
                    index++;
                }
                resp.append("               </table>");
                if (index != 0) {
                    output.println(resp);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cautare.class.getName()).log(Level.SEVERE, null, ex);
            output.println(ex);
        }
        output.println("	</body>\n"
                + "</html>");
    }

    @Override
    public String getServletInfo() {
        return "Cautare contact";
    }

}
