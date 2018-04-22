package com.github.chhorz.openapi.common.util;

import java.util.List;

import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class TypeMirrorUtils {

	private Elements elements;
	private Types types;
	
	public TypeMirrorUtils(final Elements elements, final Types types) {
		this.elements = elements;
		this.types = types;
	}

	public TypeMirror removeEnclosingType(final TypeMirror originalReturnType, final Class<?> removableClass) {
		// The given type has to be assignable to the type of the class: List<String> is assignable to List.class
		if (types.isAssignable(types.erasure(originalReturnType), elements.getTypeElement(removableClass.getCanonicalName()).asType())) {
			if(originalReturnType instanceof DeclaredType) {
				List<? extends TypeMirror> typeArguments = ((DeclaredType) originalReturnType).getTypeArguments();
				if (typeArguments != null && !typeArguments.isEmpty()) {
					// TODO rework to get the index of the requested type
					return typeArguments.get(0);
				}
			}
		}
		return originalReturnType;
	}

	
	
}