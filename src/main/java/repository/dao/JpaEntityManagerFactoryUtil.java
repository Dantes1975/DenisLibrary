package repository.dao;

import org.h2.tools.Server;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class JpaEntityManagerFactoryUtil {
    public final static Server SERVER;

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;

    static {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("PERSISTENCE");
        try {
            SERVER = Server.createTcpServer().start();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    private JpaEntityManagerFactoryUtil() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }


}
