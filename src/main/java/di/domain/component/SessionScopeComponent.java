package di.domain.component;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * Session scope component<br>
 * <p>
 *  This class is a session scope bean for passing property values between 
 *  web screens.
 */
@Component @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter @Setter
public class SessionScopeComponent implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
}
