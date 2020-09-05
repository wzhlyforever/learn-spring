package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByDeliveryZip(String deliveryZip);
}
