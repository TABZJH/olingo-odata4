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
import com.msopentech.odatajclient.engine.client.http.HttpClientException;
import com.msopentech.odatajclient.engine.communication.header.ODataHeaders;
import com.msopentech.odatajclient.engine.communication.response.ODataRetrieveResponse;
import com.msopentech.odatajclient.engine.format.ODataMediaFormat;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

/**
 * This class implements an OData media query request.
 */
public class ODataMediaRequest extends AbstractODataRetrieveRequest<InputStream, ODataMediaFormat> {

    /**
     * Private constructor.
     *
     * @param odataClient client instance getting this request
     * @param query query to be executed.
     */
    ODataMediaRequest(final ODataClient odataClient, final URI query) {
        super(odataClient, ODataMediaFormat.class, query);

        setAccept(ODataMediaFormat.APPLICATION_OCTET_STREAM.toString());
        setContentType(ODataMediaFormat.APPLICATION_OCTET_STREAM.toString());

        this.odataHeaders.removeHeader(ODataHeaders.HeaderName.minDataServiceVersion);
        this.odataHeaders.removeHeader(ODataHeaders.HeaderName.maxDataServiceVersion);
        this.odataHeaders.removeHeader(ODataHeaders.HeaderName.dataServiceVersion);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ODataRetrieveResponse<InputStream> execute() {
        final HttpResponse res = doExecute();
        return new ODataMediaResponseImpl(httpClient, res);
    }

    /**
     * Response class about an ODataMediaRequest.
     */
    protected class ODataMediaResponseImpl extends ODataRetrieveResponseImpl {

        private InputStream input = null;

        /**
         * Constructor.
         * <p>
         * Just to create response templates to be initialized from batch.
         */
        private ODataMediaResponseImpl() {
        }

        /**
         * Constructor.
         *
         * @param client HTTP client.
         * @param res HTTP response.
         */
        private ODataMediaResponseImpl(final HttpClient client, final HttpResponse res) {
            super(client, res);
        }

        /**
         * Gets query result objects.
         * <br/>
         * <b>WARNING</b>: Closing this <tt>ODataResponse</tt> instance is left to the caller.
         *
         * @return query result objects as <tt>InputStream</tt>.
         */
        @Override
        public InputStream getBody() {
            if (input == null) {
                try {
                    input = res.getEntity().getContent();
                } catch (IOException e) {
                    throw new HttpClientException(e);
                }
            }
            return input;
        }
    }
}
