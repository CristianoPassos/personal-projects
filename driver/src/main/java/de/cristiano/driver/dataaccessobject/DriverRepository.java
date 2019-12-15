package de.cristiano.driver.dataaccessobject;

import de.cristiano.driver.domainobject.DriverDO;
import de.cristiano.driver.domainvalue.OnlineStatus;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>, QueryByExampleExecutor<DriverDO>
{

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);
}
