package service;

import bean.Authenticate;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AuthenticateDao;

@Data
@NoArgsConstructor
@Service
public class AuthenticateService {

    @Autowired
    private AuthenticateDao authenticateDao;

public Authenticate save(Authenticate authenticate){
    authenticateDao.save(authenticate);
    return authenticate;
}

}
