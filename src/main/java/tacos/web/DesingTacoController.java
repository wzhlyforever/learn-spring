package tacos.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.Ingredient;
import tacos.Order;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesingTacoController {

    private final IngredientRepository ingredientRepository;

    private final TacoRepository designRepo;

    @Autowired
    public DesingTacoController(IngredientRepository ingredientRepository, TacoRepository designRepo){
        this.ingredientRepository = ingredientRepository;
        this.designRepo = designRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "order")
    public Order order()
    {
        return new Order();
    }

    @GetMapping
    public String showDesignForm(Model model){
        model.addAttribute("design", new Taco());
        return "design";
    }


   /* @ModelAttribute(name = "design")
    public Taco design() {
        return new Taco();
    }*/

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, @ModelAttribute Order order)    // 添加验证
    {

        if (errors.hasErrors()) return "design";
        //
        log.info("Processing design: " + design);
        Taco saved = designRepo.save(design);
        order.addDesign(saved);
        return "redirect:/orders/current";
    }
    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type){
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

}
