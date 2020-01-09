package cn.vlinker.daylog.repository;

import cn.vlinker.daylog.model.Log;
import cn.vlinker.daylog.model.LogPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface LogRepository extends JpaRepository<Log, LogPK> {

    @Query("select entity from Log entity where entity.day>= :begin and entity.day <= :end order by entity.name")
    List<Log> findByDay(LocalDate begin, LocalDate end);

    @Query(nativeQuery = true, value = "select a.email,t.total from \"user\" a LEFT JOIN (select count(l.name) total,u.email from log l ,\"user\" u  where l.name=u.name and l.day>= :begin and l.day <= :end GROUP BY u.email) t on a.email = t.email")
    List<Map<String, Object>> groupByUserCountName(LocalDate begin, LocalDate end);

    @Query("select l.day as day,l.work as work from Log l where l.name = :name order by l.day asc")
    List<Map<String,Object>> findByName(String name);
}
