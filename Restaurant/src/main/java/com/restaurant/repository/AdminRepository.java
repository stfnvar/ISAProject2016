package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.Administrator;

public interface AdminRepository extends JpaRepository<Administrator, Long> {

	Administrator findById(Long id);

	List<Administrator> findAll();

	Administrator findByEmail(String email);

	@Query("select admins from Administrator as admins where supreme = ?1")
	List<Administrator> getAdminsBySupreme(boolean supreme);

	@Query("update Administrator p set p.name = ?1, p.surname = ?2, p.password=?3 where p.id=?4")
	@Modifying
	void modifyAdmin(String name, String surname, String password, Long id);
}
