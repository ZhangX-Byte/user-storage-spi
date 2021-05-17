package com.toy.keycloak.auth.provider.user;

import org.keycloak.component.ComponentModel;
import org.keycloak.component.ComponentValidationException;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;
import org.keycloak.storage.UserStorageProviderFactory;

import java.sql.Connection;
import java.util.List;

import static com.toy.keycloak.auth.provider.user.JdbcConstants.*;

/**
 * @author Zhang_Xiang
 * @since 2021/5/13 10:50:52
 */
public class MysqlUserStorageProviderFactory implements UserStorageProviderFactory<MysqlUserStorageProvider> {

    protected final List<ProviderConfigProperty> configMetadata;

    public MysqlUserStorageProviderFactory() {
        configMetadata = ProviderConfigurationBuilder.create()
                .property()
                .name(CONFIG_KEY_JDBC_DRIVER)
                .label("JDBC Driver Class")
                .type(ProviderConfigProperty.STRING_TYPE)
                .defaultValue("org.h2.Driver")
                .helpText("Fully qualified class name of the JDBC driver")
                .add()
                .property()
                .name(CONFIG_KEY_JDBC_URL)
                .label("JDBC URL")
                .type(ProviderConfigProperty.STRING_TYPE)
                .defaultValue("jdbc:h2:mem:customdb")
                .helpText("JDBC URL used to connect to the user database")
                .add()
                .property()
                .name(CONFIG_KEY_DB_USERNAME)
                .label("Database User")
                .type(ProviderConfigProperty.STRING_TYPE)
                .helpText("Username used to connect to the database")
                .add()
                .property()
                .name(CONFIG_KEY_DB_PASSWORD)
                .label("Database Password")
                .type(ProviderConfigProperty.STRING_TYPE)
                .helpText("Password used to connect to the database")
                .secret(true)
                .add()
                .property()
                .name(CONFIG_KEY_VALIDATION_QUERY)
                .label("SQL Validation Query")
                .type(ProviderConfigProperty.STRING_TYPE)
                .helpText("SQL query used to validate a connection")
                .defaultValue("select 1")
                .add()
                .build();
    }

    @Override
    public MysqlUserStorageProvider create(KeycloakSession session, ComponentModel model) {
        return new MysqlUserStorageProvider(session, model);
    }

    @Override
    public String getId() {
        return "user-center-provider";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return configMetadata;
    }

    @Override
    public void validateConfiguration(KeycloakSession session, RealmModel realm, ComponentModel config) throws ComponentValidationException {
        try (Connection c = DbUtil.getConnection(config)) {
            System.out.println(config.get(CONFIG_KEY_VALIDATION_QUERY));
            c.createStatement().execute(config.get(CONFIG_KEY_VALIDATION_QUERY));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new ComponentValidationException("Unable to validate database connection", ex);
        }
    }
}
