package tacos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;


@Data    //自动补充缺少的方法，包括覆盖的方法
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity                  //将Ingredient 声明为JPA实体
public class Ingredient{
    @Id
    private final String id;
    private final String name;
    @Enumerated(EnumType.STRING)
    private final Type type;


    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}