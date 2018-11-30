package com.github.chhorz.openapi.common.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.chhorz.openapi.common.domain.meta.Required;

/**
 * https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.2.md#server-variable-object
 *
 * @author chhorz
 *
 */
public class ServerVariableObject {

	@JsonProperty("enum")
	private List<String> enumValue;
	@Required
	@JsonProperty("default")
	private String defaultValue;
	private String description;

}
