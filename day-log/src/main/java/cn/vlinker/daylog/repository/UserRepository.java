package cn.vlinker.daylog.repository;

import cn.vlinker.daylog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User getByMac(String mac);
}
