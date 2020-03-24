package eci.ieti.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import eci.ieti.data.model.ToDo;

public interface ToDoRepository extends CrudRepository<ToDo, Long>{

	Page<ToDo> findByDescriptionContaining(String description,
			Pageable pageable );
	
	Page<ToDo> findByResponsible(String responsible,
			Pageable pageble);
	
}
