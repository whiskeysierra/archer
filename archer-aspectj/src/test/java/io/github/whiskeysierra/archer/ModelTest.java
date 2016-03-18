package io.github.whiskeysierra.archer;

/*
 * ⁣​
 * Archer: AspectJ
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
import org.example.model.GatewayCallingModel;
import org.example.model.LibraryCallingModel;
import org.example.model.LogicCallingModel;
import org.example.model.ModelCallingModel;
import org.example.model.PersistenceCallingModel;
import org.example.model.QueueCallingModel;
import org.example.model.ResourceCallingModel;
import org.example.model.SchedulerCallingModel;
import org.junit.Test;

import java.io.IOException;

public final class ModelTest implements Compiling {

    @Override
    public String errorMessage() {
        return LayerPolicy.MODEL;
    }

    @Test
    public void shouldFailWhenCallingGateway() throws IOException {
        deny(GatewayCallingModel.class, SomeGateway.class);
    }

    @Test
    public void shouldSucceedWhenCallingLibrary() throws IOException {
        allow(LibraryCallingModel.class, SomeLibrary.class);
    }

    @Test
    public void shouldFailWhenCallingLogic() throws IOException {
        deny(LogicCallingModel.class, SomeLogic.class);
    }

    @Test
    public void shouldSucceedWhenCallingModel() throws IOException {
        allow(ModelCallingModel.class, SomeModel.class);
    }

    @Test
    public void shouldFailWhenCallingPersistence() throws IOException {
        deny(PersistenceCallingModel.class, SomePersistence.class);
    }

    @Test
    public void shouldFailWhenCallingQueue() throws IOException {
        deny(QueueCallingModel.class, SomeQueue.class);
    }

    @Test
    public void shouldFailWhenCallingResource() throws IOException {
        deny(ResourceCallingModel.class, SomeResource.class);
    }

    @Test
    public void shouldFailWhenCallingScheduler() throws IOException {
        deny(SchedulerCallingModel.class, SomeScheduler.class);
    }

}
