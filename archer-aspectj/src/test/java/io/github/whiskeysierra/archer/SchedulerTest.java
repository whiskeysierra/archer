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
import org.example.scheduler.GatewayCallingScheduler;
import org.example.scheduler.LibraryCallingScheduler;
import org.example.scheduler.LogicCallingScheduler;
import org.example.scheduler.ModelCallingScheduler;
import org.example.scheduler.PersistenceCallingScheduler;
import org.example.scheduler.QueueCallingScheduler;
import org.example.scheduler.ResourceCallingScheduler;
import org.example.scheduler.SchedulerCallingScheduler;
import org.junit.Test;

import java.io.IOException;

public final class SchedulerTest implements Compiling {

    @Override
    public String errorMessage() {
        return LayerPolicy.SCHEDULER;
    }

    @Test
    public void shouldFailWhenCallingGateway() throws IOException {
        deny(GatewayCallingScheduler.class, SomeGateway.class);
    }

    @Test
    public void shouldSucceedWhenCallingLibrary() throws IOException {
        allow(LibraryCallingScheduler.class, SomeLibrary.class);
    }

    @Test
    public void shouldSucceedWhenCallingLogic() throws IOException {
        allow(LogicCallingScheduler.class, SomeLogic.class);
    }

    @Test
    public void shouldSucceedWhenCallingModel() throws IOException {
        allow(ModelCallingScheduler.class, SomeModel.class);
    }

    @Test
    public void shouldFailWhenCallingPersistence() throws IOException {
        deny(PersistenceCallingScheduler.class, SomePersistence.class);
    }

    @Test
    public void shouldFailWhenCallingQueue() throws IOException {
        deny(QueueCallingScheduler.class, SomeQueue.class);
    }

    @Test
    public void shouldFailWhenCallingResource() throws IOException {
        deny(ResourceCallingScheduler.class, SomeResource.class);
    }

    @Test
    public void shouldSucceedWhenCallingScheduler() throws IOException {
        allow(SchedulerCallingScheduler.class, SomeScheduler.class);
    }

}
