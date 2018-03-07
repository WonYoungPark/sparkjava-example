import com.google.gson.JsonObject;
import core.FreeMarkerTemplateEngine;
import core.JsonTransformer;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Version;
import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

/**
 * Created by One0 on 2018. 3. 7..
 */
public class Application {
    public static void main(String[] args) {
        get("/hello", (request, response) -> {
            if (shouldReturnHtml(request)) {
                response.status(200);
                response.type("application/json");
            } else {
                return "Hello World2";
            }

            return "Hello World";
        });

        get("/json", ((request, response) -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("key1", "박원영");
            jsonObject.addProperty("key2", "박원영2");
            return jsonObject;
        }), new JsonTransformer());

        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index2.ftl");
        }, new FreeMarkerTemplateEngine());

    }

    private static boolean shouldReturnHtml(Request request) {
        String accept = request.headers("Accept");
        return accept != null && accept.contains("text/html");
    }

}
