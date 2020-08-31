package tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data    //自动补充缺少的方法，包括覆盖的方法
@RequiredArgsConstructor
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN,VEGGIES, CHEESE, SAUCE
    }
}
