package models;

import models.*;
import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;
import com.avaje.ebean.*;
import play.libs.Yaml;
import java.util.List;

public class ModelsTest extends WithApplication {

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}

	@Test
	public void fullTest() {
		//Ebean.save((List) Yaml.load("test-data.yml"));

		//Count Things
		assertEquals(3, User.find.findRowCount());
		assertEquals(7, Project.find.findRowCount());
		assertEquals(5, Task.find.findRowCount());

		//Try to authenticate as users
		assertNotNull(User.authenticate("bob@example.com", "secret"));
		assertNotNull(User.authenticate("jane@example.com", "secret"));
		assertNull(User.authenticate("jeff@example.com", "badpassword"));
		assertNull(User.authenticate("tom@example.com", "secret"));

		// Find All Bob's projects
		List<Project> bobsProjects = Project.findInvolving("bob@example.com");
		assertEquals(5, bobsProjects.size());

		// Find all Bob's todo tasks
		List<Task> bobsTasks = Task.findTodoInvolving("bob@example.com");
		assertEquals(4, bobsTasks.size());
	}
}