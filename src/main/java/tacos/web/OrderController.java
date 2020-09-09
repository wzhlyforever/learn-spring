package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.Order;
import tacos.OrderProps;
import tacos.User;
import tacos.data.OrderRepository;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderProps props;
    private OrderRepository orderRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo, OrderProps orderProps) {
        this.orderRepo = orderRepo;
        this.props = orderProps;
    }


    @GetMapping("/current")
    public String orderForm(Model model)
    {
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        order.setUser(user);
        orderRepo.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model){
        Pageable pageable = PageRequest.of(0, props.getPageSize());   //
        model.addAttribute("orders", orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }
}
