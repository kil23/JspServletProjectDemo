package org.care.context;

import org.care.dao.DAO;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

class DAOFactory {

    private ConcurrentMap<Class<? extends DAO>, DAO> cachedDAO = new ConcurrentHashMap<>();

    <T extends DAO> T getDAO(Class<T> clazz) {
        if (cachedDAO.get(clazz) == null) {
            try {
                T obj = clazz.newInstance();
                cachedDAO.put(clazz, obj);
            } catch (Exception e) {
                Logger logger = Logger.getLogger(DAOFactory.class.getName());
                logger.log(Level.SEVERE, "Cannot create a new instance of " + clazz
                        + ": " + e);
                return null;
            }
        }
        return (T) cachedDAO.get(clazz);
    }
}
