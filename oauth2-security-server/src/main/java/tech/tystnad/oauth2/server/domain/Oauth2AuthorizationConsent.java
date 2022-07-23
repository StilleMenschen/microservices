package tech.tystnad.oauth2.server.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "`oauth2_authorization_consent`")
@IdClass(Oauth2AuthorizationConsent.AuthorizationConsentId.class)
@Data
public class Oauth2AuthorizationConsent {
    @Id
    private String registeredClientId;
    @Id
    private String principalName;
    @Lob
    @Column(columnDefinition="TEXT")
    private String authorities;

    public static class AuthorizationConsentId implements Serializable {
        private String registeredClientId;
        private String principalName;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AuthorizationConsentId that = (AuthorizationConsentId) o;
            return registeredClientId.equals(that.registeredClientId) && principalName.equals(that.principalName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(registeredClientId, principalName);
        }
    }
}
