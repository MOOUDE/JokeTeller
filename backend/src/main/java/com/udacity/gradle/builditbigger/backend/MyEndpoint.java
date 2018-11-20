package com.udacity.gradle.builditbigger.backend;



import com.example.jokerlib.Joke;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JDOCursorHelper;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@Api(name = "jokeEndpoint",
        namespace = @ApiNamespace(ownerDomain = "mindstorm.com",
                ownerName = "mindstorm.com", packagePath = "famousJokes.entity"))
public class MyEndpoint {

    /**
     * This method lists all the entities inserted in datastore.
     * It uses HTTP GET method and paging support.
     *
     * @return A CollectionResponse class containing the list of all entities
     * persisted and a cursor to the next page.
     */
    @SuppressWarnings({ "unchecked", "unused" })
    @ApiMethod(name = "listJoke")
    public CollectionResponse<Joke> listJoke(
            @Nullable @Named("cursor") String cursorString,
            @Nullable @Named("limit") Integer limit) {

        PersistenceManager mgr = null;
        Cursor cursor = null;
        List<Joke> execute = null;

        try {
            mgr = getPersistenceManager();
            Query query = mgr.newQuery(Joke.class);
            if (cursorString != null && cursorString != "") {
                cursor = Cursor.fromWebSafeString(cursorString);
                HashMap<String, Object> extensionMap = new HashMap<String, Object>();
                extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
                query.setExtensions(extensionMap);
            }

            if (limit != null) {
                query.setRange(0, limit);
            }

            execute = (List<Joke>) query.execute();
            cursor = JDOCursorHelper.getCursor(execute);
            if (cursor != null)
                cursorString = cursor.toWebSafeString();

            // Tight loop for fetching all entities from datastore and accomodate
            // for lazy fetch.
            for (Joke obj : execute)
                ;
        } finally {
            mgr.close();
        }

        return CollectionResponse.<Joke> builder().setItems(execute)
                .setNextPageToken(cursorString).build();
    }

    /**
     * This method gets the entity having primary key id. It uses HTTP GET method.
     *
     * @param id the primary key of the java bean.
     * @return The entity with primary key id.
     */
    @ApiMethod(name = "getJoke")
    public Joke getJoke(@Named("id") Long id) {
        PersistenceManager mgr = getPersistenceManager();
        Joke Joke = null;
        try {
            Joke = mgr.getObjectById(Joke.class, id);
        } finally {
            mgr.close();
        }
        return Joke;
    }

    /**
     * This inserts a new entity into App Engine datastore. If the entity already
     * exists in the datastore, an exception is thrown.
     * It uses HTTP POST method.
     *
     * @param Joke the entity to be inserted.
     * @return The inserted entity.
     */
    @ApiMethod(name = "insertJoke")
    public Joke insertJoke(Joke Joke) {
        PersistenceManager mgr = getPersistenceManager();
        try {
            if (Joke.getId() != null) {
                if (containsJoke(Joke)) {
                    throw new EntityExistsException("Object already exists");
                }
            }
            mgr.makePersistent(Joke);
        } finally {
            mgr.close();
        }
        return Joke;
    }

    /**
     * This method is used for updating an existing entity. If the entity does not
     * exist in the datastore, an exception is thrown.
     * It uses HTTP PUT method.
     *
     * @param Joke the entity to be updated.
     * @return The updated entity.
     */
    @ApiMethod(name = "updateJoke")
    public Joke updateJoke(Joke Joke) {
        PersistenceManager mgr = getPersistenceManager();
        try {
            if (!containsJoke(Joke)) {
                throw new EntityNotFoundException("Object does not exist");
            }
            mgr.makePersistent(Joke);
        } finally {
            mgr.close();
        }
        return Joke;
    }

    /**
     * This method removes the entity with primary key id.
     * It uses HTTP DELETE method.
     *
     * @param id the primary key of the entity to be deleted.
     */
    @ApiMethod(name = "removeJoke")
    public void removeJoke(@Named("id") Long id) {
        PersistenceManager mgr = getPersistenceManager();
        try {
            Joke Joke = mgr.getObjectById(Joke.class, id);
            mgr.deletePersistent(Joke);
        } finally {
            mgr.close();
        }
    }

    private boolean containsJoke(Joke Joke) {
        PersistenceManager mgr = getPersistenceManager();
        boolean contains = true;
        try {
            mgr.getObjectById(Joke.class, Joke.getId());
        } catch (javax.jdo.JDOObjectNotFoundException ex) {
            contains = false;
        } finally {
            mgr.close();
        }
        return contains;
    }

    private static PersistenceManager getPersistenceManager() {
        return PMF.get().getPersistenceManager();
    }

}