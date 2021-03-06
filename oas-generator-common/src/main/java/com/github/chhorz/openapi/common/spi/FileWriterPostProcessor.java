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
package com.github.chhorz.openapi.common.spi;

import com.github.chhorz.openapi.common.domain.OpenAPI;
import com.github.chhorz.openapi.common.properties.domain.ParserProperties;
import com.github.chhorz.openapi.common.util.FileUtils;
import com.github.chhorz.openapi.common.util.LogUtils;

import java.util.List;

import static java.util.Collections.singletonList;

/**
 * A custom {@link OpenAPIPostProcessor} that writes the OpenAPI domain object to a local file.
 *
 * @author chhorz
 */
public class FileWriterPostProcessor implements OpenAPIPostProcessor {

	private static final Integer POST_PROCESSOR_ORDER = Integer.MAX_VALUE;

	private final FileUtils fileUtils;
	private final LogUtils logUtils;

	FileWriterPostProcessor(final LogUtils logUtils, final ParserProperties parserProperties) {
		this.logUtils = logUtils;
		this.fileUtils = new FileUtils(logUtils, parserProperties);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(final OpenAPI openApi) {
		logUtils.logInfo("FileWriterPostProcessor | Start");
		fileUtils.writeToFile(openApi);
		logUtils.logInfo("FileWriterPostProcessor | Finish");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getPostProcessorOrder() {
		return POST_PROCESSOR_ORDER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PostProcessorType> getPostProcessorType() {
		return singletonList(PostProcessorType.DOMAIN_OBJECT);
	}
}
