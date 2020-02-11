package by.it_academy.service;

import by.it_academy.bean.User;
import by.it_academy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }



    public User getById(long id){
        return userRepository.getById(id);
    }

    public void deleteById(long id){
        userRepository.deleteById(id);
    }
}
