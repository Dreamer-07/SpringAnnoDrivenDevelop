package pers.dreamer07.springAoon.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import sun.rmi.runtime.Log;

/**
 * @author EMTKnight
 * @create 2021-02-26
 */
public class MyImportSelector implements ImportSelector {

    /**
     *
     * @param importingClassMetadata 当前使用了 @Import 注解的组件的其他注解的信息
     * @return 返回需要导入的组件的全类名构成的字符串数组
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        for (String type : importingClassMetadata.getAnnotationTypes()) {
            System.out.println("其他注解信息:" + type);
        }

        return new String[]{
                "pers.dreamer07.springAoon.bean.Servant",
                "pers.dreamer07.springAoon.bean.Master"
        };
    }

}
