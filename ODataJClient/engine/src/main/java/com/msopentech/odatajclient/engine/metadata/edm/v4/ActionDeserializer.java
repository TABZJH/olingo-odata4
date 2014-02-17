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
package com.msopentech.odatajclient.engine.metadata.edm.v4;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.msopentech.odatajclient.engine.metadata.edm.AbstractEdmDeserializer;
import java.io.IOException;
import org.apache.commons.lang3.BooleanUtils;

public class ActionDeserializer extends AbstractEdmDeserializer<Action> {

    @Override
    protected Action doDeserialize(final JsonParser jp, final DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        final Action action = new Action();

        for (; jp.getCurrentToken() != JsonToken.END_OBJECT; jp.nextToken()) {
            final JsonToken token = jp.getCurrentToken();
            if (token == JsonToken.FIELD_NAME) {
                if ("Name".equals(jp.getCurrentName())) {
                    action.setName(jp.nextTextValue());
                } else if ("IsBound".equals(jp.getCurrentName())) {
                    action.setBound(BooleanUtils.toBoolean(jp.nextTextValue()));
                } else if ("EntitySetPath".equals(jp.getCurrentName())) {
                    action.setEntitySetPath(jp.nextTextValue());
                } else if ("Parameter".equals(jp.getCurrentName())) {
                    jp.nextToken();
                    action.getParameters().add(jp.getCodec().readValue(jp, Parameter.class));
                } else if ("ReturnType".equals(jp.getCurrentName())) {
                    action.setReturnType(parseReturnType(jp, "Action"));
                } else if ("Annotation".equals(jp.getCurrentName())) {
                    jp.nextToken();
                    action.setAnnotation(jp.getCodec().readValue(jp, Annotation.class));
                }
            }
        }

        return action;
    }
}
