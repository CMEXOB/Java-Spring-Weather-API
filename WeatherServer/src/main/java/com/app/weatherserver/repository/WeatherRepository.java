package com.app.weatherserver.repository;

import com.app.weatherserver.entity.Sensor;
import com.app.weatherserver.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    @Query("select w from Weather w where w.sensor = ?1 order by w.time desc limit 20")
    List<Weather> findWeatherBySensor(Sensor sensor);

    /**
     * Retrieve all {@link Weather} which time later that given time
     *
     * @param time - given time
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
    Optional<Weather> findWeatherBySensorOrderByTimeDesc(Sensor sensor);

    /**
     * Retrieve {@link Sensor} from {@link Weather} which need to deactivate
     * when {@link Sensor} is active and time {@link Weather} later that given time
     *
     * @param time - given time
     * @return <code>Set</code> of {@link Sensor}
     */
    @Query("select w.sensor from Weather w where w.sensor not in (select w1.sensor from Weather w1 where w1.sensor.isActive = false or w1.time >= ?1)")
    Set<Sensor> findWeatherSensorWitchNeedDeactivated(Time time);
}
