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
import org.example.persistence.GatewayCallingPersistence;
import org.example.persistence.LibraryCallingPersistence;
import org.example.persistence.LogicCallingPersistence;
import org.example.persistence.ModelCallingPersistence;
import org.example.persistence.PersistenceCallingPersistence;
import org.example.persistence.QueueCallingPersistence;
import org.example.persistence.ResourceCallingPersistence;
import org.example.persistence.SchedulerCallingPersistence;
import org.junit.Test;

import java.io.IOException;

public final class PersistenceTest implements Compiling {

    @Override
    public String errorMessage() {
        return LayerPolicy.PERSISTENCE;
    }

    @Test
    public void shouldFailWhenCallingGateway() throws IOException {
        deny(GatewayCallingPersistence.class, SomeGateway.class);
    }

    @Test
    public void shouldSucceedWhenCallingLibrary() throws IOException {
        allow(LibraryCallingPersistence.class, SomeLibrary.class);
    }

    @Test
    public void shouldFailWhenCallingLogic() throws IOException {
        deny(LogicCallingPersistence.class, SomeLogic.class);
    }

    @Test
    public void shouldSucceedWhenCallingModel() throws IOException {
        allow(ModelCallingPersistence.class, SomeModel.class);
    }

    @Test
    public void shouldSucceedWhenCallingPersistence() throws IOException {
        allow(PersistenceCallingPersistence.class, SomePersistence.class);
    }

    @Test
    public void shouldFailWhenCallingQueue() throws IOException {
        deny(QueueCallingPersistence.class, SomeQueue.class);
    }

    @Test
    public void shouldFailWhenCallingResource() throws IOException {
        deny(ResourceCallingPersistence.class, SomeResource.class);
    }

    @Test
    public void shouldFailWhenCallingScheduler() throws IOException {
        deny(SchedulerCallingPersistence.class, SomeScheduler.class);
    }

}
