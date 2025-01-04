package ndgroups.DAShop.repository;

import ndgroups.DAShop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    void deleteAllByCartId(Integer cartId);
}
