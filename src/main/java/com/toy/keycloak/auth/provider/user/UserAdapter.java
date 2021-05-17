package com.toy.keycloak.auth.provider.user;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.adapter.AbstractUserAdapterFederatedStorage;

/**
 * @author Zhang_Xiang
 * @since 2021/5/16 15:40:00
 */
public class UserAdapter extends AbstractUserAdapterFederatedStorage {

    private final User user;
    private String keycloakId;

    public UserAdapter(KeycloakSession session, RealmModel realm, ComponentModel componentModel, User user) {
        super(session, realm, componentModel);
        this.user = user;
        if (user != null) {
            keycloakId = StorageId.keycloakId(componentModel, user.getId().toString());
        }
        setEnabled(true);
    }

    @Override
    public String getId() {
        return keycloakId;
    }

    @Override
    public String getUsername() {
        return user.getLoginAccountId();
    }

    @Override
    public void setUsername(String username) {
        user.setLoginAccountId(username);
    }
    
}
