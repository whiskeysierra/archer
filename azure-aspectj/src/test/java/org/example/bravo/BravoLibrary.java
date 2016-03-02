package org.example.bravo;

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

import io.github.whiskeysierra.azure.Library;

@Library
public class BravoLibrary {
    
    private final BravoModel model;
    private final BravoQueue queue;
    private final BravoRepository repository;
    private final BravoResource resource;
    private final BravoScheduler scheduler;
    private final BravoService service;

    public BravoLibrary(BravoModel model, BravoQueue queue, BravoRepository repository, BravoResource resource, BravoScheduler scheduler, BravoService service) {
        this.model = model;
        this.queue = queue;
        this.repository = repository;
        this.resource = resource;
        this.scheduler = scheduler;
        this.service = service;
    }
    
    public void perform() {
        model.perform();
        queue.perform();
        repository.perform();
        resource.perform();
        scheduler.perform();
        service.perform();
    }
    
}
