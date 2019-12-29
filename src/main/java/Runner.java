import repository.daoImpl.AuthenticateDaoImpl;
import repository.daoImpl.UserDaoImpl;
import repository.daoImpl.UsersRolesDaoImpl;

public class Runner {
    public static void main(String[] args) {
        UserDaoImpl userDao= new UserDaoImpl();
        AuthenticateDaoImpl authenticateDao = new AuthenticateDaoImpl();
        UsersRolesDaoImpl users_rolesDao = new UsersRolesDaoImpl();
//        User user = new User();
//        user.setName("Denis");
//        user.setSurname("Rumyancev");
//        user.setAge(44);
//        user.setEmail("dantes75@mail.ru");
//        userDao.insert(user);
        System.out.println(userDao.getById(2).toString());
        System.out.println(authenticateDao.getById(2));
        System.out.println(authenticateDao.getByLogin("admin"));
        long i= users_rolesDao.getByUserID(2).getRole_id();
        System.out.println(i);
        Boolean bol = authenticateDao.isExistByLogin("dantes");
        System.out.println(bol);
    }
}
