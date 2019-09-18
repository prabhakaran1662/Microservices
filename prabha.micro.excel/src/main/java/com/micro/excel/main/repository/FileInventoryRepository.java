package com.micro.excel.main.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.micro.excel.main.been.PerficientData;


@Service
public interface FileInventoryRepository extends CrudRepository<PerficientData, Long>{

	@Query(value = "SELECT * FROM perficient.fileinventory file",nativeQuery=true)
	List<PerficientData> findLastElement(Pageable limit);
}
