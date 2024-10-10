import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get the search query from the request
        String query = request.getParameter("query");
        
        // Read from registry.json
        List<RegistryEntry> registryEntries = readRegistry();
        List<RegistryEntry> results = new ArrayList<>();

        if (query != null && !query.isEmpty()) {
            for (RegistryEntry entry : registryEntries) {
                if (entry.getName().toLowerCase().contains(query.toLowerCase())) {
                    results.add(entry);
                }
            }
        }

        // Set response type to JSON
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        out.print(gson.toJson(results));
        out.flush();
    }

    private List<RegistryEntry> readRegistry() {
        try {
            FileReader reader = new FileReader("registry.json"); // Update with the correct path
            return new Gson().fromJson(reader, new TypeToken<List<RegistryEntry>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static class RegistryEntry {
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }
}
