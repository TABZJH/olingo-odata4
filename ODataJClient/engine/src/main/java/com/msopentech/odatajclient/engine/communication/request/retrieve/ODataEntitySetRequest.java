/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.msopentech.odatajclient.engine.communication.request.retrieve;

import com.msopentech.odatajclient.engine.client.ODataClient;
import com.msopentech.odatajclient.engine.communication.response.ODataRetrieveResponse;
import com.msopentech.odatajclient.engine.data.ODataEntitySet;
import com.msopentech.odatajclient.engine.format.ODataPubFormat;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

/**
 * This class implements an OData EntitySet query request.
 */
public class ODataEntitySetRequest extends AbstractODataRetrieveRequest<ODataEntitySet, ODataPubFormat> {

    private ODataEntitySet feed = null;

    /**
     * Private constructor.
     *
     * @param odataClient client instance getting this request
     * @param query query to be executed.
     */
    ODataEntitySetRequest(final ODataClient odataClient, final URI query) {
        super(odataClient, ODataPubFormat.class, query);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ODataRetrieveResponse<ODataEntitySet> execute() {
        final HttpResponse res = doExecute();
        return new ODataEntitySetResponseImpl(httpClient, res);
    }

    /**
     * Response class about an ODataEntitySetRequest.
     */
    protected class ODataEntitySetResponseImpl extends ODataRetrieveResponseImpl {

        /**
         * Constructor.
         * <p>
         * Just to create response templates to be initialized from batch.
         */
        private ODataEntitySetResponseImpl() {
        }

        /**
         * Constructor.
         *
         * @param client HTTP client.
         * @param res HTTP response.
         */
        private ODataEntitySetResponseImpl(final HttpClient client, final HttpResponse res) {
            super(client, res);
        }

        /**
         * {@inheritDoc }
         */
        @Override
        @SuppressWarnings("unchecked")
        public ODataEntitySet getBody() {
            if (feed == null) {
                try {
                    feed = odataClient.getReader().
                            readEntitySet(getRawResponse(), ODataPubFormat.fromString(getContentType()));
                } finally {
                    this.close();
                }
            }
            return feed;
        }
    }
}
