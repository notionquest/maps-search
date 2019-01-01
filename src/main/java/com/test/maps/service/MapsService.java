package com.test.maps.service;

import com.test.maps.util.MapsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;

@Service
public class MapsService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Environment env;

    @Autowired
    private VaultTemplate vaultTemplate;

    @Autowired
    private RestTemplate restTemplate;

    public String findPlace(String placeName) throws URISyntaxException {

        ResponseEntity<String> mapSearchResponse = null;
        String key = getMapKey();
        if (key != null && !key.isEmpty() && placeName != null && !placeName.isEmpty()) {
            UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
            builder.uri(new URI(env.getProperty(MapsConstants.GOOGLE_FIND_PLACE_API_URL)));
            builder.queryParam("input", placeName);
            builder.queryParam("inputtype", "textquery");
            builder.queryParam("fields", "formatted_address,geometry,icon,id,name,permanently_closed,photos,place_id,plus_code,types");
            builder.queryParam("key", key);
            mapSearchResponse = restTemplate.getForEntity(builder.toUriString(),
                    String.class);
            logger.info("Place result ====>\n" + mapSearchResponse);
        }

        return mapSearchResponse.getBody();
    }

    public String getPlaceDetails(String placeId) throws URISyntaxException {

        ResponseEntity<String> placeDetailsResponse = null;
        String key = getMapKey();
        if (key != null && !key.isEmpty() && placeId != null && !placeId.isEmpty()) {

            UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
            builder.uri(new URI(env.getProperty(MapsConstants.GOOGLE_PLACE_DETAILS_API_URL)));
            builder.queryParam("placeid", placeId);
            builder.queryParam("fields", "address_component,adr_address,alt_id,formatted_address,geometry,icon,id,name,permanently_closed,photo,place_id,plus_code,scope,type,url,utc_offset,vicinity,formatted_phone_number,international_phone_number,opening_hours,website,price_level,rating,review");
            builder.queryParam("key", key);
            placeDetailsResponse = restTemplate.getForEntity(builder.toUriString(),
                    String.class);
            logger.info("Place details result ====>\n" + placeDetailsResponse);
        }

        return placeDetailsResponse.getBody();
    }

    private String getMapKey() {
        VaultResponse vaultResponse = vaultTemplate.read(MapsConstants.MAP_API_SECRET);
        LinkedHashMap<String, String> data = (LinkedHashMap) vaultResponse.getData().values().stream().findFirst().get();
        if (data != null && data.containsKey(MapsConstants.MAP_API_KEY)) {
            return data.get(MapsConstants.MAP_API_KEY);
        }
        return null;
    }
}
