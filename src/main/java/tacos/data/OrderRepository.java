package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Order;
import tacos.User;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUserOrderByPlacedAtDesc(User user,  Pageable pageable);
}
