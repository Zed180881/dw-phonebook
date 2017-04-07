
package com.softserveinc.phonebook;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.softserveinc.phonebook.api.User;
import com.softserveinc.phonebook.config.ApplicationContextProvider;
import com.softserveinc.phonebook.config.SpringConfiguration;
import com.softserveinc.phonebook.resources.ContactResource;
import com.softserveinc.phonebook.resources.UserResource;
import com.softserveinc.phonebook.security.PhoneBookAuthenticator;
import com.softserveinc.phonebook.security.PhoneBookAuthorizer;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PhoneBookApplication extends Application<PhoneBookConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneBookApplication.class);

    public static void main(final String[] args) throws Exception {
        new PhoneBookApplication().run(args);
    }

    @Override
    public String getName() {
        return "phonebook-spring";
    }

    @Override
    public void initialize(final Bootstrap<PhoneBookConfiguration> bootstrap) {
    }

    @Override
    public void run(final PhoneBookConfiguration configuration, final Environment environment) {

        LOGGER.info("Application start");

        // Spring integration
        ApplicationContext context = getApplicationContext(configuration, environment);
        ApplicationContextProvider.setApplicationContext(context);

        // Add the resource to the environment
        environment.jersey().register(context.getBean(ContactResource.class));
        environment.jersey().register(context.getBean(UserResource.class));

        // Add security
        environment.jersey()
                .register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(context.getBean(PhoneBookAuthenticator.class))
                        .setAuthorizer(context.getBean(PhoneBookAuthorizer.class)).setRealm("SUPER SECRET STUFF")
                        .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);

    }

    private ApplicationContext getApplicationContext(PhoneBookConfiguration configuration, Environment environment) {

        // Get DataSource from DropWizard
        DataSourceFactory dataSourceFactory = configuration.getDataSourceFactory();
        ManagedDataSource dataSource = dataSourceFactory.build(environment.metrics(), "dataSource");

        // Build parent Context with DropWizard Beans
        AnnotationConfigApplicationContext parent = new AnnotationConfigApplicationContext();
        parent.refresh();
        ConfigurableListableBeanFactory beanFactory = parent.getBeanFactory();
        beanFactory.registerSingleton("dataSource", dataSource);
        beanFactory.registerSingleton("configuration", configuration);
        parent.registerShutdownHook();
        parent.start();

        // Build child Context with Spring Beans
        AnnotationConfigApplicationContext annotationContext = new AnnotationConfigApplicationContext();
        annotationContext.setParent(parent);
        annotationContext.register(SpringConfiguration.class);
        annotationContext.refresh();
        annotationContext.registerShutdownHook();
        annotationContext.start();
        return annotationContext;
    }

}
