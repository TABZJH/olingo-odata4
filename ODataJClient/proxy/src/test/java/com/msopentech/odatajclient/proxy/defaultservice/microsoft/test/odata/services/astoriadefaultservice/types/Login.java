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
package com.msopentech.odatajclient.proxy.defaultservice.microsoft.test.odata.services.astoriadefaultservice.types;

import com.msopentech.odatajclient.proxy.api.annotations.Namespace;
import com.msopentech.odatajclient.proxy.api.annotations.EntityType;
import com.msopentech.odatajclient.proxy.api.annotations.Key;
import com.msopentech.odatajclient.proxy.api.annotations.KeyRef;
import com.msopentech.odatajclient.proxy.api.annotations.NavigationProperty;
import com.msopentech.odatajclient.proxy.api.annotations.Property;
import com.msopentech.odatajclient.proxy.api.annotations.Operation;
import com.msopentech.odatajclient.proxy.api.annotations.Parameter;
import com.msopentech.odatajclient.engine.metadata.edm.v3.ParameterMode;
import com.msopentech.odatajclient.proxy.api.AbstractComplexType;
import com.msopentech.odatajclient.proxy.api.AbstractOpenType;
import com.msopentech.odatajclient.engine.metadata.EdmContentKind;
import com.msopentech.odatajclient.engine.metadata.edm.ConcurrencyMode;
import com.msopentech.odatajclient.proxy.defaultservice.microsoft.test.odata.services.astoriadefaultservice.*;
import com.msopentech.odatajclient.proxy.defaultservice.microsoft.test.odata.services.astoriadefaultservice.types.*;

// EdmSimpleType property imports
import com.msopentech.odatajclient.engine.data.ODataDuration;
import com.msopentech.odatajclient.engine.data.ODataTimestamp;
import com.msopentech.odatajclient.engine.metadata.edm.geospatial.Geospatial;
import com.msopentech.odatajclient.engine.metadata.edm.geospatial.GeospatialCollection;
import com.msopentech.odatajclient.engine.metadata.edm.geospatial.LineString;
import com.msopentech.odatajclient.engine.metadata.edm.geospatial.MultiLineString;
import com.msopentech.odatajclient.engine.metadata.edm.geospatial.MultiPoint;
import com.msopentech.odatajclient.engine.metadata.edm.geospatial.MultiPolygon;
import com.msopentech.odatajclient.engine.metadata.edm.geospatial.Point;
import com.msopentech.odatajclient.engine.metadata.edm.geospatial.Polygon;
import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;
import java.io.Serializable;
import java.util.Collection;


@Namespace("Microsoft.Test.OData.Services.AstoriaDefaultService")
@EntityType(name = "Login",
        openType = false,
        hasStream = false,
        isAbstract = false,
        baseType = "")
public interface Login extends Serializable {

    
    @Key
    @Property(name = "Username", 
                type = "Edm.String", 
                nullable = false,
                defaultValue = "",
                maxLenght = Integer.MAX_VALUE,
                fixedLenght = false,
                precision = 0,
                scale = 0,
                unicode = true,
                collation = "",
                srid = "",
                concurrencyMode = ConcurrencyMode.None,
                fcSourcePath = "",
                fcTargetPath = "",
                fcContentKind = EdmContentKind.text,
                fcNSPrefix = "",
                fcNSURI = "",
                fcKeepInContent = true)
    public String getUsername();

    public void setUsername(final String _username);

    
    @Property(name = "CustomerId", 
                type = "Edm.Int32", 
                nullable = false,
                defaultValue = "",
                maxLenght = Integer.MAX_VALUE,
                fixedLenght = false,
                precision = 0,
                scale = 0,
                unicode = true,
                collation = "",
                srid = "",
                concurrencyMode = ConcurrencyMode.None,
                fcSourcePath = "",
                fcTargetPath = "",
                fcContentKind = EdmContentKind.text,
                fcNSPrefix = "",
                fcNSURI = "",
                fcKeepInContent = true)
    public Integer getCustomerId();

    public void setCustomerId(final Integer _customerId);

    


    @NavigationProperty(name = "Customer", 
                type = "Microsoft.Test.OData.Services.AstoriaDefaultService.Customer", 
                targetSchema = "Microsoft.Test.OData.Services.AstoriaDefaultService", 
                targetContainer = "DefaultContainer", 
                targetEntitySet = "Customer")
    public com.msopentech.odatajclient.proxy.defaultservice.microsoft.test.odata.services.astoriadefaultservice.types.Customer getCustomer();

    public void setCustomer(final com.msopentech.odatajclient.proxy.defaultservice.microsoft.test.odata.services.astoriadefaultservice.types.Customer _customer);


    @NavigationProperty(name = "LastLogin", 
                type = "Microsoft.Test.OData.Services.AstoriaDefaultService.LastLogin", 
                targetSchema = "Microsoft.Test.OData.Services.AstoriaDefaultService", 
                targetContainer = "DefaultContainer", 
                targetEntitySet = "LastLogin")
    public com.msopentech.odatajclient.proxy.defaultservice.microsoft.test.odata.services.astoriadefaultservice.types.LastLogin getLastLogin();

    public void setLastLogin(final com.msopentech.odatajclient.proxy.defaultservice.microsoft.test.odata.services.astoriadefaultservice.types.LastLogin _lastLogin);


    @NavigationProperty(name = "SentMessages", 
                type = "Collection(Microsoft.Test.OData.Services.AstoriaDefaultService.Message)", 
                targetSchema = "Microsoft.Test.OData.Services.AstoriaDefaultService", 
                targetContainer = "DefaultContainer", 
                targetEntitySet = "Message")
    public com.msopentech.odatajclient.proxy.defaultservice.microsoft.test.odata.services.astoriadefaultservice.types.MessageCollection getSentMessages();

    public void setSentMessages(final com.msopentech.odatajclient.proxy.defaultservice.microsoft.test.odata.services.astoriadefaultservice.types.MessageCollection _sentMessages);


    @NavigationProperty(name = "ReceivedMessages", 
                type = "Collection(Microsoft.Test.OData.Services.AstoriaDefaultService.Message)", 
                targetSchema = "Microsoft.Test.OData.Services.AstoriaDefaultService", 
                targetContainer = "DefaultContainer", 
                targetEntitySet = "Message")
    public com.msopentech.odatajclient.proxy.defaultservice.microsoft.test.odata.services.astoriadefaultservice.types.MessageCollection getReceivedMessages();

    public void setReceivedMessages(final com.msopentech.odatajclient.proxy.defaultservice.microsoft.test.odata.services.astoriadefaultservice.types.MessageCollection _receivedMessages);


    @NavigationProperty(name = "Orders", 
                type = "Collection(Microsoft.Test.OData.Services.AstoriaDefaultService.Order)", 
                targetSchema = "Microsoft.Test.OData.Services.AstoriaDefaultService", 
                targetContainer = "DefaultContainer", 
                targetEntitySet = "Order")
    public com.msopentech.odatajclient.proxy.defaultservice.microsoft.test.odata.services.astoriadefaultservice.types.OrderCollection getOrders();

    public void setOrders(final com.msopentech.odatajclient.proxy.defaultservice.microsoft.test.odata.services.astoriadefaultservice.types.OrderCollection _orders);



}
