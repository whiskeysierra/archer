package io.github.whiskeysierra.azure;

/*
 * ⁣​
 * Azure: AspectJ
 * ⁣⁣
 * Copyright (C) 2015 whiskeysierra
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

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareError;

@Aspect
public final class LayerPolicy {

    @DeclareError("staticinitialization(!@(@io.github.whiskeysierra.azure.Layer *) *)")
    public static final String LAYER = "must be part of a layer";

    @DeclareError("within(@Gateway *) && call(* (@(@Layer *) !@(Library || Model) *).*(..))")
    public static final String GATEWAY = "may only call @Library and @Model";
    
    @DeclareError("within(@Library *) && call(* (@(@Layer *) !@Model *).*(..))")
    public static final String LIBRARY = "may only call @Model";
    
    @DeclareError("within(@Logic *) && call(* (@(@Layer *) !@(Gateway || Model || Persistence) *).*(..))")
    public static final String LOGIC = "may only call @Gateway, @Model and @Persistence";
    
    @DeclareError("within(@Persistence *) && call(* (@(@Layer *) !@(Library || Model) *).*(..))")
    public static final String PERSISTENCE = "may only call @Library and @Model";

}
