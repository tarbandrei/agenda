
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/edit"})
public class Actualizare extends HttpServlet {

    private static Connection link;

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
                + "		<title>Actualizare contact</title>\n"
                + "	</head>\n"
                + "	<body>\n"
                + "		<h3>Actualizare contact</h3>\n"
                + "		<form method=\"POST\" action=\"/\">\n"
                + "			<input type=\"hidden\" name=\"tip\" value=\"edit\">\n"
                + "			<input type=\"hidden\" name=\"id\" value=\"" + request.getParameter("id") + "\">\n"
                + "			<table>\n"
                + "				<tr><td>Nume</td><td><input type=\"text\" name=\"nume\" placeholder=\"obligatoriu\" value=\"" + request.getParameter("nume") + "\"></td></tr>\n"
                + "				<tr><td>Prenume</td><td><input type=\"text\" name=\"prenume\" placeholder=\"obligatoriu\" value=\"" + request.getParameter("prenume") + "\"></td></tr>\n"
                + "				<tr><td>Telefon mobil</td><td><input type=\"text\" name=\"mobil\" placeholder=\"obligatoriu\" value=\"" + request.getParameter("mobil") + "\"></td></tr>\n"
                + "				<tr><td>Telefon fix</td><td><input type=\"text\" name=\"fix\" value=\"" + request.getParameter("fix") + "\"></td></tr>\n"
                + "				<tr><td>Email</td><td><input type=\"email\" name=\"email\" placeholder=\"obligatoriu\" value=\"" + request.getParameter("email") + "\"></td></tr>\n"
                + "				<tr><td>Adresa</td><td><input type=\"text\" name=\"adresa\" value=\"" + request.getParameter("adresa") + "\"></td></tr>\n"
                + "				<tr><td>Oras</td><td><input type=\"text\" name=\"oras\" value=\"" + request.getParameter("oras") + "\"></td></tr>\n"
                + "				<tr><td>Judet</td><td><input type=\"text\" name=\"judet\" value=\"" + request.getParameter("judet") + "\"></td></tr>\n"
                + "				<tr><td>Cod postal</td><td><input type=\"number\" name=\"cod\" value=\"" + request.getParameter("cod") + "\"></td></tr>\n"
                + "			</table>\n"
                + "			<br>\n"
                + "			<input type=\"submit\" value=\"Actualizare\">\n"
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
        return "Actualizare contact";
    }

}
