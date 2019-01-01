package com.test.maps.controller;

import com.test.maps.service.MapsService;
import com.test.maps.util.MapsConstants;
import com.test.maps.util.MapsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
public class MapsController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MapsService mapsService;

    @GetMapping("/place/search/{placeName}")
    public ResponseEntity<?> findPlace(@PathVariable String placeName) throws URISyntaxException {
        logger.info("Find place name :" + placeName);
        return new ResponseEntity<>(mapsService.findPlace(placeName), HttpStatus.OK);
        //return new ResponseEntity<>(MapsUtil.readJson(MapsConstants.LONDON_JSON), HttpStatus.OK);
    }

    @GetMapping("/place/{placeId}")
    public ResponseEntity<?> getPlaceDetails(@PathVariable String placeId) throws URISyntaxException {
        logger.info("Get place details :" + placeId);
        //return new ResponseEntity<>(mapsService.getPlaceDetails(placeId), HttpStatus.OK);
        return new ResponseEntity<>(MapsUtil.readJson(MapsConstants.LONDON_DETAILS_JSON), HttpStatus.OK);
    }
}
