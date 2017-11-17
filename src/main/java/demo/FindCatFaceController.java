package demo;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class FindCatFaceController {
	
	/**
	 * API Info 
	 * @return
	 */
	@RequestMapping("/api/v1/info")
	public ResponseEntity<Response> appsInfo() {
		Response rsp = new Response();
		ArrayList<String> results = new ArrayList<String>();
		results.add("CatFace Demo 1.0");
		rsp.setResults(results);
		return new ResponseEntity<Response>(rsp, HttpStatus.OK);
	}
	
	/**
	 * Find catface API
	 * @param Request 
	 * @return Positions of catface found in the matrix  
	 */
	@ApiOperation(value = "find cat face matchs the image of the perfect cat face")
	@RequestMapping(value = "/api/v1/catface", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Response> find(@RequestBody Request req) {		
		double confidence = req.getSimilarity();
		ArrayList<String> img = req.getImgMatrix();
		
		CatFaceDetecter detecter = new CatFaceDetecter(img, confidence);	
		ArrayList<String> results = detecter.findCatFace();
	
		Response rsp = new Response();
		rsp.setResults(results);
		return new ResponseEntity<Response>(rsp, HttpStatus.OK);
	}	
}
