package com.example.complier;

import com.example.annotation.NeedPermission;
import com.example.annotation.OnNeverAsAgain;
import com.example.annotation.OnPermissionDenied;
import com.example.annotation.OnShowRationable;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;


@AutoService(Processor.class)
public class PermissionProcessor extends AbstractProcessor {

    private Messager messager;          //用来报告错误、警告、提示
    private Elements elementsUtils;     //包含了很多操作Element的工具方法
    private Filer filer;                //用来创建新的源文件（造币器），例如：class文件
    private Types typeUtils;            //包含用于操作TypeMIrro工具方法

    //初始化操作
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        messager=processingEnvironment.getMessager();
        elementsUtils=processingEnvironment.getElementUtils();
        filer=processingEnvironment.getFiler();
        typeUtils=processingEnvironment.getTypeUtils();
    }

    //获取支持注解的类型
    @Override
    public Set<String> getSupportedAnnotationTypes() {

        Set<String> types=new LinkedHashSet<>();
        types.add(NeedPermission.class.getCanonicalName());
        types.add(OnNeverAsAgain.class.getCanonicalName());
        types.add(OnPermissionDenied.class.getCanonicalName());
        types.add(OnShowRationable.class.getCanonicalName());

        return types;
    }

    //返回注解支持的最新源版本，JDK版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    //注解处理器的核心方法，处理具体的注解实现，生成java文件
    //强调的是一行一行代码去写
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        //获取MainActivity中所有带NeedPermission注解的方法
        Set<? extends Element> needsPermissionSet = roundEnvironment.getElementsAnnotatedWith(NeedPermission.class);
        //保存起来，以键值对的方式： key——Activity对应的包名      value——所有带NeedPermission注解的方法
        Map<String, List<ExecutableElement>> needsPermissionMap = new HashMap<>();
        //遍历所有带NeedPermission注解的方法
        for (Element element : needsPermissionSet) {
            //转换成元素（结构体元素）
            ExecutableElement executableElement=(ExecutableElement)element;
            //获取方法对应的Activity完整类名
            String activityName = getActivityName(executableElement);
            //从缓存集合中获取MainActivity所带NeedPermission注解的方法集合
            List<ExecutableElement> list=needsPermissionMap.get(activityName);
            if(list==null){
                list=new ArrayList<>();
                needsPermissionMap.put(activityName,list);
            }
            list.add(executableElement);
        }

        //获取MainActivity中所有带onPermissionDenied注解的方法
        Set<? extends Element> onPermissionDeniedSet = roundEnvironment.getElementsAnnotatedWith(OnPermissionDenied.class);
        //保存起来，以键值对的方式： key——Activity对应的包名      value——所有带NeedPermission注解的方法
        Map<String, List<ExecutableElement>> onPermissionDeniedMap = new HashMap<>();
        //遍历所有带NeedPermission注解的方法
        for (Element element : onPermissionDeniedSet) {
            //转换成元素（结构体元素）
            ExecutableElement executableElement=(ExecutableElement)element;
            //获取方法对应的Activity完整类名
            String activityName = getActivityName(executableElement);
            //从缓存集合中获取MainActivity所带NeedPermission注解的方法集合
            List<ExecutableElement> list=onPermissionDeniedMap.get(activityName);
            if(list==null){
                list=new ArrayList<>();
                onPermissionDeniedMap.put(activityName,list);
            }
            list.add(executableElement);
        }

        //获取MainActivity中所有带onNeverAsAgain注解的方法
        Set<? extends Element> OnNeverAsAgainSet = roundEnvironment.getElementsAnnotatedWith(OnNeverAsAgain.class);
        //保存起来，以键值对的方式： key——Activity对应的包名      value——所有带NeedPermission注解的方法
        Map<String, List<ExecutableElement>> onNeverAsAgainMap = new HashMap<>();
        //遍历所有带NeedPermission注解的方法
        for (Element element : OnNeverAsAgainSet) {
            //转换成元素（结构体元素）
            ExecutableElement executableElement=(ExecutableElement)element;
            //获取方法对应的Activity完整类名
            String activityName = getActivityName(executableElement);
            //从缓存集合中获取MainActivity所带NeedPermission注解的方法集合
            List<ExecutableElement> list=onNeverAsAgainMap.get(activityName);
            if(list==null){
                list=new ArrayList<>();
                onNeverAsAgainMap.put(activityName,list);
            }
            list.add(executableElement);
        }

        //获取MainActivity中所有带OnShowRationable注解的方法
        Set<? extends Element> onShowRationableSet = roundEnvironment.getElementsAnnotatedWith(OnShowRationable.class);
        //保存起来，以键值对的方式： key——Activity对应的包名      value——所有带NeedPermission注解的方法
        Map<String, List<ExecutableElement>> onShowRationableMap = new HashMap<>();
        //遍历所有带NeedPermission注解的方法
        for (Element element : onShowRationableSet) {
            //转换成元素（结构体元素）
            ExecutableElement executableElement=(ExecutableElement)element;
            //获取方法对应的Activity完整类名
            String activityName = getActivityName(executableElement);
            //从缓存集合中获取MainActivity所带NeedPermission注解的方法集合
            List<ExecutableElement> list=onShowRationableMap.get(activityName);
            if(list==null){
                list=new ArrayList<>();
                onShowRationableMap.put(activityName,list);
            }
            list.add(executableElement);
        }

        //——————————————————————————————————————造币技术——————————————————————
        //获取Activity完整的字符串类名（包名+类名）
        for (String activityName : needsPermissionMap.keySet()) {
            //获取 activityName 对应的所有方法元素的集合
            List<ExecutableElement> needsPermissionElements=needsPermissionMap.get(activityName);
            List<ExecutableElement> onNeverAsAgainElements=onNeverAsAgainMap.get(activityName);
            List<ExecutableElement> onPermissionDenied=onPermissionDeniedMap.get(activityName);
            List<ExecutableElement> onShowRationableElements=onShowRationableMap.get(activityName);

            final String CLASS_SUFFIX = "$Permissions";

            try {
                filer=processingEnv.getFiler();
                //创建一个新的原文件（class），并返回一个对象以允许他写入（MainActivity$Permissions）
                JavaFileObject javaFileObject = filer.createSourceFile(activityName + CLASS_SUFFIX);
                //通过方法标签获得包名标签
                String packageName=getPackageName(needsPermissionElements.get(0));

                //定义Writer对象，开启造币过程
                Writer writer = javaFileObject.openWriter();

                String activitySimpleName=needsPermissionElements.get(0).getSimpleName().toString()+CLASS_SUFFIX;

                writer.write("package "+packageName+";\n");

                writer.write("import android.content.Intent;\n" +
                        "import android.support.v7.app.AppCompatActivity;\n" +
                        "import android.os.Bundle;\n" +
                        "import android.util.Log;\n" +
                        "import android.support.annotation.NonNull;\n" +
                        "import com.example.library.PermissionLibrary.Listener.RequestPermission;");

                writer.write("public class "+activitySimpleName+"{\n");
//                writer.write("public class "+activitySimpleName+" implements RequestPermission<"+activityName+">{\n");
//
//                writer.write("public void requestPermission("+activityName+"  target, String[] permissions) {\n" +
//                        "    }");
//
//                writer.write("public void onRequestPermissionResults("+activityName+" target, int requestCode, @NonNull int[] grantResult) {\n" +
//                        "    }");

                writer.write("\n}");
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return false;
    }

    private String getPackageName(ExecutableElement executableElement){
        //通过方法标签获取类标签
        TypeElement typeElement=(TypeElement)executableElement.getEnclosingElement();
        //通过类标签获取包标签
        String packageName=processingEnv.getElementUtils().getPackageOf(typeElement).getQualifiedName().toString();
        System.out.print("packageName >>> "+packageName);
        return packageName;
    }

    private String getActivityName(ExecutableElement executableElement){
        //通过方法标签获取包名标签
        String packageName=getPackageName(executableElement);
        //通过方法标签获取类名标签
        TypeElement typeElement=(TypeElement)executableElement.getEnclosingElement();
        return packageName+"."+typeElement.getSimpleName().toString();
    }

}
