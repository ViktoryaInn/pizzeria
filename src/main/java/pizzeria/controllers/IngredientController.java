package pizzeria.controllers;

import org.springframework.web.bind.annotation.*;
import pizzeria.dbService.DBService;
import pizzeria.dbService.dataSets.Ingredient;
import pizzeria.dbService.dataSets.Order;

@RestController
@RequestMapping("ingredients")
public class IngredientController {
    private DBService dbService = new DBService();

    @GetMapping()
    public Ingredient[] getList(){
        return dbService.getListIngredient();
    }

    @GetMapping("{id}")
    public Ingredient get(@PathVariable String id){
        return dbService.getIngredient(Integer.parseInt(id));
    }

    @PostMapping()
    public void create(@RequestBody Ingredient ingredient){
        dbService.addIngredient(ingredient);
    }

    @PutMapping("{id}")
    public void update(@PathVariable String id, @RequestBody Ingredient ingredient){
        dbService.updateIngredient(ingredient);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        dbService.deleteIngredient(Integer.parseInt(id));
    }

    @GetMapping("orders")
    public Order[] getOrdersList(){
        return dbService.getListOrder();
    }
//    private int counter = 3;
//
//    private List<Map<String,String>> ingredients = new ArrayList<Map<String,String>>(){{
//        add(new HashMap<String, String>() {{put("id", "0"); put("name", "tomato"); put("price", "30");}});
//        add(new HashMap<String, String>() {{put("id", "1"); put("name", "salted cucumbers"); put("price", "40");}});
//        add(new HashMap<String, String>() {{put("id", "2"); put("name", "pepper"); put("price", "50");}});
//    }};
//
//    @GetMapping("{id}")
//    public Map<String, String> get(@PathVariable String id){
//        return getIngredientById(id);
//    }
//
//    @GetMapping()
//    public List<Map<String, String>> getList(){
//        return ingredients;
//    }
//
//    @PostMapping()
//    public Map<String, String> create(@RequestBody Map<String, String> ingredient){
//        ingredient.put("id", String.valueOf(counter++));
//        ingredients.add(ingredient);
//        return ingredient;
//    }
//
//    private Map<String, String> getIngredientById(String id) {
//        return ingredients.stream()
//                .filter(user -> user.get("id").equals(id))
//                .findFirst()
//                .orElseThrow(NotFoundException::new);
//    }
//
//    @PutMapping("{id}")
//    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> ingredient){
//        Map<String, String> ingredientFromDb = getIngredientById(id);
//        ingredientFromDb.putAll(ingredient);
//        ingredientFromDb.put("id", id);
//        return ingredientFromDb;
//    }
//
//    @DeleteMapping("{id}")
//    public void delete(@PathVariable String id){
//        Map<String, String> ingredientFromDb = getIngredientById(id);
//        ingredients.remove(ingredientFromDb);
//    }
}
