package GTL_API.IntegrationTests;

import GTL_API.MainApplicationClass;
import GTL_API.Repositories.BookBorrowRepository.IBookBorrowRepository;
import GTL_API.Services.BookReturnService.IBookReturnService;
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
public class BookLoanActivitiesIntegrationTest {

    @Autowired
    IBookBorrowRepository iBookBorrowRepository;

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
    public void borrowBookValid() throws Exception {

        mvc.perform(post("/gtl/borrow/")
                .headers(headers)
                .content("{\"bookCatalogId\": \"18\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"title\": \"Zeeglibin\",\n" +
                        "    \"author\": \"Valerie Hill\",\n" +
                        "    \"releasedDate\": \"2010-01-02\",\n" +
                        "    \"catalogId\": 18\n" +
                        "}"));

    }
    @Test
    public void returnBookValid() throws Exception {

        mvc.perform(post("/gtl/return/")
                .headers(headers)
                .content("{\"bookCatalogId\": \"18\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void borrowSameBookTwoTimesValid() throws Exception {

        mvc.perform(post("/gtl/borrow/")
                .headers(headers)
                .content("{\"bookCatalogId\": \"18\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"title\": \"Zeeglibin\",\n" +
                        "    \"author\": \"Valerie Hill\",\n" +
                        "    \"releasedDate\": \"2010-01-02\",\n" +
                        "    \"catalogId\": 18\n" +
                        "}"));

        mvc.perform(post("/gtl/borrow/")
                .headers(headers)
                .content("{\"bookCatalogId\": \"18\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("The book was not found or is not available."));

    }

    @Test
    public void returnSameBookTwoTimesValid() throws Exception {

        mvc.perform(post("/gtl/return/")
                .headers(headers)
                .content("{\"bookCatalogId\": \"18\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        mvc.perform(post("/gtl/return/")
                .headers(headers)
                .content("{\"bookCatalogId\": \"18\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Book is not borrowed"));

    }

    @Test
    public void borrowBookInValidBookCatalogId() throws Exception {

        mvc.perform(post("/gtl/borrow/")
                .headers(headers)
                .content("{\"bookCatalogId\": \"-1\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(content().string("There was an error while finding a book catalog with ID: -1"));

    }
    @Test
    public void returnBookInValidBookCatalogId() throws Exception {

        mvc.perform(post("/gtl/return/")
                .headers(headers)
                .content("{\"bookCatalogId\": \"-1\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(content().string("There was an error while finding a book catalog with ID: -1"));

    }

    @Test
    public void borrowBookInValidNotNumericInput() throws Exception {

        mvc.perform(post("/gtl/borrow/")
                .headers(headers)
                .content("{\"bookCatalogId\": \"abs\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }
    @Test
    public void returnBookInValidNotNumericInput() throws Exception {

        mvc.perform(post("/gtl/return/")
                .headers(headers)
                .content("{\"bookCatalogId\": \"abc\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void borrowBookInValidEmptyInput() throws Exception {

        mvc.perform(post("/gtl/borrow/")
                .headers(headers)
                .content("{\"bookCatalogId\": \"\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("ID of a book catalog must be set\n"));

    }
    @Test
    public void returnBookInValidEmptyInput() throws Exception {

        mvc.perform(post("/gtl/return/")
                .headers(headers)
                .content("{\"bookCatalogId\": \"\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("ID of a book catalog must be set\n"));

    }
}
