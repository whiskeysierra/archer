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

import org.example.alpha.Alpha;
import org.example.alpha.AlphaGateway;
import org.example.alpha.AlphaRepository;
import org.example.alpha.AlphaResource;
import org.example.alpha.AlphaService;
import org.example.bravo.Bravo;
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
        final Compilation compilation = compiler.compile(Alpha.class, AlphaGateway.class,
                AlphaRepository.class, AlphaResource.class, AlphaService.class);
        
        assertThat(compilation, hasNoErrors());
    }
    
    @Test
    public void shouldFail() {
        final Compilation compilation = compiler.compile(Bravo.class);

        assertThat(compilation, hasError(Bravo.class,
                containsString("must be part of a layer")));
    }
    

}