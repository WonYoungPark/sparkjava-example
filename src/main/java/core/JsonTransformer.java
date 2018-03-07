package core;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by One0 on 2018. 3. 7..
 */
public class JsonTransformer implements ResponseTransformer {
    private Gson gson = new Gson();

    /**
     * Method called for rendering the output.
     *
     * @param model object used to render output.
     * @return message that it is sent to client.
     * @throws Exception when render fails
     */
    @Override
    public String render(Object model) throws Exception {
        return gson.toJson(model);
    }
}
