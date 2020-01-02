package repository.daoImpl;

import bean.UsersRoles;
import repository.dao.AbstractDao;
import repository.dao.Users_rolesDao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UsersRolesDaoImpl extends AbstractDao<UsersRoles> implements Users_rolesDao {


    public UsersRoles getByUserID(long id) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UsersRoles> criteria = cb.createQuery(UsersRoles.class);
        Root<UsersRoles> root = criteria.from(UsersRoles.class);
        Predicate expression = cb.equal(root.get("user_id"), id);
        criteria.select(root).where(expression);
        UsersRoles users_roles = em.createQuery(criteria).getSingleResult();
        return users_roles;
    }
}
