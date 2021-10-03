package GTL_API.Repositories;

import GTL_API.MainApplicationClass;
import GTL_API.Models.ReturnModels.MapReturn;
import GTL_API.Repositories.MapRepository.MapRepository;
import GTL_API.TestDataSourceConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { MainApplicationClass.class, TestDataSourceConfig.class })
public class MapRepositoryTest {

    @Autowired
    private MapRepository mapRepository;

    /**
     * The test method finds a map by its barcode by using map repository instance and asserts
     * its getAddedDate value to an expected value.
     */
    @Test
    public void findMapByBarcode_returnsMapReturn(){
        MapReturn found = mapRepository.findMapByBarcode("000244159972");
        Assert.assertEquals("000244159972", found.getBarcode());
    }
}
