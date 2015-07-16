
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/"})
public class Main extends HttpServlet {

    private static Connection link;
    private static Statement statement;
    private static ResultSet result;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter output = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            link = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "");
        } catch (ClassNotFoundException cnfEx) {
            output.println("<script>alert(\"* Nu am putut incarca driverul! *\"</script>");
            System.exit(1);
        } catch (SQLException sqlEx) {
            output.println("<script>alert(\"* Eroare SQL! *\"</script>");
            System.exit(1);
        }
        try {
            statement = link.createStatement();
            result = statement.executeQuery("select * from agenda");
            if (result != null) {
                output.println("<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "	<head>\n"
                        + "		<title>Contacte</title>\n"
                        + "	</head>\n"
                        + "	<body>\n"
                        + "		<table>\n"
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
                        + "				<td>Edit</td>\n"
                        + "				<td>Delete</td>\n"
                        + "			</tr>");
            }
            while (result.next()) {
                output.println("			<tr>\n"
                        + "				<td>" + result.getString(2) + "</td>\n"
                        + "				<td>" + result.getString(3) + "</td>\n"
                        + "				<td>" + result.getString(4) + "</td>\n"
                        + "				<td>" + (result.getString(5) == null ? "" : result.getString(5)) + "</td>\n"
                        + "				<td>" + result.getString(6) + "</td>\n"
                        + "				<td>" + (result.getString(7) == null ? "" : result.getString(7)) + "</td>\n"
                        + "				<td>" + (result.getString(8) == null ? "" : result.getString(8)) + "</td>\n"
                        + "				<td>" + (result.getString(9) == null ? "" : result.getString(9)) + "</td>\n"
                        + "				<td>" + (result.getString(10) == null ? "" : result.getString(10)) + "</td>\n"
                        + "				<td>\n"
                        + "					<form method=\"POST\" action=\"edit\">\n"
                        + "						<input type=\"hidden\" name=\"id\" value=\"" + result.getString(1) + "\">\n"
                        + "						<input type=\"hidden\" name=\"nume\" value=\"" + result.getString(2) + "\">\n"
                        + "						<input type=\"hidden\" name=\"prenume\" value=\"" + result.getString(3) + "\">\n"
                        + "						<input type=\"hidden\" name=\"mobil\" value=\"" + result.getString(4) + "\">\n"
                        + "						<input type=\"hidden\" name=\"fix\" value=\"" + (result.getString(5) == null ? "" : result.getString(5)) + "\">\n"
                        + "						<input type=\"hidden\" name=\"email\" value=\"" + result.getString(6) + "\">\n"
                        + "						<input type=\"hidden\" name=\"adresa\" value=\"" + (result.getString(7) == null ? "" : result.getString(7)) + "\">\n"
                        + "						<input type=\"hidden\" name=\"oras\" value=\"" + (result.getString(8) == null ? "" : result.getString(8)) + "\">\n"
                        + "						<input type=\"hidden\" name=\"judet\" value=\"" + (result.getString(9) == null ? "" : result.getString(9)) + "\">\n"
                        + "						<input type=\"hidden\" name=\"cod\" value=\"" + (result.getString(10) == null ? "" : result.getString(10)) + "\">\n"
                        + "						<input type=\"submit\" value=\"edit\">\n"
                        + "					</form>\n"
                        + "				</td>\n"
                        + "				<td>\n"
                        + "					<form method=\"POST\" action=\"delete\">\n"
                        + "						<input type=\"hidden\" name=\"id\" value=\"" + result.getString(1) + "\">\n"
                        + "						<input type=\"hidden\" name=\"nume\" value=\"" + result.getString(2) + "\">\n"
                        + "						<input type=\"hidden\" name=\"prenume\" value=\"" + result.getString(3) + "\">\n"
                        + "						<input type=\"hidden\" name=\"mobil\" value=\"" + result.getString(4) + "\">\n"
                        + "						<input type=\"hidden\" name=\"fix\" value=\"" + (result.getString(5) == null ? "" : result.getString(5)) + "\">\n"
                        + "						<input type=\"hidden\" name=\"email\" value=\"" + result.getString(6) + "\">\n"
                        + "						<input type=\"hidden\" name=\"adresa\" value=\"" + (result.getString(7) == null ? "" : result.getString(7)) + "\">\n"
                        + "						<input type=\"hidden\" name=\"oras\" value=\"" + (result.getString(8) == null ? "" : result.getString(8)) + "\">\n"
                        + "						<input type=\"hidden\" name=\"judet\" value=\"" + (result.getString(9) == null ? "" : result.getString(9)) + "\">\n"
                        + "						<input type=\"hidden\" name=\"cod\" value=\"" + (result.getString(10) == null ? "" : result.getString(10)) + "\">\n"
                        + "						<input type=\"submit\" value=\"delete\">\n"
                        + "					</form>\n"
                        + "				</td>\n"
                        + "			</tr>"
                );
            }
            output.println("		</table>\n"
                    + "		<form method=\"POST\" action=\"add\">\n"
                    + "		<br>\n"
                    + "			<input type=\"submit\" value=\"Adauga\">\n"
                    + "		</form>\n"
                    + "		<br>\n"
                    + "		<form method=\"POST\" action=\"search\">\n"
                    + "			<input type=\"submit\" value=\"Cauta\">\n"
                    + "		</form>\n"
                    + "	</body>\n"
                    + "</html>");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter output = response.getWriter();
        if (request.getParameter("tip").equals("delete")) {
            try {
                statement = link.createStatement();
                String sql = "delete from agenda where id=" + request.getParameter("id");
                if (statement.executeUpdate(sql) == 0) {
                    output.println("<script>alert(\"Failed!\")</script>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Stergere.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("tip").equals("edit")) {
            try {
                statement = link.createStatement();
                String sql = "update agenda set Nume='" + request.getParameter("nume")
                        + "', Prenume='" + request.getParameter("prenume")
                        + "', Tmobil='" + request.getParameter("mobil")
                        + "', Tfix='" + request.getParameter("fix")
                        + "', Email='" + request.getParameter("email")
                        + "', Adresa='" + request.getParameter("adresa")
                        + "', Oras='" + request.getParameter("oras")
                        + "', Judet='" + request.getParameter("judet")
                        + "', Cpostal=" + request.getParameter("cod")
                        + "\nwhere id=" + request.getParameter("id");
                if (statement.executeUpdate(sql) == 0) {
                    output.println("<script>alert(\"Failed!\")</script>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Actualizare.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("tip").equals("create")) {
            if (request.getParameter("prenume").isEmpty() || request.getParameter("prenume").isEmpty() || request.getParameter("mobil").isEmpty() || request.getParameter("email").isEmpty()) {
                output.println("<script>alert(\"Failed!\")</script>");
            } else {
                try {
                    statement = link.createStatement();
                    String sql = "insert into agenda (Nume, Prenume, Tmobil, Tfix, Email, Adresa, Oras, Judet, Cpostal) values ('" + request.getParameter("nume")
                            + "', '" + request.getParameter("prenume")
                            + "', '" + request.getParameter("mobil")
                            + "', '" + request.getParameter("fix")
                            + "', '" + request.getParameter("email")
                            + "', '" + request.getParameter("adresa")
                            + "', '" + request.getParameter("oras")
                            + "', '" + request.getParameter("judet")
                            + "', " + (request.getParameter("cod").isEmpty() ? null : request.getParameter("cod"))
                            + ")";
                    System.out.println(sql);
                    if (statement.executeUpdate(sql) == 0) {
                        output.println("<script>alert(\"Failed!\")</script>");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Actualizare.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Show contacts";
    }

}
