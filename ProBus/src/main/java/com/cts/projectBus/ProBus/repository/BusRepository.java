package com.cts.projectBus.ProBus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.projectBus.ProBus.entity.Bus;


public interface BusRepository extends JpaRepository<Bus , String>{
	@Query("SELECT b FROM Bus b WHERE b.source = ?1 "
            + "AND b.destination = ?2 ")
	public List<Bus> searchBus(String source,String destination);
	
	@Query(value ="select b from Bus b where b.source=:source")
	public List<Bus> getBusByStartLoc(String source);
	
	@Query(value ="select * from Bus b where b.source=:source" , nativeQuery = true)
	public List<Bus> getBusByStartLocNativeQuery(String source);
	
	@Query("SELECT b FROM Bus b WHERE b.source = :source AND b.destination = :destination AND b.capacity >= :capacity")
	List<Bus> findBySourceAndDestinationAndCapacityGreaterThanEqual(@Param("source") String source, @Param("destination") String destination, @Param("capacity") int capacity);

	@Query("SELECT b FROM Bus b WHERE b.source = ?1 AND b.destination = ?2 AND b.busName=?3")
	public List<Bus> searchPurticularBus(String source, String destination, String busName);


}
