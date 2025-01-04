package ndgroups.DAShop.controller;

import ndgroups.DAShop.service.Interface.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
}
