package pizzeria.controllers;

import org.springframework.web.bind.annotation.*;
import pizzeria.dbService.DBService;
import pizzeria.dbService.dataSets.Ingredient;
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
        return dbService.getOrder(Integer.parseInt(id));
    }

    @PostMapping()
    public void create(@RequestBody Order order){
        order.setDate(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        dbService.addOrder(order);
    }

    @PutMapping("{id}")
    public void update(@PathVariable String id, @RequestBody Ingredient ingredient){
        dbService.updateIngredient(ingredient);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        dbService.deleteIngredient(Integer.parseInt(id));
    }
}
