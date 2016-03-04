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

import com.google.common.collect.Lists;
import org.aspectj.bridge.IMessage;
import org.aspectj.bridge.MessageHandler;
import org.aspectj.tools.ajc.Main;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Iterables.toArray;
import static io.github.whiskeysierra.azure.CompilerMatchers.hasError;
import static io.github.whiskeysierra.azure.CompilerMatchers.hasNoErrors;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public interface Compiling {

    default List<IMessage> compile(final Class<?>... classes) throws IOException {
        final TemporaryFolder temporaryFolder = new TemporaryFolder();
        temporaryFolder.create();

        try {
            final List<String> arguments = new ArrayList<>();

            arguments.add("-source");
            arguments.add("1.8");
            arguments.add("-target");
            arguments.add("1.8");

            arguments.add("-cp");
            arguments.add(System.getProperty("java.class.path"));

            arguments.add("-aspectpath");
            arguments.add("target/classes");

            arguments.add("-d");

            arguments.add(temporaryFolder.newFolder().getAbsolutePath());

            asList(classes).stream()
                    .map(Class::getName)
                    .map(n -> n.replace('.', '/'))
                    .map(n -> format("src/test/java/%s.java", n))
                    .forEach(arguments::add);

            final Main compiler = new Main();
            final MessageHandler handler = new MessageHandler();
            compiler.run(toArray(arguments, String.class), handler);

            return asList(handler.getMessages(null, true))
                    .stream()
                    .filter(IMessage::isError)
                    .collect(toList());
        } finally {
            temporaryFolder.delete();
        }
    }

    String errorMessage();
    
    default void allow(final Class<?> unit, final Class<?>... classes) throws IOException {
        final List<IMessage> compile = compile(unit, classes);
        assertThat(compile, hasNoErrors());
    }

    default void deny(final Class<?> unit, final Class<?>... classes) throws IOException {
        final List<IMessage> compile = compile(unit, classes);
        assertThat(compile, hasError(unit, containsString(errorMessage())));
    }

    default List<IMessage> compile(Class<?> unit, Class<?>[] classes) throws IOException {
        return compile(toArray(Lists.asList(unit, classes), Class.class));
    }


}
