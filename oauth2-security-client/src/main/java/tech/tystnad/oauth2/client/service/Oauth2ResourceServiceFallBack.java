package tech.tystnad.oauth2.client.service;

//@Component
public class Oauth2ResourceServiceFallBack implements Oauth2ResourceService {

    @Override
    public String[] books() {
        return new String[]{};
    }

}
