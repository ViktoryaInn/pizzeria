package pizzeria.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizzeria.dbService.DBService;
import pizzeria.dbService.dataSets.Ingredient;
import pizzeria.dbService.dataSets.Order;
import pizzeria.dbService.dataSets.OrderIngredients;
import pizzeria.dto.OrderDTO;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;

@RestController
@RequestMapping("orders")
public class OrderController {
    private DBService dbService = new DBService();

    @GetMapping()
    public ResponseEntity<OrderDTO[]> getList(){
        Order[] orders = dbService.getListOrder();
        if(orders.length == 0){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        var responseOrders = new LinkedList<OrderDTO>();
        for(Order order: orders){
            Ingredient[] ingredients = dbService.getIngredientsByOrder(order.getId());
            responseOrders.add(new OrderDTO(order.getClientName(), order.getClientPhone(), order.getCost(), ingredients));
        }

        return new ResponseEntity<>(responseOrders.toArray(new OrderDTO[0]), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> get(@PathVariable String id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Order order = dbService.getOrder(id);
        if(order == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Ingredient[] ingredients = dbService.getIngredientsByOrder(order.getId());
        OrderDTO responseOrder = new OrderDTO(order.getClientName(), order.getClientPhone(), order.getCost(), ingredients);
        return new ResponseEntity<>(responseOrder, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Order> create(@RequestBody OrderDTO requestOrder) {
        if(requestOrder == null || requestOrder.getClientName() == null || requestOrder.getClientPhone() == null || requestOrder.getCost() == 0 || requestOrder.getIngredients().length == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Order order = new Order(requestOrder.getClientName(), requestOrder.getClientPhone(), requestOrder.getCost(), java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        dbService.addOrder(order);
        for(Ingredient ingredient: requestOrder.getIngredients()){
            dbService.addIngredientToOrder(order.getId(), ingredient.getId());
        }
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
