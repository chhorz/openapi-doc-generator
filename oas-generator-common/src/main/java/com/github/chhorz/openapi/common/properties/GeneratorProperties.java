package com.github.chhorz.openapi.common.properties;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GeneratorProperties {

	private InfoProperties info;
	private List<ServerProperties> servers;
	private ExternalDocsProperties externalDocs;
	private Map<String, SecuritySchemeProperties> securitySchemes;
	private Map<String, TagProperties> tags;
	private ParserProperties parser;

	public GeneratorProperties() {
		info = new InfoProperties();
		servers = null;
		externalDocs = null;
		securitySchemes = new TreeMap<>();
		tags = new TreeMap<>();
		parser = new ParserProperties();
	}

	public InfoProperties getInfo() {
		return info;
	}

	public void setInfo(final InfoProperties info) {
		this.info = info;
	}

	public List<ServerProperties> getServers() {
		return servers;
	}

	public void setServers(final List<ServerProperties> servers) {
		this.servers = servers;
	}

	public ExternalDocsProperties getExternalDocs() {
		return externalDocs;
	}

	public void setExternalDocs(final ExternalDocsProperties externalDocs) {
		this.externalDocs = externalDocs;
	}

	public Map<String, SecuritySchemeProperties> getSecuritySchemes() {
		return securitySchemes;
	}

	public void setSecuritySchemes(final Map<String, SecuritySchemeProperties> securitySchemes) {
		this.securitySchemes = securitySchemes;
	}

	public Map<String, TagProperties> getTags() {
		return tags;
	}

	public void setTags(final Map<String, TagProperties> tags) {
		this.tags = tags;
	}

	public ParserProperties getParser() {
		return parser;
	}

	public void setParser(final ParserProperties parser) {
		this.parser = parser;
	}

}
