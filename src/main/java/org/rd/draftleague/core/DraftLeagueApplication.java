package org.rd.draftleague.core;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.rd.draftleague.core.resources.HelloWorldResource;
import org.rd.draftleague.core.utils.BasicHealthCheck;

public class DraftLeagueApplication extends Application<DraftLeagueApiConfiguration> {

    public static void main(String[] args) throws Exception {
        new DraftLeagueApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<DraftLeagueApiConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DraftLeagueApiConfiguration configuration,
                    Environment environment) {
        final HelloWorldResource helloWorldResource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final BasicHealthCheck healthCheck = new BasicHealthCheck(configuration.getTemplate());

        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(helloWorldResource);
    }
}
