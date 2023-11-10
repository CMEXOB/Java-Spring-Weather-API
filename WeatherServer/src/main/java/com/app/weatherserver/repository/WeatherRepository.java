package com.app.weatherserver.repository;

import com.app.weatherserver.entity.Sensor;
import com.app.weatherserver.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

/**
 * Repository class to manage {@link Weather} instances
 *
 * @author Skripko Egor
 */
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    /**
     * Retrieve 20 latest {@link Weather} which belong to given {@link Sensor}
     *
     * @param sensor - given {@link Sensor}
     * @return <code>List</code> of {@link Weather}
     */
    @Query("select w from Weather w where w.creator = ?1 order by w.time desc limit 20")
    List<Weather> findWeatherFromSensor(Sensor sensor);

    /**
     * Retrieve all {@link Weather} which time later that given {@link Time}
     *
     * @param time - given {@link Time}
     * @return <code>List</code> of {@link Weather}
     */
    @Query("select w from Weather w where w.time >= ?1")
    List<Weather> findWeatherLaterThatTime(Time time);

    /**
     * Retrieve latest {@link Weather} which belong to given {@link Sensor}
     *
     * @param sensor - given {@link Sensor}
     * @return <code>Optional</code> of {@link Weather}
     */
    Optional<Weather> findWeatherByCreatorOrderByTimeDesc(Sensor sensor);
}
