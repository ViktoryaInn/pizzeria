package pizzeria.controllers;

import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizzeria.dbService.DBService;
import pizzeria.dbService.dataSets.Ingredient;
import pizzeria.dbService.dataSets.Order;

@RestController
@RequestMapping("ingredients")
public class IngredientController {
    private DBService dbService = new DBService();

    @GetMapping()
    public ResponseEntity<Ingredient[]> getList(){
        Ingredient[] ingredients = dbService.getListIngredient();
        if(ingredients.length == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Ingredient> get(@PathVariable String id){
        if (id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Ingredient responseIngredient = dbService.getIngredient(id);
        if(responseIngredient == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(responseIngredient, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Ingredient> create(@RequestBody Ingredient ingredient){
        if (ingredient == null || ingredient.getName() == null || ingredient.getPrice() == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        dbService.addIngredient(ingredient);
        return new ResponseEntity<>(ingredient, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Ingredient> update(@PathVariable String id, @RequestBody Ingredient ingredient){
        if (ingredient == null || ingredient.getId() == null || ingredient.getName() == null || ingredient.getPrice() == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        dbService.updateIngredient(ingredient);
        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Ingredient> delete(@PathVariable String id){
        Ingredient ingredient = dbService.getIngredient(id);
        if(ingredient == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        dbService.deleteIngredient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
