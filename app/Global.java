import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import java.util.*;

public class Global extends GlobalSettings {
	@Override
	public void onStart(Application app) {
		// Check if the DB is empty
		if(User.find.findRowCount() == 0) {
			Ebean.save((List) Yaml.load("test-data.yml"));
		}
	}
}