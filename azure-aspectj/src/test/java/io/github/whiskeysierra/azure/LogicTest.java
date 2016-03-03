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
import org.example.library.GatewayCallingLibrary;
import org.example.library.LibraryCallingLibrary;
import org.example.library.LogicCallingLibrary;
import org.example.library.ModelCallingLibrary;
import org.example.library.PersistenceCallingLibrary;
import org.example.library.QueueCallingLibrary;
import org.example.library.ResourceCallingLibrary;
import org.example.library.SchedulerCallingLibrary;
import org.example.logic.GatewayCallingLogic;
import org.example.logic.LibraryCallingLogic;
import org.example.logic.LogicCallingLogic;
import org.example.logic.ModelCallingLogic;
import org.example.logic.PersistenceCallingLogic;
import org.example.logic.QueueCallingLogic;
import org.example.logic.ResourceCallingLogic;
import org.example.logic.SchedulerCallingLogic;
import org.junit.Test;

import java.io.IOException;

public final class LogicTest implements Compiling {

    @Override
    public String errorMessage() {
        return LayerPolicy.LOGIC;
    }

    @Test
    public void shouldSucceedWhenCallingGateway() throws IOException {
        allow(GatewayCallingLogic.class, SomeGateway.class);
    }

    @Test
    public void shouldSucceedWhenCallingLibrary() throws IOException {
        allow(LibraryCallingLogic.class, SomeLibrary.class);
    }

    @Test
    public void shouldSucceedWhenCallingLogic() throws IOException {
        allow(LogicCallingLogic.class, SomeLogic.class);
    }

    @Test
    public void shouldSucceedWhenCallingModel() throws IOException {
        allow(ModelCallingLogic.class, SomeModel.class);
    }

    @Test
    public void shouldSucceedWhenCallingPersistence() throws IOException {
        allow(PersistenceCallingLogic.class, SomePersistence.class);
    }

    @Test
    public void shouldFailWhenCallingQueue() throws IOException {
        deny(QueueCallingLogic.class, SomeQueue.class);
    }

    @Test
    public void shouldFailWhenCallingResource() throws IOException {
        deny(ResourceCallingLogic.class, SomeResource.class);
    }

    @Test
    public void shouldFailWhenCallingScheduler() throws IOException {
        deny(SchedulerCallingLogic.class, SomeScheduler.class);
    }

}
