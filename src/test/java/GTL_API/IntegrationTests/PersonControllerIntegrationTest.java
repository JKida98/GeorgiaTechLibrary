package GTL_API.IntegrationTests;

import GTL_API.MainApplicationClass;
import GTL_API.Models.Entities.PersonEntity;
import GTL_API.Repositories.PersonRepository.IPersonRepositoryCustom;
import GTL_API.TestDataSourceConfig;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MainApplicationClass.class, TestDataSourceConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PersonControllerIntegrationTest {
    @Autowired
    IPersonRepositoryCustom personRepository;

    private HttpHeaders headers;

    @Autowired
    private MockMvc mvc;

    private String token;

    @Before
    public void login() throws Exception{
        headers = new HttpHeaders();
        headers.add("Origin","*");
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        headers.add("Access-Control-Max-Age", "3600");
        headers.add("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        MvcResult result = mvc.perform(post("/gtl/auth/login").headers(headers)
                .content("{\"login\": \"1045946125\", \"password\": \"Pass011-53-8195word\"}")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        JSONObject obj = new JSONObject(result.getResponse().getContentAsString());
        token = obj.getString("authToken");
        headers.add("Authorization", "Bearer " + token);
    }

    @Test
    public void findPersonBySsn() throws Exception {
        mvc.perform(get("/gtl/person/findbyssn/000-71-3764")
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"ssn\": \"000-71-3764\",\n" +
                        "    \"firstName\": \"Bart\",\n" +
                        "    \"middleName\": \"Aaron Prince\",\n" +
                        "    \"lastName\": \"Bennet\",\n" +
                        "    \"homeAddressId\": 3052,\n" +
                        "    \"campusAddressId\": 3052,\n" +
                        "    \"loanDuration\": 21,\n" +
                        "    \"cardNumberId\": 1027294938,\n" +
                        "    \"personTypeId\": 102084}"));
    }

    @Test
    public void updatePersonLastName() throws Exception {
        mvc.perform(post("/gtl/person/")
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"ssn\": \"000-71-3764\", \"lastName\": \"test\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"ssn\": \"000-71-3764\",\n" +
                        "    \"firstName\": \"Bart\",\n" +
                        "    \"middleName\": \"Aaron Prince\",\n" +
                        "    \"lastName\": \"test\",\n" +
                        "    \"homeAddressId\": 3052,\n" +
                        "    \"campusAddressId\": 3052,\n" +
                        "    \"loanDuration\": 21,\n" +
                        "    \"cardNumberId\": 1027294938,\n" +
                        "    \"personTypeId\": 102084}"));

        cleanup();
    }

    @Test
    public void findPersonWhichDoesNotExistBySsn() throws Exception {
        mvc.perform(get("/gtl/person/findbyssn/1")
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Person with ssn: 1 was not found"));
    }

    @Test
    public void findPersonByName() throws Exception {
        mvc.perform(get("/gtl/person/findbyname/Bart/Bennet")
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"ssn\": \"000-71-3764\",\n" +
                        "    \"firstName\": \"Bart\",\n" +
                        "    \"middleName\": \"Aaron Prince\",\n" +
                        "    \"lastName\": \"Bennet\",\n" +
                        "    \"homeAddressId\": 3052,\n" +
                        "    \"campusAddressId\": 3052,\n" +
                        "    \"loanDuration\": 21,\n" +
                        "    \"cardNumberId\": 1027294938,\n" +
                        "    \"personTypeId\": 102084}"));
    }

    @Test
    public void findPersonByCard() throws Exception {
        mvc.perform(get("/gtl/person/findbycard/1027294938")
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"ssn\": \"000-71-3764\",\n" +
                        "    \"firstName\": \"Bart\",\n" +
                        "    \"middleName\": \"Aaron Prince\",\n" +
                        "    \"lastName\": \"Bennet\",\n" +
                        "    \"homeAddressId\": 3052,\n" +
                        "    \"campusAddressId\": 3052,\n" +
                        "    \"loanDuration\": 21,\n" +
                        "    \"cardNumberId\": 1027294938,\n" +
                        "    \"personTypeId\": 102084}"));
    }

    public void cleanup(){
        PersonEntity person = new PersonEntity();
        person.setSsn("000-71-3764");
        person.setFirstName("Bart");
        person.setMiddleName("Aaron Prince");
        person.setLastName("Bennet");
        person.setHomeAddressId(3052);
        person.setCampusAddressId(3052);
        person.setLoanDuration(21);
        person.setPersonTypeId(102084);


        personRepository.updatePerson(person);
    }
}
