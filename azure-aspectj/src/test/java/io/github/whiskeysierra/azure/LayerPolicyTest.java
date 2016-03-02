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

import org.example.alpha.AlphaLibrary;
import org.example.alpha.AlphaModel;
import org.example.alpha.AlphaGateway;
import org.example.alpha.AlphaQueue;
import org.example.alpha.AlphaRepository;
import org.example.alpha.AlphaResource;
import org.example.alpha.AlphaScheduler;
import org.example.alpha.AlphaService;
import org.example.bravo.BravoGateway;
import org.example.bravo.BravoLibrary;
import org.example.bravo.BravoModel;
import org.example.bravo.BravoQueue;
import org.example.bravo.BravoResource;
import org.example.bravo.BravoScheduler;
import org.example.bravo.BravoSomething;
import org.example.bravo.BravoRepository;
import org.example.bravo.BravoService;
import org.junit.Rule;
import org.junit.Test;

import static io.github.whiskeysierra.azure.CompilerMatchers.hasError;
import static io.github.whiskeysierra.azure.CompilerMatchers.hasNoErrors;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public final class LayerPolicyTest {

    @Rule
    public final Compiler compiler = new Compiler();

    @Test
    public void shouldCompile() {
        final Compilation compilation = compiler.compile(AlphaGateway.class, AlphaLibrary.class, AlphaModel.class,
                AlphaQueue.class, AlphaRepository.class, AlphaResource.class, AlphaScheduler.class, AlphaService.class);
        
        assertThat(compilation, hasNoErrors());
    }
    
    @Test
    public void shouldNotCompile() {
        final Compilation compilation = compiler.compile(BravoGateway.class, BravoLibrary.class, BravoModel.class,
                BravoQueue.class, BravoRepository.class, BravoResource.class, BravoScheduler.class, BravoService.class,
                BravoSomething.class);

        assertThat(compilation, hasError(BravoSomething.class,
                containsString("must be part of a layer")));
        
        
        
        assertThat(compilation, hasError(BravoRepository.class,
                containsString("may only call @Library and @Model")));
        
        
    }

}