package ec.edu.insteclrg.app.api.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.insteclrg.common.dto.ApiResponseDTO;
import ec.edu.insteclrg.domain.Institucion;
import ec.edu.insteclrg.dto.InstitucionDTO;
import ec.edu.insteclrg.dto.TipoInstitucionDTO;
import ec.edu.insteclrg.service.crud.InstitucionService;

@RestController
@RequestMapping(value = "/api/v1.0/institucion")
public class InstitucionController {

	@Autowired
	private InstitucionService service; 
	
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody InstitucionDTO dto){
		service.save(dto);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, null), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody InstitucionDTO dto){
		service.update(id, dto);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, null), HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public ResponseEntity<Object> findAll(){
		List<InstitucionDTO> list= service.findAll(new InstitucionDTO());
		if(!list.isEmpty()) {
			ApiResponseDTO<List<InstitucionDTO>> response = new ApiResponseDTO<>(true, list);
			return (new ResponseEntity<Object>(response,HttpStatus.OK));
		}else {
			return new ResponseEntity<>(new ApiResponseDTO<>(false, null), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> find(@PathVariable Long id){
		InstitucionDTO dto = new InstitucionDTO();
		dto.setId(id);
		Optional<Institucion> optional = service.find(dto);
		if(optional.isPresent()) {
			ApiResponseDTO<Institucion> response = new ApiResponseDTO<>(true, optional.get());
			return (new ResponseEntity<Object>(response, HttpStatus.OK));
		}else {
			return new ResponseEntity<>(new ApiResponseDTO<>(false, null), HttpStatus.NOT_FOUND);
		}	
     }
}
