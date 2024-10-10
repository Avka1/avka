import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get the search query from the request
        String query = request.getParameter("query");

        // Mocking search results (Replace this with actual search logic)
        JSONArray results = new JSONArray();

        if (query != null && !query.isEmpty()) {
            // Mock data for testing purposes
            if (query.equalsIgnoreCase("example")) {
                results.put(new JSONObject().put("name", "Example Website 1").put("url", "http://example1.com"));
                results.put(new JSONObject().put("name", "Example Website 2").put("url", "http://example2.com"));
            } else {
                results.put(new JSONObject().put("name", "No results found").put("url", ""));
            }
        }

        // Set response type to JSON
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(results.toString());
        out.flush();
    }
}
