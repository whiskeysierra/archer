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
import org.hamcrest.Matcher;

import java.util.Collection;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.hobsoft.hamcrest.compose.ComposeMatchers.compose;
import static org.hobsoft.hamcrest.compose.ComposeMatchers.hasFeature;

public final class CompilerMatchers {

    public static Matcher<? super Collection<IMessage>> hasNoErrors() {
        return empty();
    }

    public static Matcher<? super Collection<IMessage>> hasError(final Class<?> target,
            final Matcher<String> messageMatcher) {
        return contains(compose(hasFile(target)).and(hasMessage(messageMatcher)));
    }

    private static Matcher<IMessage> hasFile(final Class<?> target) {
        return hasFeature("location", (IMessage m) ->
                        m.getSourceLocation().getSourceFile().getAbsolutePath(),
                endsWith(target.getName().replace('.', '/') + ".java"));
    }

    private static Matcher<IMessage> hasMessage(final Matcher<String> matcher) {
        return hasFeature("message", IMessage::getMessage, matcher);
    }

}
