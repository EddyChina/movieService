package com.wenqi.movies.http;

import com.wenqi.movies.entity.Theater;
import com.wenqi.movies.http.entity.HttpTheater;
import com.wenqi.movies.service.TheaterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/theaters", produces = MediaType.APPLICATION_JSON_VALUE)
public class TheaterResource {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TheaterService theaterService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpTheater> createTheater(@RequestBody HttpTheater newTheater) {
        Theater theaterToCreate = convert(newTheater);
        logger.info("Create a theater: " + theaterToCreate);
        Theater addedTheater = theaterService.addTheater(theaterToCreate);
        return new ResponseEntity<>(new HttpTheater(addedTheater), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{theaterId}")
    public ResponseEntity<HttpTheater> getTheaterById(@PathVariable("theaterId") long theaterId) {
        logger.info("Get theater by id: " + theaterId);
        Theater theater = theaterService.getTheater(theaterId);
        return new ResponseEntity<>(new HttpTheater(theater), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/name")
    public ResponseEntity<List<HttpTheater>> getTheatersByName(@RequestParam(value = "name", required = false) String name) {
        logger.info("Get movies by name = " + name);
        Iterable<Theater> found = theaterService.getTheatersByName(name);
        List<HttpTheater> returnList = new ArrayList<>();
        for (Theater theater : found) {
            returnList.add(new HttpTheater(theater));
        }
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }


    /**
     * Not pushing this into business layer. Could be a util as well
     *
     * @param httpTheater
     * @return
     */
    private Theater convert(HttpTheater httpTheater) {
        Theater theater = new Theater();
        theater.setName(httpTheater.name);
        theater.setLocation(httpTheater.location);
        return theater;
    }
}
