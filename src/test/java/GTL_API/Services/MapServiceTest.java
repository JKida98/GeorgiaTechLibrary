package GTL_API.Services;

import GTL_API.Models.ReturnModels.MapReturn;
import GTL_API.Repositories.MapRepository.MapRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class MapServiceTest {

    private MapReturn mapReturn;

    @Before
    public void setup(){
        mapReturn = new MapReturn();
        mapReturn.setBarcode("123");
    }

    @MockBean
    private MapRepository mapRepository;

    @Test
    public void whenFindByBarcode_thenReturnMapReturn(){
        Mockito.when(mapRepository.findMapByBarcode(Mockito.anyString())).thenReturn(this.mapReturn);

        MapReturn found = mapRepository.findMapByBarcode("123");

        Assert.assertEquals("123",found.getBarcode());
    }
}
