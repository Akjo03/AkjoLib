package io.github.akjo03.lib.annotation;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("io.github.akjo03.lib.annotation.PotentiallyDangerous")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
@SuppressWarnings("unused")
public class PotentiallyDangerousProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(PotentiallyDangerous.class)) {
            PotentiallyDangerous annotation = element.getAnnotation(PotentiallyDangerous.class);
            String message = annotation.value();
            processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, message, element);
        }
        return true;
    }
}