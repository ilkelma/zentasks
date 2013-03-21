package controllers;

import org.junit.*;
import java.util.*;

import play.mvc.*;
import play.libs.*;
import play.test.*;
import static play.test.Helpers.*;
import com.avaje.ebean.Ebean;
import com.google.common.collect.ImmutableMap;

import static org.fest.assertions.Assertions.*;

public class LoginTest extends WithApplication {
	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
		Ebean.save((List) Yaml.load("test-data.yml"));
	}

	@Test 
	public void authenticateSuccess() {
		Result result = callAction(controllers.routes.ref.Application.authenticate(),
								fakeRequest().withFormUrlEncodedBody(ImmutableMap.of(
									"email", "bob@example.com",
									"password", "secret"))
								);
		assertThat(status(result)).isEqualTo(303);
		assertThat(session(result).get("email")).isEqualTo("bob@example.com");
	}

	@Test
	public void authenticateFailure() {
		Result result = callAction(controllers.routes.ref.Application.authenticate(),
								fakeRequest().withFormUrlEncodedBody(ImmutableMap.of(
									"email", "bob@example.com",
									"password", "badpassword"))
								);
		assertThat(status(result)).isEqualTo(400);
		assertThat(session(result).get("email")).isNull();
	}
}