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
package com.msopentech.odatajclient.engine.metadata.edm;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import com.msopentech.odatajclient.engine.client.ODataClient;
import com.msopentech.odatajclient.engine.metadata.edm.v4.ReturnType;
import com.msopentech.odatajclient.engine.metadata.edm.v4.annotation.ConstExprConstruct;
import java.io.IOException;

public abstract class AbstractEdmDeserializer<T> extends JsonDeserializer<T> {

    protected ODataClient client;

    protected boolean isAnnotationConstExprConstruct(final JsonParser jp) throws IOException {
        return ConstExprConstruct.Type.fromString(jp.getCurrentName()) != null;
    }

    protected ConstExprConstruct parseAnnotationConstExprConstruct(final JsonParser jp) throws IOException {
        final ConstExprConstruct constExpr = new ConstExprConstruct();
        constExpr.setType(ConstExprConstruct.Type.fromString(jp.getCurrentName()));
        constExpr.setValue(jp.nextTextValue());
        return constExpr;
    }

    protected ReturnType parseReturnType(final JsonParser jp, final String elementName) throws IOException {
        ReturnType returnType;
        if (elementName.equals(((FromXmlParser) jp).getStaxReader().getLocalName())) {
            returnType = new ReturnType();
            returnType.setType(jp.nextTextValue());
        } else {
            jp.nextToken();
            returnType = jp.getCodec().readValue(jp, ReturnType.class);
        }
        return returnType;
    }

    protected abstract T doDeserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException;

    @Override
    public T deserialize(final JsonParser jp, final DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        client = (ODataClient) ctxt.findInjectableValue(ODataClient.class.getName(), null, null);
        return doDeserialize(jp, ctxt);
    }

}
