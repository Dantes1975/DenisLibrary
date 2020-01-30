package by.it_academy.service;

import by.it_academy.bean.Authenticate;
import by.it_academy.repository.AuthenticateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthenticateService {

    @Autowired
    private AuthenticateRepository authenticateRepository;

    public Authenticate save(Authenticate authenticate) {
        return authenticateRepository.save(authenticate);
    }

    public Authenticate getById(long id){
        return authenticateRepository.findById(id).orElse(null);
    }

    public Authenticate getByLoginAndPassword(String login, String password) {
        return authenticateRepository.getByLoginAndPassword(login, password);
    }

    public void deleteById(long id) {
        authenticateRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Authenticate> findAll() {
        return (List<Authenticate>) authenticateRepository.findAll();
    }

    public void authenticateOff(long id) {
        authenticateRepository.authOff(id);
    }

    public void authenticateOn(long id) {
        authenticateRepository.authOn(id);
    }

    public void authenticateBlock(long id) {
        authenticateRepository.authBlock(id);
    }
}
