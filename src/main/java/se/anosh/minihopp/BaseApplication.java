/**
 * 
 * This is a baseclass whose sole purpose
 * is to replace the web.xml and sets the path
 * to the REST webservice
 */
package se.anosh.minihopp;

import java.util.Collections;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("")
public class BaseApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Collections.emptySet();
    }
}