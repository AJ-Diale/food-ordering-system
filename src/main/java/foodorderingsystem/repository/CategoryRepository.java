package foodorderingsystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import foodorderingsystem.entity.Category;
/* Responsible for database operations*/
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}