package pizzeria.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizzeria.dbService.DBService;
import pizzeria.dbService.dataSets.Order;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("orders")
public class OrderController {
    private DBService dbService = new DBService();

    @GetMapping()
    public Order[] getList(){
        return dbService.getListOrder();
    }

    @GetMapping("{id}")
    public Order get(@PathVariable String id){
        return dbService.getOrder(id);
    }

    @PostMapping()
    public ResponseEntity<Order> create(@RequestBody Order order) {
        order.setDate(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        dbService.addOrder(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public void update(@PathVariable String id, @RequestBody Order order){
        dbService.updateOrder(order);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        dbService.deleteOrder(id);
    }
}
