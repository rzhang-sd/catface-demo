package demo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ApplicationTest {
	
	public MockMvc mvc;
	
	public TestRestTemplate testTemplate;
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(new FindCatFaceController()).build();
	}
	
	@Test
	@Ignore
	public void testAPI() {
		this.testTemplate = new TestRestTemplate();
		Request req = new Request();
		ResponseEntity<Response> ret = this.testTemplate.getForEntity("http://localhost:8080/api/v1/info", Response.class);
		assertEquals(ret.getStatusCode(), HttpStatus.OK);
	}
}
