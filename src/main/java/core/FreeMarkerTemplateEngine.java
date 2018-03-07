package core;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import spark.ModelAndView;
import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by One0 on 2018. 3. 7..
 */
public class FreeMarkerTemplateEngine extends TemplateEngine {
    private Configuration configuration;

    public FreeMarkerTemplateEngine() {
        this.configuration = createFreemarkerConfiguration();
    }

    /**
     * Method called to render the output that is sent to client.
     *
     * @param modelAndView object where object (mostly a POJO) and the name of the view to render are set.
     * @return message that it is sent to client.
     */
    @Override
    public String render(ModelAndView modelAndView) {
        try {
            StringWriter stringWriter = new StringWriter();
            Template template = configuration.getTemplate(modelAndView.getViewName());
            template.process(modelAndView.getModel(), stringWriter);

            return stringWriter.toString();
        } catch (IOException | TemplateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Configuration createFreemarkerConfiguration() {
        Configuration configuration = new Configuration(new Version(2, 3, 23));
        configuration.setClassForTemplateLoading(FreeMarkerEngine.class, "/resources/templates");
        return configuration;
    }
}
