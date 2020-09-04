package model.dao;

import java.util.List;

import model.entidades.Department;
import model.entidades.Seller;

public interface SellerDao {
	void insert(Seller obj);
	void update(Seller obj);
	void deleteById(Integer id);
	Seller findById(Integer id);
	List<Seller> findByDepartment(Department department);
	List<Seller> findAll();	
}
