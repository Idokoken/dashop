package ndgroups.DAShop.repository;

import ndgroups.DAShop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserId(Integer userId);
}
