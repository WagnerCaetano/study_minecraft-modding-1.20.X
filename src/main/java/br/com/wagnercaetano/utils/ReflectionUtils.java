package br.com.wagnercaetano.utils;

import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Consumer;

public class ReflectionUtils {

    public static <T> void  getListClassesBasedOnType(List<Field> allFields, Consumer<Object> consumer) {
        allFields.forEach(field -> {
            try {
                if (field.getType().equals(RegistryObject.class)) {
                    consumer.accept(field.get(null));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}
