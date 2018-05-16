package io.github.whiskeysierra.archer;

/*-
 * ⁣​
 * Archer: AspectJ
 * ⁣⁣
 * Copyright (C) 2015 - 2018 whiskeysierra
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
import org.example.queue.GatewayCallingQueue;
import org.example.queue.LibraryCallingQueue;
import org.example.queue.LogicCallingQueue;
import org.example.queue.ModelCallingQueue;
import org.example.queue.PersistenceCallingQueue;
import org.example.queue.QueueCallingQueue;
import org.example.queue.ResourceCallingQueue;
import org.example.queue.SchedulerCallingQueue;
import org.junit.Test;

import java.io.IOException;

public final class QueueTest implements Compiling {

    @Override
    public String errorMessage() {
        return LayerPolicy.QUEUE;
    }

    @Test
    public void shouldFailWhenCallingGateway() throws IOException {
        deny(GatewayCallingQueue.class, SomeGateway.class);
    }

    @Test
    public void shouldSucceedWhenCallingLibrary() throws IOException {
        allow(LibraryCallingQueue.class, SomeLibrary.class);
    }

    @Test
    public void shouldSucceedWhenCallingLogic() throws IOException {
        allow(LogicCallingQueue.class, SomeLogic.class);
    }

    @Test
    public void shouldSucceedWhenCallingModel() throws IOException {
        allow(ModelCallingQueue.class, SomeModel.class);
    }

    @Test
    public void shouldFailWhenCallingPersistence() throws IOException {
        deny(PersistenceCallingQueue.class, SomePersistence.class);
    }

    @Test
    public void shouldSucceedWhenCallingQueue() throws IOException {
        allow(QueueCallingQueue.class, SomeQueue.class);
    }

    @Test
    public void shouldFailWhenCallingResource() throws IOException {
        deny(ResourceCallingQueue.class, SomeResource.class);
    }

    @Test
    public void shouldFailWhenCallingScheduler() throws IOException {
        deny(SchedulerCallingQueue.class, SomeScheduler.class);
    }

}
