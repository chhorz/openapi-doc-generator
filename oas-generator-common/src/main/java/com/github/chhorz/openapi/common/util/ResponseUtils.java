package com.github.chhorz.openapi.common.util;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import com.github.chhorz.javadoc.JavaDoc;
import com.github.chhorz.openapi.common.domain.MediaType;
import com.github.chhorz.openapi.common.domain.Response;
import com.github.chhorz.openapi.common.domain.Schema;
import com.github.chhorz.openapi.common.javadoc.ResponseTag;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;

public class ResponseUtils {

	private static final Predicate<String> NOT_NULL_OR_EMPTY = s -> s != null && !s.isEmpty();

	private SchemaUtils schemaUtils;
	private TypeMirrorUtils typeMirrorUtils;

	public ResponseUtils(final Elements elements, Types types, LoggingUtils log) {
		this.schemaUtils = new SchemaUtils(elements, types, log);
		this.typeMirrorUtils = new TypeMirrorUtils(elements, types);
	}

	public Response fromTypeMirror(final TypeMirror typeMirror, final String[] produces, String description) {
		Response response = new Response();
		response.setDescription(description != null ? description : "");

		Schema schema = schemaUtils.mapTypeMirrorToSchema(typeMirror).get(typeMirror);

		MediaType mediaType = new MediaType();
		if (schema.getType().equals(Schema.Type.ARRAY)) {
			mediaType.setSchema(schema);
		} else {
			mediaType.setSchema(ReferenceUtils.createSchemaReference(typeMirror));
		}

		if (produces == null || produces.length == 0) {
			response.putContent("*/*", mediaType);
		} else {
			for (String producesHeader : produces) {
				response.putContent(producesHeader, mediaType);
			}
		}

		return response;
	}

	public Response fromSchema(final Schema schema, final String[] produces, String description) {
		Response response = new Response();
		response.setDescription(description != null ? description : "");

		MediaType mediaType = new MediaType();
		mediaType.setSchema(schema);

		if (produces == null || produces.length == 0) {
			response.putContent("*/*", mediaType);
		} else {
			for (String producesHeader : produces) {
				response.putContent(producesHeader, mediaType);
			}
		}

		return response;
	}

	public Map<String, Response> initializeFromJavadoc(final JavaDoc javaDoc, final String[] produces, final String description) {
		Map<String, Response> responses = new TreeMap<>();

		if (javaDoc != null) {
			List<ResponseTag> responseTags = javaDoc.getTags(ResponseTag.class);
			responseTags.stream()
					.filter(tag -> NOT_NULL_OR_EMPTY.test(tag.getStatusCode()))
					.filter(tag -> NOT_NULL_OR_EMPTY.test(tag.getResponseType()))
					.forEach(responseTag -> {
						TypeMirror responseType = typeMirrorUtils.createTypeMirrorFromString(responseTag.getResponseType());
						Response response = fromTypeMirror(responseType, produces, description);
						responses.put(responseTag.getStatusCode(), response);
					});
		}

		return responses;
	}

}
