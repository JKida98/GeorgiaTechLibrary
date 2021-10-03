package GTL_API.Controllers;

import GTL_API.Models.CreationModels.MapCreation;
import GTL_API.Services.MapService.IMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("gtl/map")
public class MapController {
    private IMapService iMapService;

    @Autowired
    public void setiMapService(IMapService iMapService) { this.iMapService = iMapService; }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<?> findAllMaps(){
        return new ResponseEntity<>(iMapService.findAllMaps(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{barcode}", method = RequestMethod.GET)
    public ResponseEntity<?> findMapByBarcode(@PathVariable String barcode){
        return new ResponseEntity<>(iMapService.findByBarcode(barcode), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createMap(@RequestBody @Valid MapCreation mapCreation){
        return new ResponseEntity<>(iMapService.createMap(mapCreation), new HttpHeaders(), HttpStatus.OK);
    }
}
