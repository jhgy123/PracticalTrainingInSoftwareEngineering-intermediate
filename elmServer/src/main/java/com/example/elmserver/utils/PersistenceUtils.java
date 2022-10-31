package com.example.elmserver.utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.Id;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class PersistenceUtils {

	public static Field getPrimaryKey(Object object) {
		try {
			SimpleJpaRepository clazz = (SimpleJpaRepository) AopTargetUtil.getTarget(object);
			Method method = clazz.getClass().getDeclaredMethod("getDomainClass");
			method.setAccessible(true);
			Class domainClass = (Class) method.invoke(clazz);
			List<Field> fields = new LinkedList<>();
			Class loopClass = domainClass;
			while (loopClass != null && !"java.lang.object".equalsIgnoreCase(loopClass.getName())) {
				fields.addAll(Arrays.stream(loopClass.getDeclaredFields()).collect(Collectors.toList()));
				loopClass = loopClass.getSuperclass();
			}
			String annotationName = Id.class.getTypeName();
			for (Field field : fields) {
				field.setAccessible(true);
				Annotation[] annotations = field.getAnnotations();
				for (Annotation annotation : annotations) {
					Field proxy = annotation.getClass().getSuperclass().getDeclaredField("h");
					proxy.setAccessible(true);
					Object handler = proxy.get(annotation);
					Field typeField = handler.getClass().getDeclaredField("type");
					typeField.setAccessible(true);
					Class annotationClass = (Class) typeField.get(handler);
					if (annotationClass.getTypeName().equals(annotationName)) {
						return field;
					}
				}
			}
			return null;
		}
		catch (Exception e) {
			return null;
		}
	}

}
