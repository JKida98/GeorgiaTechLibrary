package GTL_API.Services;

import GTL_API.Models.ReturnModels.CoverTypeReturn;
import GTL_API.Repositories.CoverTypeRepository.CoverTypeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class CoverTypeServiceTest {

    private CoverTypeReturn coverTypeReturn;

    @Before
    public void setUp() {
        coverTypeReturn = new CoverTypeReturn();
        coverTypeReturn.setCoverType("TestCoverTypeName");
    }

    @MockBean
    private CoverTypeRepository coverTypeRepository;


    @Test
    public void whenFindByCoverTypeName_thenReturnCoverTypeReturn() {
        Mockito.when(coverTypeRepository.findCoverTypeByName(Mockito.anyString()))
                .thenReturn(this.coverTypeReturn);

        CoverTypeReturn found = coverTypeRepository.findCoverTypeByName("ASD");


        Assert.assertEquals("TestCoverTypeName", found.getCoverType());
    }

}
