package com.yw.springbootdemo.enums;

/**
 * @author yangwei
 * @date 2019-12-11 13:52
 */
public enum Meal {

    APPRTIZER(Food.Apprtizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);

    private Food[] values;
    private Meal(Class<? extends Food> kind) {
        //通过class对象获取枚举实例
        values = kind.getEnumConstants();
    }

    public interface Food {
        enum Apprtizer implements Food {
            SALAD, SOUP, SPRING_ROLLS;
        }
        enum MainCourse implements Food {
            LASAGNE, BURRITO, PAD_THAI,
            LENTILS, HUMMOUS, VINDALOO;
        }
        enum Dessert implements Food {
            TIRAMISU, GELATO, BLACK_FOREST_CAKE,
            FRUIT, CREME_CARAMEL;
        }
        enum Coffee implements Food {
            BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
            LATTE, CAPPUCCINO, TEA, HERB_TEA;
        }
    }
}
