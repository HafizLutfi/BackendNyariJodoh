package com.juaracoding.backendnyarijodoh.respository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.juaracoding.backendnyarijodoh.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	@Query(value="SELECT * from user where username=?1 and nomor=?2",nativeQuery = true)
	User findByLogin(String username, String nomor);

	@Query(value="SELECT * from user where jenis_kelamin=?1",nativeQuery = true)
	List<User> findAllByJenisKelamin(String jeniskelamin);

	User findByid(long id);
	
}
