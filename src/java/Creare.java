
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/add"})
public class Creare extends HttpServlet {

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
                + "		<title>Creare contact</title>\n"
                + "	</head>\n"
                + "	<body>\n"
                + "		<h3>Creare contact</h3>\n"
                + "		<form method=\"POST\" action=\"/\">\n"
                + "			<input type=\"hidden\" name=\"tip\" value=\"create\">\n"
                + "			<input type=\"hidden\" name=\"id\" value=\"" + request.getParameter("id") + "\">\n"
                + "			<table>\n"
                + "				<tr><td>Nume</td><td><input type=\"text\" name=\"nume\" placeholder=\"obligatoriu\"></td></tr>\n"
                + "				<tr><td>Prenume</td><td><input type=\"text\" name=\"prenume\" placeholder=\"obligatoriu\"></td></tr>\n"
                + "				<tr><td>Telefon mobil</td><td><input type=\"text\" name=\"mobil\" placeholder=\"obligatoriu\"></td></tr>\n"
                + "				<tr><td>Telefon fix</td><td><input type=\"text\" name=\"fix\"></td></tr>\n"
                + "				<tr><td>Email</td><td><input type=\"email\" name=\"email\" placeholder=\"obligatoriu\"></td></tr>\n"
                + "				<tr><td>Adresa</td><td><input type=\"text\" name=\"adresa\"></td></tr>\n"
                + "				<tr><td>Oras</td><td><input type=\"text\" name=\"oras\"></td></tr>\n"
                + "				<tr><td>Judet</td><td><input type=\"text\" name=\"judet\"></td></tr>\n"
                + "				<tr><td>Cod postal</td><td><input type=\"number\" name=\"cod\"></td></tr>\n"
                + "			</table>\n"
                + "			<br>\n"
                + "			<input type=\"submit\" value=\"Creare\">\n"
                + "		</form>\n"
                + "		</br>\n"
                + "		<form method=\"GET\" action=\"/\">\n"
                + "			<input type=\"submit\" value=\"Inapoi\">\n"
                + "		</form>\n"
                + "	</body>\n"
                + "</html>");
    }

    @Override
    public String getServletInfo() {
        return "Creare contact";
    }

}
