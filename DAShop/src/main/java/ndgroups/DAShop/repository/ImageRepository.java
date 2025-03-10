package ndgroups.DAShop.repository;

import ndgroups.DAShop.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    public List<Image> findByProductProductId(Integer productId);
}
