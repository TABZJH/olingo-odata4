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
package com.msopentech.odatajclient.engine.it;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.msopentech.odatajclient.engine.communication.ODataClientErrorException;
import com.msopentech.odatajclient.engine.communication.request.retrieve.ODataV3MetadataRequest;
import com.msopentech.odatajclient.engine.communication.response.ODataRetrieveResponse;
import com.msopentech.odatajclient.engine.metadata.EdmType;
import com.msopentech.odatajclient.engine.metadata.EdmV3Metadata;
import com.msopentech.odatajclient.engine.metadata.EdmV3Type;
import com.msopentech.odatajclient.engine.metadata.edm.AbstractEntityType;
import com.msopentech.odatajclient.engine.metadata.edm.v3.EntityType;
import com.msopentech.odatajclient.engine.format.ODataPubFormat;

public class MetadataRetrieveTestITCase extends AbstractTestITCase {

    private void retreiveMetadataTest(final ODataPubFormat reqFormat, final String acceptFormat) {
        // testing entity types which are not open
        final ODataV3MetadataRequest req = client.getRetrieveRequestFactory().
                getMetadataRequest(testDefaultServiceRootURL);
        req.setFormat(reqFormat);
        req.setAccept(acceptFormat);
        try {
            final ODataRetrieveResponse<EdmV3Metadata> res = req.execute();
            final EdmV3Metadata metadata = res.getBody();
            assertNotNull(metadata);
            assertEquals(24, metadata.getSchemas().get(0).getEntityContainers().get(0).getEntitySets().size());
            final EdmType productCollection =
                    new EdmV3Type(metadata, "Collection(Microsoft.Test.OData.Services.AstoriaDefaultService.Product)");
            assertTrue(productCollection.isCollection());
            assertFalse(productCollection.isSimpleType());
            assertFalse(productCollection.isEnumType());
            assertFalse(productCollection.isComplexType());
            assertTrue(productCollection.isEntityType());
            final AbstractEntityType type = productCollection.getEntityType();
            assertNotNull(type);
            assertFalse(type.isOpenType());
            assertEquals("Product", type.getName());
            final EdmType stream = new EdmV3Type(metadata, "Edm.Stream");
            assertNotNull(stream);
            assertTrue(stream.isSimpleType());
            assertFalse(stream.isCollection());
            assertFalse(stream.isEnumType());
            assertFalse(stream.isComplexType());
            assertFalse(stream.isEntityType());
            final EdmType customerCollection =
                    new EdmV3Type(metadata, "Collection(Microsoft.Test.OData.Services.AstoriaDefaultService.Customer)");
            final AbstractEntityType customer = customerCollection.getEntityType();
            assertNotNull(type);
            assertFalse(customer.isOpenType());
            assertEquals("Customer", customer.getName());

            //testing open types
            ODataV3MetadataRequest req1 =
                    client.getRetrieveRequestFactory().getMetadataRequest(testOpenTypeServiceRootURL);

            req.setFormat(reqFormat);
            req.setAccept(acceptFormat);
            final ODataRetrieveResponse<EdmV3Metadata> res1 = req1.execute();
            final EdmV3Metadata metadata1 = res1.getBody();
            List<EntityType> types = metadata1.getSchema(0).getEntityTypes();
            for (int i = 0; i < types.size(); i++) {
                assertTrue(types.get(0).isOpenType());
            }
        } catch (ODataClientErrorException e) {
            assertEquals(415, e.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void jsonTest() {
        retreiveMetadataTest(ODataPubFormat.JSON, "application/json");
    }

    @Test
    public void atomTest() {
        retreiveMetadataTest(ODataPubFormat.ATOM, "application/atom+xml");
    }

    @Test
    public void xmlTest() {
        retreiveMetadataTest(ODataPubFormat.JSON, "application/xml");
    }

    @Test
    public void fullMetadataTest() {
        retreiveMetadataTest(ODataPubFormat.JSON_FULL_METADATA, "application/json");
    }

    @Test
    public void noMetadataTest() {
        retreiveMetadataTest(ODataPubFormat.JSON_NO_METADATA, "application/json");
    }

    @Test
    public void nullAcceptTest() {
        retreiveMetadataTest(ODataPubFormat.JSON_NO_METADATA, null);
    }

    @Test
    public void nullFormatTest() {
        retreiveMetadataTest(null, "application/json");
    }

    @Test
    public void atomWithNoAcceptTest() {
        retreiveMetadataTest(ODataPubFormat.ATOM, null);
    }

    @Test
    public void noFormatTest() {
        retreiveMetadataTest(null, "application/xml");
    }

    @Test
    public void large() {
        final EdmV3Metadata metadata = client.getRetrieveRequestFactory().
                getMetadataRequest(testLargeModelServiceRootURL).execute().getBody();
        assertNotNull(metadata);

        assertEquals(400, metadata.getSchemas().get(0).getEntityContainers().get(0).getEntitySets().size());
    }
}
