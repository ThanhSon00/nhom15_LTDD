package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.History;
import com.iotstar.onlinetest.models.Test;
import com.iotstar.onlinetest.models.Topic;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.models.statistics.TestStatistic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface HistoryDAO extends JpaRepository<History, Long> {
    Optional<List<History>> findByTestAndUser(Test test, User user);
    Optional<List<History>> findByUser(User user);

    @Query("select count(h.score) as numberScore, t.testId as testId, h.score as score " +
            "from Test t inner join History h on t.testId=h.test.testId " +
            "group by t.testId, h.score")
    List<Map<String, Object>> getScoreStatistic(List<Test> tests, Pageable pageable);

    @Query("select u.firstName as firstName, u.lastName as lastName, max(h.score) as score " +
            "from User u inner join History h on u.userId = h.user.userId " +
            "where h.test.testId = ?1 "+
            "group by u.userId")
    List<Map<String, Object>> getTestStatistic(Long testId, Pageable pageable);

}
