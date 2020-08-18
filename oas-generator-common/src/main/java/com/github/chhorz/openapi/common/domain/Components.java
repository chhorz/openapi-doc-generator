/**
 *
 *    Copyright 2018-2020 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.github.chhorz.openapi.common.domain;

import com.github.chhorz.openapi.common.exception.SpecificationViolationException;
import com.github.chhorz.openapi.common.util.ProcessingUtils;
import com.github.chhorz.openapi.common.util.SchemaUtils;

import javax.lang.model.type.TypeMirror;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://spec.openapis.org/oas/v3.0.3#components-object
 *
 * @author chhorz
 *
 */
public class Components {

	private Map<String, Schema> schemas;
	private Map<String, Response> responses;
	private Map<String, Parameter> parameters;
	private Map<String, Example> examples;
	private Map<String, RequestBody> requestBodies;
	private Map<String, Header> headers;
	private Map<String, SecurityScheme> securitySchemes;
	private Map<String, Link> links;
	private Map<String, Callback> callbacks;

	public static boolean isValidKey(final String key) {
		return key.matches("^[a-zA-Z0-9.\\-_]+$");
	}

	public void putAllSchemas(final Map<TypeMirror, Schema> schemas) {
		if (this.schemas == null) {
			this.schemas = new TreeMap<>();
		}

		schemas.forEach((typeMirror, schema) -> putSchema(getKey(typeMirror), schema));
	}

	public void putAllParsedSchemas(final Map<String, Schema> schemas) {
		if (this.schemas == null) {
			this.schemas = new TreeMap<>();
		}

		schemas.forEach(this::putSchema);
	}

	private void putSchema(final String type, final Schema schema) {
		if (schemas.containsKey(type)) {
			Schema existingType = schemas.get(type);
			if (existingType.getType().equals(schema.getType())) {
				schemas.put(type, SchemaUtils.mergeSchemas(existingType, schema));
			}
		} else {
			schemas.put(type, schema);
		}
	}

	public Map<String, Schema> getSchemas() {
		return schemas;
	}

	public void putRequestBody(final TypeMirror typeMirror, final RequestBody requestBody) {
		if (requestBodies == null) {
			requestBodies = new TreeMap<>();
		}
		requestBodies.put(getKey(typeMirror), requestBody);
	}

	public void putRequestBody(final String type, final RequestBody requestBody) {
		if (requestBodies == null) {
			requestBodies = new TreeMap<>();
		}
		requestBodies.put(type, requestBody);
	}

	public Map<String, RequestBody> getRequestBodies() {
		return requestBodies;
	}

	public void putResponse(final TypeMirror typeMirror, final Response response) {
		if (responses == null) {
			responses = new TreeMap<>();
		}
		responses.put(getKey(typeMirror), response);
	}

	public Map<String, Response> getResponses() {
		return responses;
	}

	public Map<String, SecurityScheme> getSecuritySchemes() {
		return securitySchemes;
	}

	public void setSecuritySchemes(final Map<String, SecurityScheme> securitySchemes) {
		this.securitySchemes = securitySchemes;
	}

	private String getKey(final TypeMirror typeMirror) {
		final String key = ProcessingUtils.getShortName(typeMirror);

		if (!isValidKey(key)) {
			throw new SpecificationViolationException("The current key '" + key + "' is not valid within component maps.");
		}

		return key;
	}

}
