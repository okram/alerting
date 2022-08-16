/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.alerting.core.model

import org.opensearch.common.settings.Settings
import org.opensearch.common.xcontent.*
import org.opensearch.commons.alerting.model.SearchInput
import org.opensearch.search.SearchModule

interface XContentTestBase {
    fun builder(): XContentBuilder {
        return XContentBuilder.builder(XContentType.JSON.xContent())
    }

    fun parser(xc: String): XContentParser {
        val parser = XContentType.JSON.xContent().createParser(xContentRegistry(), LoggingDeprecationHandler.INSTANCE, xc)
        parser.nextToken()
        return parser
    }

    fun xContentRegistry(): NamedXContentRegistry {
        return NamedXContentRegistry(
                listOf(SearchInput.XCONTENT_REGISTRY) +
                        SearchModule(Settings.EMPTY, emptyList()).namedXContents
        )
    }
}
