package io.github.whiskeysierra.azure;

/*
 * ⁣​
 * Azure: AspectJ
 * ⁣⁣
 * Copyright (C) 2015 - 2016 whiskeysierra
 * ⁣⁣
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ​⁣
 */

import org.example.SomeGateway;
import org.example.SomeLibrary;
import org.example.SomeLogic;
import org.example.SomeModel;
import org.example.SomePersistence;
import org.example.SomeQueue;
import org.example.SomeResource;
import org.example.SomeScheduler;
import org.example.resource.GatewayCallingResource;
import org.example.resource.LibraryCallingResource;
import org.example.resource.LogicCallingResource;
import org.example.resource.ModelCallingResource;
import org.example.resource.PersistenceCallingResource;
import org.example.resource.QueueCallingResource;
import org.example.resource.ResourceCallingResource;
import org.example.resource.SchedulerCallingResource;
import org.junit.Test;

import java.io.IOException;

public final class ResourceTest implements Compiling {

    @Override
    public String errorMessage() {
        return LayerPolicy.RESOURCE;
    }

    @Test
    public void shouldSucceedWhenCallingGateway() throws IOException {
        allow(GatewayCallingResource.class, SomeGateway.class);
    }

    @Test
    public void shouldSucceedWhenCallingLibrary() throws IOException {
        allow(LibraryCallingResource.class, SomeLibrary.class);
    }

    @Test
    public void shouldSucceedWhenCallingLogic() throws IOException {
        allow(LogicCallingResource.class, SomeLogic.class);
    }

    @Test
    public void shouldSucceedWhenCallingModel() throws IOException {
        allow(ModelCallingResource.class, SomeModel.class);
    }

    @Test
    public void shouldSucceedWhenCallingPersistence() throws IOException {
        allow(PersistenceCallingResource.class, SomePersistence.class);
    }

    @Test
    public void shouldFailWhenCallingQueue() throws IOException {
        deny(QueueCallingResource.class, SomeQueue.class);
    }

    @Test
    public void shouldSucceedWhenCallingResource() throws IOException {
        allow(ResourceCallingResource.class, SomeResource.class);
    }

    @Test
    public void shouldFailWhenCallingScheduler() throws IOException {
        deny(SchedulerCallingResource.class, SomeScheduler.class);
    }

}
