package pizzeria.controllers;//package pizzeria.controllers;
//
//import pizzeria.dbService.dataSets.Order;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.Date;
//
//@Controller
//public class ClientController {
//
//    @GetMapping
//    public String main(@ModelAttribute("order") Order order){
//        return "order";
//    }
//
//    @PostMapping
//    public String order(@ModelAttribute("order")Order order){
//        System.out.println(order.getClientName());
//        order.setDate(new Date());
//        return "done";
//    }
//}

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("client")
public class ClientController {
    @GetMapping
    public String list(){
        return "index";
    }
}