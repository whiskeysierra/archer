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

import org.aspectj.bridge.IMessage;
import org.aspectj.bridge.MessageHandler;
import org.aspectj.tools.ajc.Main;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Iterables.toArray;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public final class Compiler extends TestWatcher {

    private final TemporaryFolder folder = new TemporaryFolder();

    public Compiler() {
        try {
            folder.create();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public Compilation compile(final Class<?>... classes) {
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

        try {
            arguments.add(folder.newFolder().getAbsolutePath());
        } catch (IOException e) {
            throw new IllegalStateException();
        }

        asList(classes).stream()
                .map(Class::getName)
                .map(n -> n.replace('.', '/'))
                .map(n -> format("src/test/java/%s.java", n))
                .forEach(arguments::add);

        final Main compiler = new Main();
        final MessageHandler handler = new MessageHandler();
        compiler.run(toArray(arguments, String.class), handler);

        final List<IMessage> messages = asList(handler.getMessages(null, true))
                .stream()
                .filter(IMessage::isError)
                .collect(toList());
        
        return new Compilation(messages);
    }

    @Override
    protected void finished(Description description) {
        folder.delete();
    }

}
