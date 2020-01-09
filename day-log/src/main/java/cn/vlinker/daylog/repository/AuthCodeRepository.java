package cn.vlinker.daylog.repository;

import cn.vlinker.daylog.model.AuthCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthCodeRepository extends JpaRepository<AuthCode, String> {

    AuthCode findTop1ByMacOrderByUuidDesc(String mac);
}
