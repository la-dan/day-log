package cn.vlinker.daylog.service;

import cn.vlinker.daylog.model.AuthCode;
import cn.vlinker.daylog.model.User;
import cn.vlinker.daylog.repository.AuthCodeRepository;
import cn.vlinker.daylog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthCodeRepository authCodeRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public Object add(User user, String auth) {
        AuthCode authCode = authCodeRepository.findTop1ByMacOrderByUuidDesc(user.getMac());
        User u = get(user.getMac());
        if (auth.equals(authCode.getCode()) && u == null) {
            return this.save(user);
        } else {
            return null;
        }
    }

    public User get(String mac) {
        return userRepository.getByMac(mac);
    }
}
