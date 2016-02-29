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

import org.aspectj.tools.ajc.Main;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.github.whiskeysierra.azure.Result.ERROR;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LayerPolicyTest {

    @Test
    public void shouldFail() {
        final Result result = compile(new File("src/test/java/org/example/alpha/Person.java"),
                new File("src/main/java/io/github/whiskeysierra/azure/LayerPolicy.java"));

        assertThat(result, is(ERROR));
    }

    private Result compile(final File javaFileName, final File aspectFile) {

        // TODO assertExists(javaFileName);
        // TODO assertExists(aspectFile);

        List<String> argList = new ArrayList<>();

        // java 7 compatibility
        argList.add("-source");
        argList.add("1.8");
        argList.add("-target");
        argList.add("1.8");

        // set class path
        argList.add("-cp");
        argList.add(System.getProperty("java.class.path"));
        
        argList.add("-d");
        argList.add("/tmp");

        // add java file
        argList.add(javaFileName.getAbsolutePath());

        // add aspect files
        argList.add(aspectFile.getAbsolutePath());
//        for (File additionalAspectFile : requiredAspects) {
//            assertExists(additionalAspectFile);
//            argList.add(additionalAspectFile.getAbsolutePath());
//        }

        String[] args = argList.toArray(new String[argList.size()]);
        List<String> fails = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        List<String> warnings = new ArrayList<>();
        List<String> infos = new ArrayList<>();

        // org.aspectj.tools.ajc.Main;
        Main.bareMain(args, false, fails, errors, warnings, infos);

        if (!fails.isEmpty() || !errors.isEmpty()) {
            return ERROR;
        } else if (!warnings.isEmpty()) {
            return Result.WARNING;
        } else {
            return Result.SUCCESS;
        }
    }

}