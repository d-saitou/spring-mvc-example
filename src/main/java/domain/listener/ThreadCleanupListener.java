package domain.listener;

import java.sql.DriverManager;
import java.util.Collections;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import lombok.extern.slf4j.Slf4j;

/**
 * Thread cleanup listener<br>
 * <p>
 *  This class is loaded as a listener for the servlet and performs
 *  thread control when shutting down the application.
 */
@WebListener @Slf4j
public class ThreadCleanupListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("Run thread cleanup.");

		// bug fix: Connector/J thread remains. (ref: https://bugs.mysql.com/bug.php?id=65909)
		AbandonedConnectionCleanupThread.checkedShutdown();

		Collections.list(DriverManager.getDrivers()).forEach(driver -> {
			try {
				DriverManager.deregisterDriver(driver);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}
