package com.xuyh.service;

import com.squareup.javapoet.*;
import com.xuyh.annotation.MyAnnotation;
import org.springframework.stereotype.Service;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XUYH
 * @Description: 自动生成代码服务,该类主要列出可用方法，具体业务使用的时候需要优化，动态生成
 * @Date: 2018/12/21
 * @Version:
 */
@Service
public class PoetService {

    /**
     * @Author: xuyh
     * @Description: 生成类注解
     * @Date: 10:28 2018/12/21
     */
    private AnnotationSpec genClassAnnotationSpec(){
        AnnotationSpec.Builder annotationBuilder = AnnotationSpec.builder(MyAnnotation.class);
        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder().add("$S", "poetModel");
        annotationBuilder.addMember("value", codeBlockBuilder.build());
        return annotationBuilder.build();
    }

    /**
     * @Author: xuyh
     * @Description: 生成字段列表，返回list
     * @Date: 10:48 2018/12/21
     */
    private List<FieldSpec> genFieldSpec(){
        // 定义集合以及Builder
        List<FieldSpec> fieldSpecList  = new ArrayList<>();
        FieldSpec.Builder fieldBuilder = null;
        // 定义name字段
        fieldBuilder = FieldSpec.builder(String.class, "name", Modifier.PRIVATE);
        // 设置字段默认值
        fieldBuilder.initializer(CodeBlock.of("$S","poetTest"));
        // 加入集合
        fieldSpecList.add(fieldBuilder.build());
        // 定义描述字段
        fieldBuilder = FieldSpec.builder(String.class, "description", Modifier.PRIVATE);
        fieldBuilder.initializer("$S", "poet 生成代码测试类");
        fieldSpecList.add(fieldBuilder.build());
        // 定义是否实体字段
        fieldBuilder = FieldSpec.builder(TypeName.BOOLEAN, "isEntity", Modifier.PRIVATE);
        fieldBuilder.initializer(CodeBlock.of("false"));
        fieldSpecList.add(fieldBuilder.build());

        return fieldSpecList;
    }
    /**
     * @Author: xuyh
     * @Description: 生成方法
     * @Date: 10:50 2018/12/21
     */
    private List<MethodSpec> genMethodSpec(){
       List<MethodSpec> methodSpecList = new ArrayList<>();
       methodSpecList.add(genToStringMethodSpec());
       methodSpecList.addAll(genGetMethodSpec());
       methodSpecList.addAll(genSetMethodSpec());
       return methodSpecList;
    }
    /**
     * @Author: xuyh
     * @Description: 生成toString方法
     * @Date: 10:59 2018/12/21
     */
    private MethodSpec genToStringMethodSpec(){
        // 定义方法名
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("toString");
        // 增加注释
        methodBuilder.addAnnotation(Override.class);
        // 设置方法作用域
        methodBuilder.addModifiers(Modifier.PUBLIC);
        // 设置返回值
        methodBuilder.returns(String.class);
        // 定义、编写方法内容
        CodeBlock.Builder toStringCodeBuilder = CodeBlock.builder();
        toStringCodeBuilder.beginControlFlow("if( name != null )");
        toStringCodeBuilder.add(CodeBlock.of("return this.name +\"poetTest\";\n"));
        toStringCodeBuilder.nextControlFlow("else");
        toStringCodeBuilder.add(CodeBlock.of("return \"\";\n"));
        toStringCodeBuilder.endControlFlow();
        // 填充方法内容
        methodBuilder.addCode(toStringCodeBuilder.build());
        return methodBuilder.build();
    }
    /**
     * @Author: xuyh
     * @Description: 生成get方法
     * @Date: 10:59 2018/12/21
     */
    private List<MethodSpec> genGetMethodSpec(){
        // 定义集合
        List<MethodSpec> methodSpecList = new ArrayList<>();
        // 定义builder
        MethodSpec.Builder getMethodSpecBuilder = null;
        // 定义getName方法
        getMethodSpecBuilder = MethodSpec.methodBuilder("getName");
        // 设置方法作用域
        getMethodSpecBuilder.addModifiers(Modifier.PUBLIC);
        // 设置返回值类型
        getMethodSpecBuilder.returns(String.class);
        // 填充方法内容
        getMethodSpecBuilder.addCode(CodeBlock.builder().add("return this.name;\n").build());
        // 加入集合
        methodSpecList.add(getMethodSpecBuilder.build());

        // 定义getDescription方法
        getMethodSpecBuilder = MethodSpec.methodBuilder("getDescription");
        getMethodSpecBuilder.addModifiers(Modifier.PUBLIC);
        getMethodSpecBuilder.returns(String.class);
        getMethodSpecBuilder.addCode(CodeBlock.builder().add("return this.description;\n").build());
        methodSpecList.add(getMethodSpecBuilder.build());
        // 定义isEntity方法
        getMethodSpecBuilder = MethodSpec.methodBuilder("isEntity");
        getMethodSpecBuilder.addModifiers(Modifier.PUBLIC);
        getMethodSpecBuilder.returns(TypeName.BOOLEAN);
        getMethodSpecBuilder.addCode(CodeBlock.builder().add("return this.isEntity;\n").build());
        methodSpecList.add(getMethodSpecBuilder.build());
        return methodSpecList;
    }
    /**
     * @Author: xuyh
     * @Description: 生成set方法
     * @Date: 10:59 2018/12/21
     */
    private List<MethodSpec> genSetMethodSpec(){
        List<MethodSpec> methodSpecList = new ArrayList<>();
        MethodSpec.Builder setMethodSpecBuilder = MethodSpec.methodBuilder("setName");
        setMethodSpecBuilder.addModifiers(Modifier.PUBLIC);
        setMethodSpecBuilder.returns(TypeName.VOID);
        setMethodSpecBuilder.addParameter(ParameterSpec.builder(TypeName.get(String.class), "name").build());
        setMethodSpecBuilder.addCode(CodeBlock.builder().add("this.name = name;\n").build());
        methodSpecList.add(setMethodSpecBuilder.build());

        setMethodSpecBuilder = MethodSpec.methodBuilder("setDescription");
        setMethodSpecBuilder.addModifiers(Modifier.PUBLIC);
        setMethodSpecBuilder.returns(TypeName.VOID);
        setMethodSpecBuilder.addParameter(ParameterSpec.builder(TypeName.get(String.class), "description").build());
        setMethodSpecBuilder.addCode(CodeBlock.builder().add("this.description = description;\n").build());
        methodSpecList.add(setMethodSpecBuilder.build());

        setMethodSpecBuilder = MethodSpec.methodBuilder("setIsEntity");
        setMethodSpecBuilder.addModifiers(Modifier.PUBLIC);
        setMethodSpecBuilder.returns(TypeName.VOID);
        setMethodSpecBuilder.addParameter(ParameterSpec.builder(TypeName.BOOLEAN, "isEntity").build());
        setMethodSpecBuilder.addCode(CodeBlock.builder().add("this.isEntity = isEntity;\n").build());
        methodSpecList.add(setMethodSpecBuilder.build());
        return methodSpecList;
    }

    public String genJavaFile(){
        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder("PoetModel");
        typeSpecBuilder.addModifiers(Modifier.PUBLIC);
        typeSpecBuilder.addAnnotation(genClassAnnotationSpec());
        typeSpecBuilder.addFields(genFieldSpec());
        typeSpecBuilder.addMethods(genMethodSpec());
        JavaFile.Builder javaFileBuilder = JavaFile.builder("com.xuyh.model", typeSpecBuilder.build());
        JavaFile javaFile = javaFileBuilder.build();
        String javaFileString = javaFileBuilder.build().toString();
        File outputFile = new File("D:\\IdeaProject\\SpringBootDemo\\src\\main\\java");
        try {
            javaFile.writeTo(outputFile);
            javaFile.writeTo(System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return javaFileString;
    }
}
