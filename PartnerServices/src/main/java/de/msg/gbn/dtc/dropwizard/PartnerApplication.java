package de.msg.gbn.dtc.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PartnerApplication extends Application<PartnerConfiguration> {

	public static void main(String[] args) throws Exception {
        new PartnerApplication().run(args);
    }

    @Override
    public String getName() {
        return "partner-services";
    }

    @Override
    public void initialize(Bootstrap<PartnerConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(PartnerConfiguration configuration,
                    Environment environment) {
    	final PartnerResource resource = new PartnerResource(
    	        configuration.getTemplate(),
    	        configuration.getDefaultName()
    	    );
    	
    	final TemplateHealthCheck healthCheck =
    	        new TemplateHealthCheck(configuration.getTemplate());
    	
    	environment.healthChecks().register("template", healthCheck);    	
    	environment.jersey().register(resource);    
    }	
}
